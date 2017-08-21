package com.xiangxun.atms.icabinet.isapi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtils {
	private static final String TAGSPLITCHAR = "/";

	private static Logger logger = LoggerFactory.getLogger(XmlUtils.class);

	public static Document createDocument(String xmlString) {
		if (StringUtils.isEmpty(xmlString))
			return null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			try (InputStream is = new ByteArrayInputStream(xmlString.getBytes("UTF-8"))) {// 按照UTF-8读：解决2字节的UTF-8序列的字节2无效
				return builder.parse(is);
			}
		} catch (ParserConfigurationException | SAXException | IOException ex) {
			logger.error(ex.getMessage() + "\n XML:" + xmlString);
		}
		return null;
	}

	/**
	 * 查询所有的节点
	 * 
	 * @param xmlString
	 * @param tagName
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<Node> getNodesByTag(String xmlString, String tagName) {
		List<Node> nodes = new ArrayList<Node>();
		tagName = trimTagName(tagName);
		tagName = trimTagName(tagName);
		if (StringUtils.isEmpty(tagName))
			return nodes;
		try {
			Document doc = createDocument(xmlString);
			if (doc != null) {
				NodeList elements = doc.getElementsByTagName(tagName);
				for (int idx = 0; idx < elements.getLength(); idx++) {
					nodes.add(elements.item(idx));
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return nodes;
	}

	/**
	 * 去掉tagName两端的空格与分隔符/
	 * 
	 * @param tagName
	 * @return
	 */
	private static String trimTagName(String tagName) {
		if (StringUtils.isEmpty(tagName))
			return "";
		tagName = StringUtils.trim(tagName);
		if (tagName.startsWith(TAGSPLITCHAR))
			tagName = StringUtils.substringAfter(tagName, TAGSPLITCHAR);
		if (tagName.endsWith(TAGSPLITCHAR))
			tagName = StringUtils.substringBefore(tagName, TAGSPLITCHAR);
		return tagName;
	}

	/**
	 * 按照rootnode/node1/node1.1/node...的格式进行取值
	 * 
	 * @param xmlString xml格式的字符串
	 * @param fullTagName root节点开始的节点名称（多级节点使用/分割）
	 * @return 找到的节点的值（若找到多个，则返回一个，不保证是第一个） ,没有找到则返回 ""
	 */
	public static String findValueByTagName(String xmlString, String fullTagName) {
		fullTagName = trimTagName(fullTagName);
		if (StringUtils.isEmpty(fullTagName))
			return "";
		Document doc = createDocument(xmlString);
		if (doc == null) {
			logger.error("XML document没有创建成功");
			return "";
		}
		Node node = getNodeByTagName(doc.getDocumentElement(), StringUtils.substringAfter(fullTagName, TAGSPLITCHAR));
		if (node == null) {
			logger.error("没有找到XML节点");
			return "";
		}
		return node.getTextContent();
	}

	/**
	 * 根据Tag的值取相同父节点的节点值
	 * 
	 * @param xmlString xml格式的字符串
	 * @param fullTagName root节点开始的节点名称（多级节点使用/分割）
	 * @param tagValue 条件值
	 * @param seekTagName 查找的相同父节点下的节点（多级节点使用/分割，不得包含父节点）
	 * @return 找到的节点的值（若找到多个，则返回一个，不保证是第一个）,没有找到则返回 ""
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static String seekValueByTagValue(String xmlString, String fullTagName, String tagValue, String seekTagName) throws SAXException, IOException, ParserConfigurationException {
		fullTagName = trimTagName(fullTagName);
		seekTagName = trimTagName(seekTagName);
		if (StringUtils.isEmpty(fullTagName) || StringUtils.isEmpty(seekTagName))
			return "";
		Document doc = createDocument(xmlString);
		if (doc == null)
			return "";
		List<Node> nodes = getNodesByTagNameAndValue(doc.getDocumentElement(), StringUtils.substringAfter(fullTagName, TAGSPLITCHAR), tagValue);
		if (nodes == null) {
			return "";
		}
		for (Node condNode : nodes) {
			Node parentNode = condNode.getParentNode();
			Node valueNode = getNodeByTagName(parentNode, seekTagName);
			if (valueNode == null)
				continue;
			return valueNode.getTextContent();
		}
		return "";
	}

	/**
	 * 根据节点的名称和值取所有的节点
	 * 
	 * @param parentNode 父节点
	 * @param tagName 相对父节点的从节点名称（不能包含父节点名称，多级节点使用/分割）
	 * @param tagValue 从节点的值
	 * @return 找到的所有节点
	 */
	private static List<Node> getNodesByTagNameAndValue(Node parentNode, String tagName, String tagValue) {
		List<Node> nodeList = new ArrayList<Node>();
		String[] tagNames = StringUtils.split(tagName, TAGSPLITCHAR);
		NodeList childNodes = parentNode.getChildNodes();
		int idx = 0;
		while (idx < childNodes.getLength()) {
			Node aNode = childNodes.item(idx);
			if (aNode.getNodeType() == Node.ELEMENT_NODE && StringUtils.equalsIgnoreCase(aNode.getNodeName(), tagNames[0])) {
				if (tagNames.length == 1) {
					String content = aNode.getTextContent();
					if (StringUtils.equalsIgnoreCase(content, tagValue))
						nodeList.add(aNode);
				} else {
					nodeList.addAll(getNodesByTagNameAndValue(aNode, StringUtils.substringAfter(tagName, TAGSPLITCHAR), tagValue));
				}
			}
			idx++;
		}
		return nodeList;
	}

	/**
	 * 取子节点的值
	 * 
	 * @param parentNode 父节点
	 * @param tagName parentNode的下级节点（不包含parentNode,可以包含多层子级）
	 * @return
	 */
	public static String getChildValue(Node parentNode, String tagName) {
		Node node = getNodeByTagName(parentNode, tagName);
		if (node == null) {
			return "";
		}
		return node.getTextContent();
	}

	/**
	 * 查找一个节点的值
	 * 
	 * @param parentNode parentNode 父节点
	 * @param tagName 相对父节点的从节点名称（不能包含父节点名称，多级节点使用/分割）
	 * @return 返回找到的节点，没找到返回null
	 */
	private static Node getNodeByTagName(Node parentNode, String tagName) {
		String[] tagNames = StringUtils.split(tagName, TAGSPLITCHAR);
		NodeList childNodes = parentNode.getChildNodes();
		int idx = 0;
		while (idx < childNodes.getLength()) {
			Node aNode = childNodes.item(idx);
			if (aNode.getNodeType() == Node.ELEMENT_NODE && StringUtils.equalsIgnoreCase(aNode.getNodeName(), tagNames[0])) {
				if (tagNames.length == 1)
					return aNode;
				else
					return getNodeByTagName(aNode, StringUtils.substringAfter(tagName, TAGSPLITCHAR));
			}
			idx++;
		}
		return null;
	}

	/**
	 * 修改xml的值
	 * 
	 * @param xmlString xml信息
	 * @param tags 要修改的tagname(从根开始)数组
	 * @param values 需要设置的值，与tags对应
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static String modifyTagValue(String xmlString, String[] tags, String[] values) {
		if (StringUtils.isEmpty(xmlString) || tags == null || values == null || tags.length != values.length)
			return xmlString;
		Document doc = createDocument(xmlString);
		if (doc == null)
			return "";
		int idx = 0;
		for (String tag : tags) {
			String value = values[idx++];
			tag = trimTagName(tag);
			if (StringUtils.isEmpty(tag))
				continue;

			Node node = getNodeByTagName(doc.getDocumentElement(), StringUtils.substringAfter(tag, TAGSPLITCHAR));
			if (node == null)
				continue;
			node.setTextContent(value);
		}
		return toStringFromDoc(doc);
	}

	/**
	 * 根据条件节点修改同级（或下级）节点的值
	 * 
	 * @param xmlString
	 * @param condTagName
	 * @param condValue
	 * @param targetTagNames
	 * @param values
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static String modifyTagValueByTagValue(String xmlString, String condTagName, String condValue, String[] targetTagNames, String[] values) {
		condTagName = trimTagName(condTagName);
		if (StringUtils.isEmpty(condTagName) || targetTagNames == null || values == null || targetTagNames.length != values.length)
			return xmlString;
		Document doc = createDocument(xmlString);
		if (doc == null)
			return xmlString;
		List<Node> nodes = getNodesByTagNameAndValue(doc.getDocumentElement(), StringUtils.substringAfter(condTagName, TAGSPLITCHAR), condValue);
		if (nodes == null) {
			return xmlString;
		}
		for (Node condNode : nodes) {
			Node parentNode = condNode.getParentNode();
			int idx = 0;
			for (String targetTagName : targetTagNames) {
				if (StringUtils.isEmpty(targetTagName))
					continue;
				String value = values[idx++];
				Node valueNode = getNodeByTagName(parentNode, targetTagName);
				if (valueNode == null)
					continue;
				valueNode.setTextContent(value);
			}
		}
		return toStringFromDoc(doc);
	}

	/**
	 * 把dom文件转换为xml字符串
	 * 
	 * @param document
	 * 
	 */
	public static String toStringFromDoc(Document document) {
		String result = null;

		if (document != null) {
			StringWriter strWtr = new StringWriter();
			StreamResult strResult = new StreamResult(strWtr);
			TransformerFactory tfac = TransformerFactory.newInstance();
			try {
				javax.xml.transform.Transformer t = tfac.newTransformer();
				t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				t.setOutputProperty(OutputKeys.INDENT, "yes");
				t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
				// text
				t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				t.transform(new DOMSource(document.getDocumentElement()), strResult);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			result = strResult.getWriter().toString();
			try {
				strWtr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
