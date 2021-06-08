<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
 /**
  * @Class Name : EgovMenuMvmn.jsp
  * @Description : 메뉴이동 화면
  * @Modification Information
  * @
  * @ 수정일               수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용               최초 생성
  *   2018.09.10   신용호            표준프레임워크 v3.8 개선
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */
  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/com/sym/mnu/mpm/icon/";
  String imagePath_button = "/images/egovframework/com/sym/mnu/mpm/button/";

%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title><spring:message code="comSymMnuMpm.menuMvmn.title"/></title><!-- 메뉴이동 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
var imgpath = "<c:url value='/images/egovframework/com/cmm/utl/'/>";
</script>
<script language="javascript1.2" type="text/javaScript" src="<c:url value='/js/infomind/com/cms/mnu/mpm/InfoMenuList.js' />" /></script>
<script language="javascript1.2" type="text/javaScript">
<!--
var ax5modal;
(function(){
    if (window.parent && window.parent.ax5modal) {
        ax5modal = window.parent.ax5modal;
        console.log(ax5modal)
    }
})();
function selectProgramListSearch() {
	progrmManageForm.submit();
}
function choisProgramListSearch(vFileNm) {
	eval("parent.document.all."+parent.document.all.tmp_SearchElementName.value).value = vFileNm;
	parent.$('.ui-dialog-content').dialog('close');
}

/* ********************************************************
 * 상세내역조회 함수
 ******************************************************** */
function choiceNodes(nodeNum) {
	var nodeValues = treeNodes[nodeNum].split("|");
	parent.document.menuManageVO.upperMenuId.value = nodeValues[4];


    if(parent.$('.ui-dialog-content').length > 0) {
        parent.$('.ui-dialog-content').dialog('close');
        parent.$('.ui-dialog-content').remove();
    }else if(window['ax5modal']) {
        window['ax5modal'].close();
    }
}
/* ********************************************************
 * 조회 함수
 ******************************************************** */
function selectMenuListTmp() {
	document.menuListForm.req_RetrunPath.value = "<c:url value='/cms/mnu/mpm/infoMenuMvmn'/>";
    document.menuListForm.action = "<c:url value='/cms/mnu/mpm/infoMenuListSelectTmp.do'/>";
    document.menuListForm.submit();
}
-->
</script>
</head>
<body>
<form name="searchUpperMenuIdForm" action ="<c:url value='/cms/mnu/mpm/infoMenuListSelectTmp.do'/>" method="post">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
<input type="hidden" name="req_RetrunPath" value="/cms/mnu/mpm/infoMenuMvmn">
<c:forEach var="result" items="${list_menulist}" varStatus="status" >
<input type="hidden" name="tmp_menuNmVal" value="${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.progrmFileNm}|${result.menuNo}|${result.menuOrdr}|${result.menuNm}|${result.upperMenuId}|${result.menuDc}|${result.relateImagePath}|${result.relateImageNm}|${result.progrmFileNm}|${result.boardId}|">
</c:forEach>

<div class="wTableFrm" style="width:580px">
	<div style="clear:both;"></div>
</div>

<DIV id="main" style="display:">

<table width="570" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="10">&nbsp;</td>
  </tr>
</table>

<table width="570" cellpadding="8" class="table-line">
  <tr>
    <td>
 		<div class="tree" style="width:400px;">
        <script language="javascript" type="text/javaScript">

			var Tree = [];

			if ( typeof document.searchUpperMenuIdForm.req_RetrunPath == "object"
					&& typeof document.searchUpperMenuIdForm.tmp_menuNmVal == "object"
					&& document.searchUpperMenuIdForm.tmp_menuNmVal.length > 0 ) {
				for (var j = 0; j < document.searchUpperMenuIdForm.tmp_menuNmVal.length; j++) {
					Tree[j] = document.searchUpperMenuIdForm.tmp_menuNmVal[j].value;
			    }
				createTree(Tree, true);
            }else{
            	alert("<spring:message code="comSymMnuMpm.menuMvmn.validate.alert.menu"/>");
            	window.close();
            }
           </script>
		</div>
    </td>
  </tr>
</table>
</DIV>

</form>
</body>
</html>

