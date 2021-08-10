<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<script type="text/javascript">


    var firstGrid = new ax5.ui.grid();

    $(document).ready(function () {

        // 그리드세팅
        firstGrid.setConfig({
                target: $('[data-ax5grid="first-grid"]'),
                sortable: true,
                showRowSelector: false,
                multipleSelect: false,
                showLineNumber: true,
                lineNumberColumnWidth: 40,

                header: {
                    align: "center",
                    columnHeight: 40
                },
                body: {
                    align: "center",
                    columnHeight: 28,
                    onDBLClick: function () {

                    }

                },

                columns: [
                    {key: "themaGroupNm", label: "테마통계관리그룹" ,width: 120},
                    {key: "themaId", label: "테마통계아이디"},
                    {key: "themaNm", label: "테마통계명"},
                    {key: "themaTitle", label: "테마통계타이틀"},
                    {key: "themaMemo", label: "메모"},
                    {key: "themaEtc", label: "기타"},
                    {key: "tblId", label: "통계표ID"},
                    // {key: "loadGubun", label: "조회구분(시계열,횡단면)"},
                    // {key: "loadTerm", label: "조회설정"},
                    {key: "collectTypeNm", label: "수집타입"},
                    {key: "collectCycleNm", label: "수집주기"},
                    {key: "apiUrl", label: "API_URL"},
                    {key: "htmlFile", label: "HTML_파일명"},
                    {key: "orderCnt", label: "정렬순번"},
                    {key: "useYn", label: "사용여부"},
                    {
                        key: "themaId", label: "수정", width:60 ,formatter: function () {

                            // console.log(this.item);
                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoUpdt(" + this.dindex + ");'> 수정 </button>";
                        }
                    },
                    {
                        key: "themaId", label: "삭제", width:60 ,formatter: function () {
                            // console.log(this.item);
                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoDelete(" + this.dindex + ");'> 삭제 </button>";
                        }
                    }

                ],
                page: {
                    navigationItemCount: 10,
                    height: 30,
                    display: true,
                    firstIcon: '|<',
                    prevIcon: '<',
                    nextIcon: '>',
                    lastIcon: '>|',
                    onChange: function () {
                        Search(this.page.selectPage);
                    }
                },
            }
        );


        $('#btn_search').click(function () {
            Search(0);
        });

        $('#btn_regist').click(function () {
            gotoRegist();
        });

        Search(0);
    });

    // 조회
    function Search(_pageNo) {
        var searchCondition = $("#searchCondition option:selected").val();
        var searchKeyword = $("#searchKeyword").val();
        var themaGroupId = $('#themaGroupId').val();


        var p = {
            pageIndex: _pageNo,
            searchCondition: searchCondition,
            searchKeyword: searchKeyword,
            themaGroupId: themaGroupId
        };

        $ifx.ajax('<c:url value='/cms/gsis/thema/jewThemaInfoListObject.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                firstGrid.setData(res);
                // firstGrid.setData(res.list);
            }
        })
    }

    //등록 화면
    function gotoRegist() {

        var p = {};
        var API_SERVER = "<c:url value='/cms/gsis/thema/jewThemaInfoRegistView.do' />";
        ax5modal.open({
            theme: "primary",
            height: 664,
            width: 896,
            header: {
                title: '테마통계관리 등록',
                btns: {
                    close: {
                        label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
                            // modal.close();
                            ax5modal.close();
                        }
                    }
                }
            },
            iframe: {
                method: "get",
                url: API_SERVER,
                param: p
            },

        }, function (d) {
            Search();
        });
    }

    //수정화면
    function gotoUpdt(row) {

        var themaId = firstGrid.getList()[row].themaId;

        var p = {
            themaId: themaId
        };

        var API_SERVER = "<c:url value='/cms/gsis/thema/jewThemaInfoUpdtView.do' />";
        ax5modal.open({
            theme: "primary",
            height: 664,
            width: 896,
            header: {
                title: '테마통계관리 수정',
                btns: {
                    close: {
                        label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
                            // modal.close();
                            ax5modal.close();
                        }
                    }
                }
            },
            iframe: {
                method: "get",
                url: API_SERVER,
                param: p
            },
        }, function (d) {
            Search();
        });
    }

    function gotoDelete(row){

        var formData ={
              themaId : firstGrid.getList()[row].themaId
        }

        var API_SERVER = "<c:url value='/cms/gsis/thema/jewThemaInfoDelete.do' />";
        var saveQuestion = confirm("삭제 하시겠습니까?");
        if (saveQuestion) {
            $.ajax({
                url : API_SERVER,
                type : 'post',
                data : formData,
                dateType:'json',

                beforeSend: function(xhr) {

                    xhr.setRequestHeader("AJAX", "true");

                },
                success : function(data) {

                    var jsonObj = JSON.stringify(data);
                    if(data.status=="0"){
                        if(data.message=="SUCCESS"){
                            alert("삭제 완료했습니다.");
                            Search();
                        }else{
                            alert(data.message);

                        }
                    }else{

                        alert("처리중 오류가 발생했습니다.");
                    }
                }, // success
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });
        }

    }

</script>
<div class="sub subView">
    <nav class="navigation">
        <i class='bx bxs-home'></i>${menuInfo.depthFullname}
    </nav>
    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i>테마통계관리<spring:message code="title.list"/>
    </h2>
    <h3 class="btitle">
        검색
    </h3>
    <!-- 검색영역 -->
    <!-- 검색조건선택 -->
    <div class="white-box">
        <div class="rows">
        <span class="select-outline">
<%--            <form:select path="themaGroupId">--%>
<%--                <form:option value="" label="선택"/>--%>
<%--                <c:forEach items="${jewGroupList}" var="item">--%>
<%--                    <form:option value="${item.themaGroupId}" label="${item.themaGroupId}"/>--%>
<%--                </c:forEach>--%>
<%--            </form:select>--%>
            <select id="themaGroupId">
                <option value="">그룹선택</option>
                <c:forEach items="${jewGroupList}" var="item">
                    <option value="${item.themaGroupId}">${item.themaGroupNm}</option>
                </c:forEach>
            </select>
        </span>
         <span class="select-outline">
                <select name="searchCondition" id="searchCondition"
                        title="<spring:message code="title.searchCondition" />">
                       <option selected value=''>검색조건</option><!-- 선택하세요 -->
                       <option value="1">테마통계아이디</option>
                    <!-- 코드ID -->
                       <option value="2">테마통계명</option>
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
            <button type="button" class="button main" name="btn_regist" id="btn_regist"
                    title="<spring:message code="button.create" /> <spring:message code="input.button" />">
                <spring:message code="button.create"/></button>
        </div>
    </div>
    <h3 class="btitle">
        목록
    </h3>
    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 500px;"></div>
    </div>
</div>