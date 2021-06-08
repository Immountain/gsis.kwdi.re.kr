<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<script type="text/javascript">

    var gridList = [];

    var firstGrid = new ax5.ui.grid();

    $(document).ready(function () {
        // 그리드세팅
        firstGrid.setConfig({
                target: $('[data-ax5grid="first-grid"]'),
                sortable: true,
                showLineNumber: true,
                lineNumberColumnWidth: 40,

                header: {
                    align: "center",
                    columnHeight: 40
                },
                body: {
                    align: "center",
                    columnHeight: 28,
                    onClick: function () {
                    },
                    onDBLClick: function () {
                    }
                },
                columns: [
                    {key: "mailSendId", label: "mailSendId", width: 100, hidden: true},
                    {key: "mailTo", label: "수신인", width: 150},
                    {key: "mailCc", label: "참조인", width: 150},
                    {key: "mailFrom", label: "발송인", width: 150},
                    {key: "mailSubject", label: "제목", width: 150},
                    {key: "mailContent", label: "내용", width: 150},
                    {key: "mailSendDt", label: "발송시간", width: 150},
                    {key: "mailSendType", label: "발송여부", width: 150},
                    {key: "mailErrorMsg", label: "오류내용", width: 150}
                ]
            }
        );

        $('#btn_search').click(function () {
            Search();
        });
        Search();
    });

    // 조회
    function Search() {
        var searchCondition = $("#searchCondition option:selected").val();
        var searchKeyword = $("#searchKeyword").val();

        var p = {
            searchCondition: searchCondition,
            searchKeyword: searchKeyword,
        };

        $ifx.ajax('<c:url value='/cms/info/mail/selectMailSendHistoryObject.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                console.log(res)
                firstGrid.setData(res.list);
            }
        })
    }

    function gridData(row) {
        gridList = row;
    }

    function gridExcelDownLoad() {
        firstGrid.updateColumn($.extend(true, firstGrid.config.columns[0], {hidden: true}), 0);
        firstGrid.exportExcel("User.xls");
        firstGrid.updateColumn($.extend(true, firstGrid.config.columns[0], {hidden: false}), 0);
    }
</script>
<div class="sub subView">
    <nav class="navigation">
        <i class='bx bxs-home'></i>${menuInfo.depthFullname}
    </nav>
    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i>이메일 발송 대상 사용자 <spring:message code="title.list"/>
    </h2>
    <h3 class="btitle">
        검색
    </h3>
    <!-- 검색영역 -->
    <!-- 검색조건선택 -->
    <div class="white-box">
        <div class="rows">
         <span class="select-outline">
                <select name="searchCondition" id="searchCondition"
                        title="<spring:message code="title.searchCondition" />">
                       <option selected value=''><spring:message code="input.select"/></option><!-- 선택하세요 -->
                       <option value="1">아이디</option>
                    <!-- 코드ID -->
                       <option value="2">이름</option>
                    <!-- 코드ID -->
               </select>
         </span>

            <input type="text" class="w100" class="main" name="searchKeyword" id="searchKeyword" size="35"
                   title="<spring:message code="title.search" /> <spring:message code="input.input" />" value=''
                   maxlength="155">
            <button type="button" class="button" name="btn_search" id="btn_search"
                    value="<spring:message code="button.inquire" />"
                    title="<spring:message code="title.inquire" /> <spring:message code="input.button" />"><i
                    class='bx bx-slider-alt'></i><spring:message code="title.inquire"/></button>
        </div>
    </div>
    <h3 class="btitle">
        목록
    </h3>
    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 300px;"></div>
    </div>
    <input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
    <button type="button" class="button main" onclick="gridExcelDownLoad()"><i class="bx bx-slider-alt"></i>엑셀자료 다운로드
    </button>
</div>