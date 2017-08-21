/**
 * ************************修改代码时把我的js修改没有，不知道是什么原因
 * lhh*************************************************************
 */
/**
 * id图片input sctp 图片删除链接id pohon1 图片1的值 pohon2 图片2的值 pohon3 图片3的值
 */
function delByImg(id, sctp, pohon1, pohon2, pohon3) {
	//debugger;
	$("#" + id).attr("src", "");
	$("#" + id).attr("style", "display:none");
	$("#" + sctp).attr("style", "display:none");

	// $("#"+shhz).attr("style","display:none");
	// window.location.reload();
	if (id == "fileInput3") {
		// 三张图片的值都不空，删除成功后调用，不为空的上传第一个框
		if (pohon1 != null && pohon1 != "" && pohon2 != null && pohon2 != ""
				&& pohon3 != null && pohon3 != "") {

			// if($("#tr_two_td").val()!="" || $("#tr_two_td").html()!="" ||
			// $("#tr_three_td").val()!=""|| $("#tr_three_td").html()!="")
			// {
			// }else{
			// $("#tr_one_td").html("<nobr><input class=\"input-file
			// uniform_on\" style=\"display:inline\" name=\"file1\"
			// id=\"fileInput\" type=\"file\" onfocus=\"retrun upload()\"
			// onclick=\"retrun upload()\"><input type=\"button\"
			// id=\"fileInput_button\" onclick=\"return showFile('fileInput')\"
			// style=\"display:inline\" value=\"增加\"></nobr>");
			// $("#fileInput").attr("style","display:inline");
			// }
			if (str1($("#fileInput").attr("style")) == "display:none"
					&& str1($("#fileInput1").attr("style")) == "display:none"
					&& str1($("#fileInput2").attr("style")) == "display:none") {
				$("#tr_one_td").attr("style", "display:block");
                $("#fileInput").attr("style", "display:inline");
				$("#fileInput_button").attr("style", "display:inline");
			}

		} else {
			/*
			 * alert($("#fileInput1").attr("style").replace("
			 * ","")+"--"+$("#fileInput2").attr("style").replace(" ",""));
			 * //判断是否有显示的上传组件 if($("#fileInput1").attr("style").replace("
			 * ","")!="display:inline"&&$("#fileInput2").attr("style").replace("
			 * ","")!="display:inline") { //显示第一个上传框组件
			 * $("#fileInput_null").attr("style","display:inline"); //显示第一个上传框组件
			 * $("#fileInput_null_button").attr("style","display:inline"); }
			 */
		}

	} else if (id == "fileInput4") {
		// alert($("#fileInput").attr("style").replace("
		// ","")+"--"+$("#fileInput2").attr("style").replace(" ",""));
		// 三张图片的值都不空，删除成功后调用，不为空的上传第一个框
		if (pohon1 != null && pohon1 != "" && pohon2 != null && pohon2 != ""
				&& pohon3 != null && pohon3 != "") {

			/*
			 * if($("#tr_one_td").val()!="" || $("#tr_one_td").html()!="" ||
			 * $("#tr_three_td").val()!=""|| $("#tr_three_td").html()!="") {
			 * }else { $("#tr_two_td").html("<nobr><input class=\"input-file
			 * uniform_on\" style=\"display:inline\" name=\"file2\"
			 * id=\"fileInput1\" type=\"file\" ><input type=\"button\"
			 * id=\"fileInput1_button\" onclick=\"return
			 * showFile('fileInput1')\" style=\"display:inline\" value=\"增加\"></nobr>"); }
			 */
			if (str1($("#fileInput").attr("style")) == "display:none"
					&& str1($("#fileInput1").attr("style")) == "display:none"
					&& str1($("#fileInput2").attr("style")) == "display:none") {
                $("#tr_two_td").attr("style", "display:block");        
				$("#fileInput1").attr("style", "display:inline");
				$("#fileInput1_button").attr("style", "display:inline");
			}
		} else {

			// alert("else")
			// 三张图片的值都不空，删除成功后调用，不为空的上传第一个框
			// if(pohon1!=null&&pohon1!=""&&pohon2!=null&&pohon2!=""&&pohon3!=null&&pohon3!="")
			/*
			 * if($("#fileInput").attr("style").replace("
			 * ","")!="display:inline"&&$("#fileInput2").attr("style").replace("
			 * ","")!="display:inline") { //显示第二个上传框组件
			 * $("#fileInput1_null").attr("style","display:inline");
			 * //显示第二个上传框组件
			 * $("#fileInput1_null_button").attr("style","display:inline"); }
			 */
			/*
			 * if($("#upload_one_td").val()!="" ||
			 * $("#upload_one_td").html()!="" ||
			 * $("#upload_three_td").val()!=""||
			 * $("#upload_three_td").html()!="") { }else {
			 * $("#upload_two_td").html("<nobr><input class=\"input-file
			 * uniform_on\" style=\"display:inline\" name=\"file2\"
			 * id=\"fileInput1_null\" type=\"file\" ><input type=\"button\"
			 * id=\"fileInput1_null_button\" onclick=\"return
			 * showFile_null('fileInput1_null')\" style=\"display:inline\"
			 * value=\"增加\"></nobr>"); }
			 */

		}
	} else if (id == "fileInput5") {
		// debugger;
		// 三张图片的值都不空，删除成功后调用，不为空的上传第一个框
		if (pohon1 != null && pohon1 != "" && pohon2 != null && pohon2 != ""
				&& pohon3 != null && pohon3 != "") {
			// alert("#fileInput--"+$("#fileInput").attr("style").replace("
			// ","")+"--#fileInput1--"+$("#fileInput1").attr("style").replace("
			// ",""));
			// if($("#fileInput").attr("style").replace("
			// ","")!="display:inline"&&$("#fileInput1").attr("style").replace("
			// ","")!="display:inline")
			// {
			// 显示第三个上传框组件
			// $("#fileInput2").attr("style","display:inline");
			// 显示第三个上传框组件
			// $("#fileInput2_button").attr("style","display:inline");

			// }
			// else{
			// }
			/*
			 * if($("#tr_one_td").val()!="" || $("#tr_one_td").html()!="" ||
			 * $("#tr_two_td").val()!=""|| $("#tr_two_td").html()!="") { }else{
			 * $("#tr_three_td").html("<nobr><input class=\"input-file
			 * uniform_on\" style=\"display:inline\" name=\"file3\"
			 * id=\"fileInput2\" type=\"file\" ><input type=\"button\"
			 * id=\"fileInput2_button\" onclick=\"return
			 * showFile('fileInput2')\" style=\"display:inline\" value=\"增加\"></nobr>"); }
			 */
			if (str1($("#fileInput").attr("style")) == "display:none"
					&& str1($("#fileInput1").attr("style")) == "display:none"
					&& str1($("#fileInput2").attr("style")) == "display:none") {
				
                $("#tr_three_td").attr("style", "display:block");
                $("#fileInput2").attr("style", "display:inline");
				$("#fileInput2_button").attr("style", "display:inline");
			}
		} else {
			/*
			 * alert($("#fileInput").attr("style")+"--"+$("#fileInput1").attr("style"));
			 * //三张图片的值都不空，删除成功后调用，不为空的上传第一个框
			 * //if(pohon1!=null&&pohon1!=""&&pohon2!=null&&pohon2!=""&&pohon3!=null&&pohon3!="")
			 * if($("#fileInput").attr("style").replace("
			 * ","")!="display:inline"&&$("#fileInput1").attr("style").replace("
			 * ","")!="display:inline") { alert("else5") //显示第三个上传框组件
			 * $("#fileInput2_null").attr("style","display:inline");
			 * //显示第三个上传框组件
			 * $("#fileInput2_null_button").attr("style","display:inline"); }
			 */
		}
	} else {
	}
}

// 增加上传组件
function showFile(id) {
	debugger;
	if (id == "fileInput") {
		var fileInput1src = $("#fileInput4").attr("src");
		var fileInput2src = $("#fileInput5").attr("src");
		// fileInput1，fileInput2 的src都不为空不能增加fileInput1，fileInput2的上传组件
		if (fileInput1src != null && fileInput1src != ""
				&& fileInput1src != "undefined" && fileInput2src != null
				&& fileInput2src != "" && fileInput2src != "undefined") {
			alert("现场照片最大可以传三张");
		} else {

			// fileInput1，fileInput2 的src都为空就可以增加fileInput1，fileInput2的上传组件，
			if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
					&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
				if ($("#fileInput1").attr("style") == "display:inline"
						&& $("#fileInput2").attr("style") == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#fileInput1").attr("style")) == "display:inline"
							&& str1($("#fileInput2").attr("style")) == "display:none") {
                        $("#tr_three_td").attr("style", "display:block");        
						$("#fileInput2").attr('style', 'display:inline');
					} else {
                        $("#tr_two_td").attr("style", "display:block");
						$("#fileInput1").attr('style', 'display:inline');
					}
				}
			} else {
				if ((fileInput1src != null || fileInput1src != "" || fileInput1src != "undefined")
						&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
					if (str1($("#fileInput2").attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                         $("#tr_three_td").attr("style", "display:block");
						$("#fileInput2").attr('style', 'display:inline');

					}
				} else if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
						&& (fileInput2src != null || fileInput2src != "" || fileInput2src != "undefined")) {
					if (str1($("#fileInput1").attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                         $("#tr_two_td").attr("style", "display:block");
						$("#fileInput1").attr('style', 'display:inline');

					}
				} else {

				}

			}

		}
	} else if (id == "fileInput1") {

		var fileInputsrc = $("#fileInput3").attr("src");
		var fileInput2src = $("#fileInput5").attr("src");
		// fileInput，fileInput2 的src都不为空不能增加fileInput，fileInput2的上传组件
		if (fileInputsrc != null && fileInputsrc != ""
				&& fileInputsrc != "undefined" && fileInput2src != null
				&& fileInput2src != "" && fileInput2src != "undefined") {
			alert("现场照片最大可以传三张");
		} else {
			// fileInput，fileInput2
			// 的src都为空就可以增加fileInput，fileInput2的上传组件，三个图片都删除完成后的添加上传组件
			if ((fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")
					&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
				if ($("#fileInput").attr("style") == "display:inline"
						&& $("#fileInput2").attr("style") == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#fileInput").attr("style")) == "display:inline"
							&& str1($("#fileInput2").attr("style")) == "display:none") {
						 $("#tr_three_td").attr("style", "display:block");
                        $("#fileInput2").attr('style', 'display:inline');
					} else {
                        $("#tr_one_td").attr("style", "display:block");    
						$("#fileInput").attr('style', 'display:inline');
						$("#fileInput_button").attr('style', 'display:inline');
						$("#fileInput1_button").attr('style', 'display:none');
					}
				}
			} else {
				if ((fileInputsrc != null || fileInputsrc != "" || fileInputsrc != "undefined")
						&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
					if (str1($("#fileInput2").attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                         $("#tr_three_td").attr("style", "display:block");
						$("#fileInput2").attr('style', 'display:inline');

					}
				} else if ((fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")
						&& (fileInput2src != null || fileInput2src != "" || fileInput2src != "undefined")) {
					if (str1($("#fileInput").attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                        $("#tr_one_td").attr("style", "display:block");
						$("#fileInput").attr('style', 'display:inline');
						$("#fileInput_button").attr('style', 'display:inline');
						$("#fileInput1_button").attr('style', 'display:none');

					}
				} else {

				}
			}

		}

	} else if (id == "fileInput2") {
		var fileInputsrc = $("#fileInput3").attr("src");
		var fileInput1src = $("#fileInput4").attr("src");
		// fileInput1，fileInput 的src都不为空不能增加fileInput1，fileInput0的上传组件
		if (fileInput1src != null && fileInput1src != ""
				&& fileInput1src != "undefined" && fileInputsrc != null
				&& fileInputsrc != "" && fileInputsrc != "undefined") {
			alert("现场照片最大可以传三张");
		} else {

			// fileInput1，fileInput 的src都为空就可以增加fileInput1，fileInput的上传组件，
			if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
					&& (fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")) {
				if ($("#fileInput1").attr("style") == "display:inline"
						&& $("#fileInput").attr("style") == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#fileInput").attr("style")) == "display:inline"
							&& str1($("#fileInput1").attr("style")) == "display:none") {
						$("#tr_two_td").attr("style", "display:block");
                        $("#fileInput1").attr('style', 'display:inline');
						$("#fileInput1_button").attr('style', 'display:inline');
						$("#fileInput2_button").attr('style', 'display:none');
					} else {
                        $("#tr_one_td").attr("style", "display:block");
						$("#fileInput").attr('style', 'display:inline');
						$("#fileInput_button").attr('style', 'display:inline');
						$("#fileInput2_button").attr('style', 'display:none');
					}
				}
			} else {
				if ((fileInput1src != null || fileInput1src != "" || fileInput1src != "undefined")
						&& (fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")) {
					if (str1($("#fileInput").attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                        $("#tr_one_td").attr("style", "display:block");
						$("#fileInput").attr('style', 'display:inline');
						$("#fileInput_button").attr('style', 'display:inline');
						$("#fileInput2_button").attr('style', 'display:none');
					}
				} else if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
						&& (fileInputsrc != null || fileInputsrc != "" || fileInputsrc != "undefined")) {
					if (str1($("#fileInput1").attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                        $("#tr_two_td").attr("style", "display:block");
						$("#fileInput1").attr('style', 'display:inline');
						$("#fileInput1_button").attr('style', 'display:inline');
						$("#fileInput2_button").attr('style', 'display:none');
					}
				} else {

				}
			}

		}
	} else {
	}

}
/**
 * 修改删除图片 id图片input sctp 图片删除链接id pohon1 图片1的值 pohon2 图片2的值 pohon3 图片3的值
 */
function del_null_ByImg(id, sctp, pohon1, pohon2, pohon3) {
	debugger;
	$("#" + id).attr("src", "");
	$("#" + id).attr("style", "display:none");
	$("#" + sctp).attr("style", "display:none");

	// $("#"+shhz).attr("style","display:none");
	// window.location.reload();
	if (id == "fileInput3") {
		// 三张图片的值都不空，删除成功后调用，不为空的上传第一个框
		if (pohon1 != null && pohon1 != "" && pohon2 != null && pohon2 != ""
				&& pohon3 != null && pohon3 != "") {

			if ($("#tr_two_td").val() != "" || $("#tr_two_td").html() != ""
					|| $("#tr_three_td").val() != ""
					|| $("#tr_three_td").html() != "") {
			} else {
				$("#tr_one_td")
						.html("<nobr><input class=\"input-file uniform_on\" style=\"display:inline\" name=\"file1\"  id=\"fileInput\" type=\"file\" onfocus=\"retrun upload()\" onclick=\"retrun upload()\"><input type=\"button\" id=\"fileInput_button\" onclick=\"return showFile('fileInput')\" style=\"display:inline\" value=\"增加\"></nobr>");
			}
		} else {
			alert($("#fileInput1").attr("style").replace(" ", "") + "--"
					+ $("#fileInput2").attr("style").replace(" ", ""));
			// 判断是否有显示的上传组件
			if ($("#fileInput1").attr("style").replace(" ", "") != "display:inline"
					&& $("#fileInput2").attr("style").replace(" ", "") != "display:inline") {
				// 显示第一个上传框组件
				$("#fileInput_null").attr("style", "display:inline");
				// 显示第一个上传框组件
				$("#fileInput_null_button").attr("style", "display:inline");
			}
		}

	} else if (id == "fileInput4") {
		// alert($("#fileInput").attr("style").replace("
		// ","")+"--"+$("#fileInput2").attr("style").replace(" ",""));
		// 三张图片的值都不空，删除成功后调用，不为空的上传第一个框
		if (pohon1 != null && pohon1 != "" && pohon2 != null && pohon2 != ""
				&& pohon3 != null && pohon3 != "") {
			// 判断是否有显示的上传组件
			// if($("#fileInput").attr("style").replace("
			// ","")!="display:inline"&&$("#fileInput2").attr("style").replace("
			// ","")!="display:inline")
			// {
			// 显示第二个上传框组件
			// $("#fileInput1").attr("style","display:inline");
			// 显示第二个上传框组件
			// $("#fileInput1_button").attr("style","display:inline");

			// }
			// else{}
			// alert($("#tr_one_td").val()+"--"+$("#tr_one_td").html())
			// alert($("#fileInput").val()+$("#fileInput").html());
			if ($("#tr_one_td").val() != "" || $("#tr_one_td").html() != ""
					|| $("#tr_three_td").val() != ""
					|| $("#tr_three_td").html() != "") {
			} else {
				$("#tr_two_td")
						.html("<nobr><input class=\"input-file uniform_on\" style=\"display:inline\" name=\"file2\"  id=\"fileInput1\" type=\"file\" ><input type=\"button\" id=\"fileInput1_button\" onclick=\"return showFile('fileInput1')\" style=\"display:inline\" value=\"增加\"></nobr>");
			}
		} else {

			// alert("else")
			// alert($("#fileInput").attr("style").replace("
			// ","")+"--"+$("#fileInput2").attr("style").replace(" ",""));
			// 三张图片的值都不空，删除成功后调用，不为空的上传第一个框
			// if(pohon1!=null&&pohon1!=""&&pohon2!=null&&pohon2!=""&&pohon3!=null&&pohon3!="")
			if ($("#fileInput").attr("style").replace(" ", "") != "display:inline"
					&& $("#fileInput2").attr("style").replace(" ", "") != "display:inline") {
				// 显示第二个上传框组件
				$("#fileInput1_null").attr("style", "display:inline");
				// 显示第二个上传框组件
				$("#fileInput1_null_button").attr("style", "display:inline");

			}

		}
	} else if (id == "fileInput5") {
		// 三张图片的值都不空，删除成功后调用，不为空的上传第一个框
		if (pohon1 != null && pohon1 != "" && pohon2 != null && pohon2 != ""
				&& pohon3 != null && pohon3 != "") {
			// alert("#fileInput--"+$("#fileInput").attr("style").replace("
			// ","")+"--#fileInput1--"+$("#fileInput1").attr("style").replace("
			// ",""));
			// if($("#fileInput").attr("style").replace("
			// ","")!="display:inline"&&$("#fileInput1").attr("style").replace("
			// ","")!="display:inline")
			// {
			// 显示第三个上传框组件
			// $("#fileInput2").attr("style","display:inline");
			// 显示第三个上传框组件
			// $("#fileInput2_button").attr("style","display:inline");

			// }
			// else{
			// }
			if ($("#tr_one_td").val() != "" || $("#tr_one_td").html() != ""
					|| $("#tr_two_td").val() != ""
					|| $("#tr_two_td").html() != "") {
			} else {
				$("#tr_three_td")
						.html("<nobr><input class=\"input-file uniform_on\" style=\"display:inline\" name=\"file3\"    id=\"fileInput2\" type=\"file\" ><input type=\"button\" id=\"fileInput2_button\" onclick=\"return showFile('fileInput2')\" style=\"display:inline\" value=\"增加\"></nobr>");
			}
		} else {
			// alert($("#fileInput").attr("style")+"--"+$("#fileInput1").attr("style"));
			// 三张图片的值都不空，删除成功后调用，不为空的上传第一个框
			// if(pohon1!=null&&pohon1!=""&&pohon2!=null&&pohon2!=""&&pohon3!=null&&pohon3!="")
			if ($("#fileInput").attr("style").replace(" ", "") != "display:inline"
					&& $("#fileInput1").attr("style").replace(" ", "") != "display:inline") {
				// alert("else5")
				// 显示第三个上传框组件
				$("#fileInput2_null").attr("style", "display:inline");
				// 显示第三个上传框组件
				$("#fileInput2_null_button").attr("style", "display:inline");
			}
		}
	} else {
	}
}


function str1(s) {
	if (s.indexOf(" ") != -1) {
		s = s.replace(" ", "");
	}
	if (s.indexOf(";") != -1) {
		s = s.replace(";", "");
	}
	return s;
}

// 修改上传组件
function oneTableUpload(table_id, td_id, id) {
    debugger;
	if (id == "one_table_fileInput_photo1_null") {
		// 获取上传图片的路径
		var fileInput1src = $("#fileInput4").attr("src");
		var fileInput2src = $("#fileInput5").attr("src");
		// alert(fileInput1src + "---fileInput1src");
		// alert(fileInput2src + "---fileInput2src");
		if (fileInput1src != null && fileInput1src != ""
				&& fileInput1src != "undefined" && fileInput2src != null
				&& fileInput2src != "" && fileInput2src != "undefined") {
			alert("现场照片最大可以传三张");
		} else {

			if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
					&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {

				if (str1($("#one_table_fileInput1_photo2_null").attr("style")) == "display:inline"
						&& str1($("#one_table_fileInput2_photo3_null").attr("style")) == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#one_table_fileInput1_photo2_null")
							.attr("style")) == "display:inline"
							&& str1($("#one_table_fileInput2_photo3_null")
									.attr("style")) == "display:none") {
                        $("#upload_one_table_three_tr_td").attr('style',
                                'display:block');                
						$("#one_table_fileInput2_photo3_null").attr('style',
								'display:inline');
					} else {
						$("#upload_one_table_two_tr_td").attr('style',
								'display:block');
						$("#one_table_fileInput1_photo2_null").attr('style',
								'display:inline');
					}

				}
			} else {
				if ((fileInput1src != null || fileInput1src != "" || fileInput1src != "undefined")
						&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
					if (str1($("#one_table_fileInput2_photo3_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
						$("#upload_one_table_three_tr_td").attr('style',
								'display:block');
						$("#one_table_fileInput2_photo3_null").attr('style',
								'display:inline');

					}
				} else if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
						&& (fileInput2src != null || fileInput2src != "" || fileInput2src != "undefined")) {
					if (str1($("#one_table_fileInput1_photo2_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
						alert(47);
						$("#upload_one_table_two_tr_td").attr('style',
								'display:block');
						$("#one_table_fileInput1_photo2_null").attr('style',
								'display:inline');

					}
				} else {
				}

			}
		}
	} else if (id == "one_table_fileInput1_photo2_null") {
		var fileInputsrc = $("#fileInput3").attr("src");
		var fileInput2src = $("#fileInput5").attr("src");
		// fileInput，fileInput2 的src都不为空不能增加fileInput，fileInput2的上传组件
		if (fileInputsrc != null && fileInputsrc != "" && fileInput2src != null
				&& fileInput2src != "") {
			alert("现场照片最大可以传三张");
		} else {
			// fileInput，fileInput2
			// 的src都为空就可以增加fileInput，fileInput2的上传组件，三个图片都删除完成后的添加上传组件
			if ((fileInputsrc == null || fileInputsrc == "")
					&& (fileInput2src == null || fileInput2src == "")) {
				if (($("#upload_one_td").val() != "" || $("#upload_one_td")
						.html() != "")
						&& ($("#upload_three_td").val() != "" || $("#upload_three_td")
								.html() != "")) {
					alert("现场照片最大可以传三张");
				} else {
					if ($("#upload_one_td").val() != ""
							|| $("#upload_one_td").html() != "") {
						$("#upload_three_td")
								.html("<input class=\"input-file uniform_on\" style=\"display:inline\" name=\"file3\"    id=\"fileInput2_null\" type=\"file\" ><input type=\"button\" id=\"fileInput2_null_button\" onclick=\"return showFile_null('fileInput2_null')\" style=\"display:none\" value=\"增加\">");
					} else {
						$("#fileInput1_null_button").attr("style",
								"display:none");
						$("#upload_one_td")
								.html("<input class=\"input-file uniform_on\" style=\"display:inline\" name=\"file1\"  id=\"fileInput_null\" type=\"file\" onfocus=\"retrun upload()\" onclick=\"retrun upload()\"><input type=\"button\" id=\"fileInput_null_button\" onclick=\"return showFile_null('fileInput_null')\" style=\"display:inline\" value=\"增加\">");

					}
				}
			} else {

				// fileInput 的src不为空就可以增加fileInput2的上传组件
				if (fileInputsrc != null && fileInputsrc != "") {

				} else {
                    $("#upload_one_table_one_tr_td").attr('style',
                                'display:block');
					$("#one_table_fileInput_photo_null").attr("style",
							"display:inline");
					$("#one_table_fileInput_photo_null_button").attr("style",
							"display:inline");
					$("#one_table_fileInput1_photo2_null_button").attr("style",
							"display:none");
				}
				// fileInput2 的src不为空就可以增加fileInput的上传组件
				if (fileInput2src != null && fileInput2src != "") {
				} else {
                    $("#upload_one_table_three_tr_td").attr('style',
                                'display:block');
					$("#one_table_fileInput2_photo3_null").attr("style",
							"display:inline");
				}
			}

		}
	} else if (id == "one_table_fileInput2_photo3_null") {
		var fileInputsrc = $("#fileInput3").attr("src");
		var fileInput1src = $("#fileInput4").attr("src");
		// fileInput1，fileInput 的src都不为空不能增加fileInput1，fileInput0的上传组件
		if (fileInput1src != null && fileInput1src != ""
				&& fileInputsrc != null && fileInputsrc != "") {
			alert("现场照片最大可以传三张");
		} else {

			// fileInput1，fileInput 的src都为空就可以增加fileInput1，fileInput的上传组件，
			if ((fileInput1src == null || fileInput1src == "")
					&& (fileInputsrc == null || fileInputsrc == "")) {
				if (($("#upload_one_td").val() != "" || $("#upload_one_td")
						.html() != "")
						&& ($("#upload_two_td").val() != "" || $("#upload_two_td")
								.html() != "")) {
					alert("现场照片最大可以传三张");
				} else {

					if ($("#upload_one_td").val() != ""
							|| $("#upload_one_td").html() != "") {
						$("#upload_two_td")
								.html("<input class=\"input-file uniform_on\" style=\"display:inline\" name=\"file2\"  id=\"fileInput1_null\" type=\"file\" ><input type=\"button\" id=\"fileInput1_null_button\" onclick=\"return showFile_null('fileInput1_null')\" style=\"display:inline\" value=\"增加\">");
						$("#fileInput2_null_button").attr("style",
								"display:none");
					} else {
						$("#upload_one_td")
								.html("<input class=\"input-file uniform_on\" style=\"display:inline\" name=\"file1\"  id=\"fileInput_null\" type=\"file\" onfocus=\"retrun upload()\" onclick=\"retrun upload()\"><input type=\"button\" id=\"fileInput_null_button\" onclick=\"return showFile_null('fileInput_null')\" style=\"display:inline\" value=\"增加\">");
						$("#fileInput2_null_button").attr("style",
								"display:none");
					}
				}
			} else {

				// fileInput1 的src不为空就可以增加fileInput的上传组件
				if (fileInput1src != null && fileInput1src != "") {

				} else {
                    $("#upload_one_table_two_tr_td").attr('style',
                                'display:block');
					$("#one_table_fileInput1_photo2_null").attr("style",
							"display:inline");
					$("#one_table_fileInput1_photo2_null_button").attr("style",
							"display:inline");
					$("#one_table_fileInput2_photo3_null_button").attr("style",
							"display:none");
				}
				// fileInput 的src不为空就可以增加fileInput1的上传组件
				if (fileInputsrc != null && fileInputsrc != "") {

				} else {
                    $("#upload_one_table_one_tr_td").attr('style',
                                'display:block');
					$("#one_table_fileInput_photo1_null").attr("style",
							"display:inline");
					$("#one_table_fileInput_photo1_null_button").attr("style",
							"display:inline");
					$("#one_table_fileInput2_photo3_null_button").attr("style",
							"display:none");
				}
			}

		}
	} else {
	}

}
function twoTableUpload(table_id, td_id, id) {

	if (id == "two_table_fileInput_photo1_null") {

		var fileInput1src = $("#fileInput4").attr("src");
		var fileInput2src = $("#fileInput5").attr("src");
		if (fileInput1src != null && fileInput1src != ""
				&& fileInput1src != "undefined" && fileInput2src != null
				&& fileInput2src != "" && fileInput2src != "undefined") {
			alert("现场照片最大可以传三张");
		} else {

			if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
					&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {

				if ($("#two_table_fileInput1_photo2_null").attr("style") == "display:inline"
						&& $("#two_table_fileInput2_photo3_null").attr("style") == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#two_table_fileInput1_photo2_null")
							.attr("style")) == "display:inline"
							&& str1($("#two_table_fileInput2_photo3_null")
									.attr("style")) == "display:none") {

						$("#upload_two_table_three_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput2_photo3_null").attr('style',
								'display:inline');
					} else {
						$("#upload_two_table_two_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput1_photo2_null").attr('style',
								'display:inline');
					}

				}
			} else {
				if ((fileInput1src != null || fileInput1src != "" || fileInput1src != "undefined")
						&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
					if (str1($("#two_table_fileInput2_photo3_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
						$("#upload_two_table_three_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput2_photo3_null").attr('style',
								'display:inline');

					}
				} else if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
						&& (fileInput2src != null || fileInput2src != "" || fileInput2src != "undefined")) {
					if (str1($("#two_table_fileInput1_photo2_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
						$("#upload_two_table_two_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput1_photo2_null").attr('style',
								'display:inline');

					}
				} else {

				}
			}
		}

	} else if (id == "two_table_fileInput1_photo2_null") {
		var fileInputsrc = $("#fileInput3").attr("src");
		var fileInput2src = $("#fileInput5").attr("src");
		// fileInput，fileInput2
		// 的src都不为空不能增加fileInput，fileInput2的上传组件
		if (fileInputsrc != null && fileInputsrc != ""
				&& fileInputsrc != "undefined" && fileInput2src != null
				&& fileInput2src != "" && fileInput2src != "undefined") {
			alert("现场照片最大可以传三张");
		} else {
			// fileInput，fileInput2
			// 的src都为空就可以增加fileInput，fileInput2的上传组件，三个图片都删除完成后的添加上传组件
			if ((fileInputsrc == null || fileInputsrc == ""
					&& fileInputsrc != "undefined")
					&& (fileInput2src == null || fileInput2src == ""
							&& fileInput2src != "undefined")) {

				if ($("#two_table_fileInput_photo1_null").attr("style") == "display:inline"
						&& $("#one_table_fileInput2_photo3_null").attr("style") == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#two_table_fileInput_photo1_null")
							.attr("style")) == "display:inline"
							&& str1($("#one_table_fileInput2_photo3_null")
									.attr("style")) == "display:none") {
						$("#upload_two_table_three_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput2_photo3_null").attr('style',
								'display:inline');
					} else {
						$("#upload_two_table_one_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput_photo1_null").attr('style',
								'display:inline');
						$("#two_table_fileInput_photo1_null_button").attr(
								'style', 'display:inline');
						$("#two_table_fileInput1_photo2_null_button").attr(
								'style', 'display:none');
					}

				}
			} else {
				if ((fileInputsrc != null || fileInputsrc != "" || fileInputsrc != "undefined")
						&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
					if (str1($("#two_table_fileInput2_photo3_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {

						$("#upload_two_table_three_tr_td").attr('style',
								'display:inline');
						$("#two_table_fileInput2_photo3_null").attr('style',
								'display:inline');

					}
				} else if ((fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")
						&& (fileInput2src != null || fileInput2src != "" || fileInput2src != "undefined")) {
					if (str1($("#two_table_fileInput_photo1_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                        $("#upload_two_table_one_tr_td").attr('style',
                                'display:block');
						$("#two_table_fileInput_photo1_null").attr('style',
								'display:inline');
						$("#two_table_fileInput_photo1_null_button").attr(
								'style', 'display:inline');
						$("#two_table_fileInput1_photo2_null_button").attr(
								'style', 'display:none');

					}
				} else {

				}
			}

		}
	} else if (id == "two_table_fileInput2_photo3_null") {

		var fileInputsrc = $("#fileInput3").attr("src");
		var fileInput1src = $("#fileInput4").attr("src");
		// fileInput1，fileInput
		// 的src都不为空不能增加fileInput1，fileInput0的上传组件
		if (fileInput1src != null && fileInput1src != ""
				&& fileInput1src != "undefined" && fileInputsrc != null
				&& fileInputsrc != "" && fileInputsrc != "undefined") {
			alert("现场照片最大可以传三张");
		} else {

			// 的src都为空就可以增加fileInput1，fileInput的上传组件，
			if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
					&& (fileInputsrc == null || fileInputsrc == "" || fileInput1src == "undefined")) {

				if ($("#two_table_fileInput_photo1_null").attr("style") == "display:inline"
						&& $("#two_table_fileInput1_photo2_null").attr("style") == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#two_table_fileInput1_photo2_null")
							.attr("style")) == "display:inline"
							&& str1($("#one_table_fileInput2_photo3_null")
									.attr("style")) == "display:none") {
						$("#upload_two_table_three_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput2_photo3_null").attr('style',
								'display:inline');
					} else {
						$("#upload_two_table_two_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput1_photo2_null").attr('style',
								'display:inline');
					}
				}
			} else {
				if ((fileInput1src != null || fileInput1src != "" || fileInput1src != "undefined")
						&& (fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")) {
					if (str1($("#two_table_fileInput_photo1_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
						$("#upload_two_table_one_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput_photo1_null").attr('style',
								'display:inline');

					}
				} else if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
						&& (fileInputsrc != null || fileInputsrc != "" || fileInputsrc != "undefined")) {
					if (str1($("#two_table_fileInput1_photo2_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
						$("#upload_two_table_two_tr_td").attr('style',
								'display:block');
						$("#two_table_fileInput1_photo2_null").attr('style',
								'display:inline');

					}
				} else {

				}
			}

		}

	} else {
	}

}
function threeTableUpload(table_id, td_id, id) {
    debugger;
	if (id == "three_table_fileInput_photo1_null") {

		var fileInput1src = $("#fileInput4").attr("src");
		var fileInput2src = $("#fileInput5").attr("src");
		if (fileInput1src != null && fileInput1src != ""
				&& fileInput1src != "undefined" && fileInput2src != null
				&& fileInput2src != "" && fileInput2src != "undefined") {
			alert("现场照片最大可以传三张");
		} else {

			if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
					&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
				if ($("#three_table_fileInput1_photo2_null").attr("style") == "display:inline"
						&& $("#three_table_fileInput2_photo3_null")
								.attr("style") == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#three_table_fileInput1_photo2_null")
							.attr("style")) == "display:inline"
							&& str1($("#three_table_fileInput2_photo3_null")
									.attr("style")) == "display:none") {
						$("#upload_three_table_three_tr_td").attr('style',
								'display:block');
						$("#three_table_fileInput2_photo3_null").attr('style',
								'display:inline');
					} else {
						$("#upload_three_table_two_tr_td").attr('style',
								'display:block');
						$("#three_table_fileInput1_photo2_null").attr('style',
								'display:inline');
					}
				}
			} else {
				if ((fileInput1src != null || fileInput1src != "" || fileInput1src != "undefined")
						&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
					if (str1($("#three_table_fileInput2_photo3_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
						$("#upload_three_table_three_tr_td").attr('style',
								'display:block');
						$("#three_table_fileInput2_photo3_null").attr('style',
								'display:inline');

					}
				} else if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
						&& (fileInput2src != null || fileInput2src != "" || fileInput2src != "undefined")) {
					if (str1($("#three_table_fileInput1_photo2_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
						$("#upload_three_table_two_tr_td").attr('style',
								'display:block');
						$("#three_table_fileInput1_photo2_null").attr('style',
								'display:inline');

					}
				} else {

				}
			}
		}

	} else if (id == "three_table_fileInput1_photo2_null") {

		var fileInputsrc = $("#fileInput3").attr("src");
		var fileInput2src = $("#fileInput5").attr("src");
		// fileInput，fileInput2
		// 的src都不为空不能增加fileInput，fileInput2的上传组件
		if (fileInputsrc != null && fileInputsrc != ""
				&& fileInputsrc != "undefined" && fileInput2src != null
				&& fileInput2src != "" && fileInput2src != "undefined") {
			alert("现场照片最大可以传三张");
		} else {
			// fileInput，fileInput2
			// 的src都为空就可以增加fileInput，fileInput2的上传组件，三个图片都删除完成后的添加上传组件
			if ((fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")
					&& (fileInput2src == null || fileInput2src == "" || fileInput2src != "undefined")) {
				if ($("#three_table_fileInput_photo1_null").attr("style") == "display:inline"
						&& $("#three_table_fileInput2_photo3_null")
								.attr("style") == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#three_table_fileInput_photo1_null")
							.attr("style")) == "display:inline"
							&& str1($("#three_table_fileInput2_photo3_null")
									.attr("style")) == "display:none") {
						$("#upload_three_table_three_tr_td").attr('style',
								'display:block');
						$("#three_table_fileInput2_photo3_null").attr('style',
								'display:inline');
					} else {
                        $("#upload_three_table_one_tr_td").attr('style',
                                'display:block');
						$("#three_table_fileInput_photo1_null").attr('style',
								'display:inline');
						$("#three_table_fileInput_photo1_null_button").attr(
								'style', 'display:inline');
						$("#three_table_fileInput1_photo2_null_button").attr(
								'style', 'display:none');
					}
				}
			} else {
				if ((fileInputsrc != null || fileInputsrc != "" || fileInputsrc != "undefined")
						&& (fileInput2src == null || fileInput2src == "" || fileInput2src == "undefined")) {
					// alert($("#three_table_fileInput2_photo3_null")
					// .attr("style"));
					if (str1($("#three_table_fileInput2_photo3_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
						$("#upload_three_table_three_tr_td").attr('style',
								'display:block');
						$("#three_table_fileInput2_photo3_null").attr('style',
								'display:inline');

					}
				} else if ((fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")
						&& (fileInput2src != null || fileInput2src != "" || fileInput2src != "undefined")) {
					if (str1($("#three_table_fileInput_photo1_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                        $("#upload_three_table_one_tr_td").attr('style',
                                'display:block');
						$("#three_table_fileInput_photo1_null").attr('style',
								'display:inline');
						$("#three_table_fileInput_photo1_null_button").attr(
								'style', 'display:inline');
						$("#three_table_fileInput1_photo2_null_button").attr(
								'style', 'display:none');

					}
				} else {

				}
			}

		}

	} else if (id == "three_table_fileInput2_photo3_null") {

		var fileInputsrc = $("#fileInput3").attr("src");
		var fileInput1src = $("#fileInput4").attr("src");
		// fileInput1，fileInput
		// 的src都不为空不能增加fileInput1，fileInput0的上传组件
		if (fileInput1src != null && fileInput1src != ""
				&& fileInput1src != "undefined" && fileInputsrc != null
				&& fileInputsrc != "" && fileInputsrc != "undefined") {
			alert("现场照片最大可以传三张");
		} else {

			// fileInput1，fileInput
			// 的src都为空就可以增加fileInput1，fileInput的上传组件，
			if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
					&& (fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")) {
				if ($("#three_table_fileInput_photo1_null").attr("style") == "display:inline"
						&& $("#three_table_fileInput1_photo2_null")
								.attr("style") == "display:inline") {
					alert("现场照片最大可以传三张");
				} else {
					if (str1($("#three_table_fileInput_photo1_null")
							.attr("style")) == "display:inline"
							&& str1($("#three_table_fileInput1_photo2_null")
									.attr("style")) == "display:none") {
                        $("#upload_three_table_two_tr_td").attr('style',
                                'display:block');
						$("#three_table_fileInput1_photo2_null").attr('style',
								'display:inline');
						$("#three_table_fileInput1_photo2_null_button").attr(
								'style', 'display:inline');
						$("#three_table_fileInput2_photo3_null_button").attr(
								'style', 'display:none');
					} else {
                        $("#upload_three_table_one_tr_td").attr('style',
                                'display:block');
						$("#three_table_fileInput_photo1_null").attr('style',
								'display:inline');
						$("#three_table_fileInput_photo1_null_button").attr(
								'style', 'display:inline');
						$("#three_table_fileInput2_photo3_null_button").attr(
								'style', 'display:none');
					}
				}
			} else {
				if ((fileInput1src != null || fileInput1src != "" || fileInput1src != "undefined")
						&& (fileInputsrc == null || fileInputsrc == "" || fileInputsrc == "undefined")) {
					if (str1($("#three_table_fileInput_photo1_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                        $("#upload_three_table_one_tr_td").attr('style',
                                'display:block');
						$("#three_table_fileInput_photo1_null").attr('style',
								'display:inline');
						$("#three_table_fileInput_photo1_null_button").attr(
								'style', 'display:inline');
						$("#three_table_fileInput2_photo3_null_button").attr(
								'style', 'display:none');

					}
				} else if ((fileInput1src == null || fileInput1src == "" || fileInput1src == "undefined")
						&& (fileInputsrc != null || fileInputsrc != "" || fileInputsrc != "undefined")) {
					if (str1($("#three_table_fileInput1_photo2_null")
							.attr("style")) == "display:inline") {
						alert("现场照片最大可以传三张");
					} else {
                        $("#upload_three_table_two_tr_td").attr('style',
                                'display:block');
						$("#three_table_fileInput1_photo2_null").attr('style',
								'display:inline');

						$("#three_table_fileInput1_photo2_null_button").attr(
								'style', 'display:inline');
						$("#three_table_fileInput2_photo3_null_button").attr(
								'style', 'display:none');

					}
				} else {

				}
			}

		}

	} else {
        
	}

}

// 修改上传组件
/**
 * @param table_id
 *            tableid
 * @param td_id
 *            tdid<b>
 * @param id
 *            input id
 */
function showFile_null(table_id, td_id, id) {
    debugger;
	if (table_id == "upload_one_table") {
		oneTableUpload(table_id, td_id, id);
	} else if (table_id == "upload_two_table") {
		twoTableUpload(table_id, td_id, id);
	} else if (table_id == "upload_three_table") {
		threeTableUpload(table_id, td_id, id);
	} else {
	}
}

function submitForm(form) {

	$("#hid_photo1").val($("#fileInput3").attr("src"));
	$("#hid_photo2").val($("#fileInput4").attr("src"));
	$("#hid_photo3").val($("#fileInput5").attr("src"));

	/*
	 * var fileInput =$("#fileInput").attr("src");//上传文件第一个框 var fileInput1
	 * =$("#fileInput1").attr("src");//上传文件第二个框 var fileInput2
	 * =$("#fileInput2").attr("src");//上传文件第三个框 var fileInput3
	 * =$("#fileInput3").attr("src");//显示图片第一个img var fileInput4
	 * =$("#fileInput4").attr("src");//显示图片第二个img var fileInput5
	 * =$("#fileInput5").attr("src");//显示图片第三个img
	 */
}

function tijiao() {
	// debugger;
	// alert(document.forms[0].method);
	$("#hid_photo1").val($("#fileInput3").attr("src"));
	$("#hid_photo2").val($("#fileInput4").attr("src"));
	$("#hid_photo3").val($("#fileInput5").attr("src"));
	/*
	 * var from =document.getElementById("inputForm"); debugger;
	 * alert($("#inputForm").find(":file"));
	 * alert($("#inputForm").find(":file").length);
	 * if($("#inputForm").find(":file").length==0){
	 * 
	 * var from =document.getElementById("inputForm"); debugger;
	 * from.method="post"; from.action="${root}/device/deviceinfo/doUpdate";
	 * //action="${root}/device/deviceinfo/doUpdate" form.submit();
	 * 
	 *  } else{ var from =document.getElementById("inputForm");
	 * from.method="post";
	 * //from.action="${root}/device/deviceinfo/doUpdateImg";
	 * from.action="${root}/device/deviceinfo/doImg"; alert(from.action);
	 * form.submit(); }
	 */
}

function uploadDisplay() {
	debugger;
	if ('${deviceInfo.photo1}' == '' || '${deviceInfo.photo1}' == null) {
		if ('${deviceInfo.photo2}' == '' || '${deviceInfo.photo2}' == null) {
			$("#two_table_fileInput1_photo2_null")
					.attr("style", "display:none");
			$("#upload_two_table_two_tr_td").attr("style", "display:none");
			$("#upload_two_table_two_tr").attr("style", "display:none");
		} else {
			if ('${deviceInfo.photo3}' == '' || '${deviceInfo.photo3}' == null) {
				$("#three_table_fileInput2_photo3_null").attr("style",
						"display:none");
				$("#upload_three_table_three_tr").attr("style", "display:none");
				$("#upload_three_table_three_tr_td").attr("style",
						"display:none");
			}
		}
	} else {
		if ('${deviceInfo.photo2}' == '' || '${deviceInfo.photo2}' == null) {
			$("#three_table_fileInput2_photo3_null").attr("style",
					"display:none");
			$("#upload_three_table_three_tr").attr("style", "display:none");
			$("#upload_three_table_three_tr_td").attr("style", "display:none");
		} else {
			if ('${deviceInfo.photo3}' == '' || '${deviceInfo.photo3}' == null) {

			}
		}
	}

}