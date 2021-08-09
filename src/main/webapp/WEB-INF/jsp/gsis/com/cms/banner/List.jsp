<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                    {key: "bannerType", label: "배너 타입"},
                    {key: "bannerNm", label: "제목"},
                    {key: "strDt", label: "시작일"},
                    {key: "endDt", label: "종료일"},
                    {key: "bannerNm", label: "제목"},
                    {key: "linkType", label: "링크타입"},
                    {key: "linkUrl", label: "링크URL"},
                    {key: "useYn", label: "사용여부"},
                    {
                        key: "themaGroupId", label: "수정", width:60 ,formatter: function () {

                            // console.log(this.item);
                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoUpdt(" + this.dindex + ");'> 수정 </button>";
                        }
                    },
                    {
                        key: "categoryId", label: "삭제", width:60 ,formatter: function () {
                            // console.log(this.item);
                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoDelete(" + this.dindex + ");'> 삭제 </button>";
                        }
                    }

                ]
            }
        );


        $('#btn_search').click(function () {
            Search();
        });

        $('#btn_regist').click(function () {
            gotoRegist();
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

        $ifx.ajax('<c:url value='/cms/gsis/banner/ObjList.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                firstGrid.setData(res.list);
            }
        })
    }

    //등록 화면
    function gotoRegist() {

        var p = {};
        var API_SERVER = "<c:url value='/cms/gsis/banner/Regist.do' />";
        ax5modal.open({
            theme: "primary",
            height: 800,
            width: 800,
            header: {
                title: '배너 등록',
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

        var bannerSno = firstGrid.getList()[row].bannerSno;

        var p = {
            bannerSno: bannerSno
        };

        var API_SERVER = "<c:url value='/cms/gsis/banner/view.do' />";
        ax5modal.open({
            theme: "primary",
            height: 800,
            width: 800,
            header: {
                title: '배너 수정',
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

            bannerSno : firstGrid.getList()[row].bannerSno
        }

        var API_SERVER = "<c:url value='/cms/gsis/banner/deleteJewBanner.do' />";
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

    };



</script>
<div class="sub subView">
    <nav class="navigation">
        <i class='bx bxs-home'></i>${menuInfo.depthFullname}
    </nav>
    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i>메인배너<spring:message code="title.list"/>
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
                       <option value='-1'>전체</option><!-- 선택하세요 -->
                       <option value="0">배너이미지</option>
                    <!-- 코드ID -->
                       <option value="1">배너 리스트</option>
                    <!-- 코드ID -->
               </select>
         </span>
            제목:
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
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 300px;"></div>
    </div>
</div>