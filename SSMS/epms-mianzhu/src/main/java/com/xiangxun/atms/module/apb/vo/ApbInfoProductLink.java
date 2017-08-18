package com.xiangxun.atms.module.apb.vo;

import java.io.Serializable;

public class ApbInfoProductLink implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3857144955171577778L;

	private String infoId;

    private String productId;

    /**
     * @return the value of INFO_ID
     */
    public String getInfoId() {
        return infoId;
    }

    /**
    
     *
     * @param infoId the value for INFO_ID
     */
    public void setInfoId(String infoId) {
        this.infoId = infoId == null ? null : infoId.trim();
    }

    /**
     * @return the value of PRODUCT_ID
     */
    public String getProductId() {
        return productId;
    }

    /**
    
     *
     * @param productId the value for PRODUCT_ID
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
    
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", infoId=").append(infoId);
        sb.append(", productId=").append(productId);
        sb.append("]");
        return sb.toString();
    }
}