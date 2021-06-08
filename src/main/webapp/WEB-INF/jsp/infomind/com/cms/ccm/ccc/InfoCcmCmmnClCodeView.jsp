<%
	/**
	 * @Class Name : EgovCcmCmmnClCodeDetail.jsp
	 * @Description : 공통분류코드 상세조회 화면
	 * @Modification Information
	 * @
	 * @  수정일             수정자                   수정내용
	 * @ -------    --------    ---------------------------
	 * @ 2009.02.01   박정규              최초 생성
	 *   2017.08.03   이정은              표준프레임워크 v3.7 개선
	 *  @author 공통서비스팀
	 *  @since 2009.02.01
	 *  @version 1.0
	 *  @see
	 *
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comSymCcmCcc.cmmnClCodeVO.title"/></c:set>
<script type="text/javascript">



    $(document.body).ready(function () {

        //닫기
        $('#modal-close').click(function () {
            ax5modal.close();
        });

        //수정하기
        $('#btn_modify').click(function () {

            var clCode = $("#clCode").val();
            var p = {
                modType:'modify' ,clCode:clCode
            };


            //gotoModify(clCode);

            ax5modal.callback(p);
        });

        /* ********************************************************
        * 삭제처리
        ******************************************************** */
         $('#btn_delete').click(function () {

            if(confirm("<spring:message code="common.delete.msg" />")){

                var API_SERVER = "<c:url value='/cms/ccm/ccc/RemoveCcmCmmnClCode.do' />";
                var clCode =$('#clCode').val();
                var p ={clCode:clCode};


                $.ajax({
                    url : API_SERVER,
                    type : 'post',
                    data : JSON.stringify(p),
                    dateType:'json',
                    contentType: "application/json",  // ajax 통신으로 보내는 타입

                    beforeSend: function(xhr) {

                        xhr.setRequestHeader("AJAX", "true");

                    },
                    success : function(data) {

                        var jsonObj = JSON.stringify(data);
                        if(data.status=="0"){
                            if(data.message=="SUCCESS"){

                                ax5modal.callback('OK');
                                ax5modal.close();
                                alert("삭제 처리 완료했습니다.");

                            }else{
                                alert(data.message);
                           }
                        }else{

                            alert(data.error.message);

                        }
                    }, // success
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log("data=1=>"+JSON.stringify(XMLHttpRequest));
                        console.log("data=2=>"+XMLHttpRequest);
                        console.log("data=3=>"+XMLHttpRequest.status);
                    }
                });
            }
        });
    });

    <%--//등록, 수정창 오픈--%>
    <%--function gotoModify(clCode) {--%>
        <%--var p = {--%>
            <%--clCode:clCode--%>
        <%--};--%>
        <%--var API_SERVER = "<c:url value='/cms/ccc/InfoCcmCmmnClCodeUpdt.do' />";--%>
        <%--ax5modal.open({--%>
            <%--theme: "primary",--%>
            <%--height: 500,--%>
            <%--width: 600,--%>
            <%--header: {--%>
                <%--title: '공통분류 수정',--%>
                <%--// btns: {--%>
                <%--//     close: {--%>
                <%--//         label: '<i class="fa fa-times-circle" aria-hidden="true"></i>', onClick: function () {--%>
                <%--//             modal.close();--%>
                <%--//         }--%>
                <%--//     }--%>
                <%--// }--%>

            <%--},--%>
            <%--iframe: {--%>
                <%--method: "get",--%>
                <%--url: API_SERVER,--%>
                <%--param:  p--%>

            <%--},--%>

        <%--}, function (d) {--%>


        <%--});--%>

    <%--}--%>


</script>

<div class="sub subView">
    <h2 class="stitle">
        <i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.detail" />
    </h2>
    <form name="CcmClCodeForm"  action="<c:url value='/sym/ccm/ccc/UpdateCcmCmmnClCodeView.do'/>" method="post">


        <h3 class="btitle">
            상세 내역
        </h3>

        <div class="rows white-box">

            <table class="landscape" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">

                <tbody>
                <!-- 분류코드 -->
                <tr>
                    <th style="width:100px"><spring:message code="comSymCcmCcc.cmmnClCodeVO.clCode" /></th>
                    <td class="left"><c:out value="${result.clCode}"/></td>
                </tr>
                <!-- 분류코드명 -->
                <tr>
                    <th><spring:message code="comSymCcmCcc.cmmnClCodeVO.clCodeNm" /></th>
                    <td class="left"><c:out value="${result.clCodeNm}"/></td>
                </tr>
                <!-- 분류코드설명 -->
                <tr>
                    <th class="vtop"><spring:message code="comSymCcmCcc.cmmnClCodeVO.clCodeDc" /></th>
                    <td class="cnt">
                        <c:out value="${fn:replace(result.clCodeDc , crlf , '<br/>')}" escapeXml="false" />
                    </td>
                </tr>
                <!-- 사용여부 -->
                <tr>
                    <th><spring:message code="comSymCcmCcc.cmmnClCodeVO.useAt" /></th>
                    <td class="left"><c:out value="${result.useAt}"/></td>
                </tr>
                </tbody>
            </table>
        </div>

        <!-- 하단 버튼 -->
        <div class="btn-set right">
            <button type="button" name="btn_modify" id="btn_modify" class="button main"  title="<spring:message code="title.update" /> <spring:message code="input.button" />" ><spring:message code="button.update" /></button>
            <button type="button" name="btn_delete" id="btn_delete" class="button" title="<spring:message code="title.delete" /> <spring:message code="input.button" />"><spring:message code="title.delete" /> </button>
            <%--<button type="button" class="button sub" id="modal-close"  /> 닫기</button>--%>
        </div>
        <input name="clCode" id="clCode" type="hidden" value="<c:out value="${result.clCode}" />">

    </form>
</div>

