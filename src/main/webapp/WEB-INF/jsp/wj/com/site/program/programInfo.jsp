<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>


<script type="text/javascript">



	$(document).ready(function(){

       alert("dd");


	});



</script>
카테고리 리스트 </br>

<c:forEach items="${categoryList}" var="item" varStatus="status">
    카테고리명:${item.programNm}
    순번:${item.programCnt}
    카테고리SNO:${item.programSno} </br>
</c:forEach>



</br></br>프로그램 소개 </br>
기간:${view.programDay}</br>
시간:${view.programStrTime}~${view.programEndTime}</br>
장소:${view.programPlace}</br>
프로그램명:${view.programNm}</br>



</br></br>포럼리스트 </br>
<c:forEach items="${forumList}" var="item" varStatus="status">
    리더여부:${item.leaderYn}
    컨텐츠타이틀:${item.contTitle}
    메인타이틀:${item.mainTitle}
    이름:${item.mainName}
    정보:${item.mainInfo}
    기타:${item.mainEtc}</br>

</c:forEach>

</br></br>이미지 리스트 </br>
<c:forEach items="${fileLsit}" var="item" varStatus="status">
    FILE_SN:${item.fileSn}
    ORIGNL_FILE_NM:${item.orignlFileNm}
    FILE_EXTSN:${item.fileExtsn}


</c:forEach>



<%--LANG_CD	en--%>
<%--LANG_CD	ja--%>
<%--LANG_CD	ko--%>
<%--LANG_CD	zh--%>



<%--</br></br></br>--%>
<%--주요추진상황${view.festivitySituation}</br></br></br>--%>



<%--https://gsis.kwdi.re.kr/statHtml/statHtml.do?mode=tab&orgId=338&tblId=DT_13204_0003&vw_cd=null&list_id=null&scrId=&seqNo=&language=ko&obj_var_id=undefined&itm_id=undefined&conn_path=null&path=--%>


