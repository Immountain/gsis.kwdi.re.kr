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
                    onDBLClick: function () {}

                },

                columns: [
                    {
                        key: "jewMdisSno", label: "수정", width:60 ,formatter: function () {

                            // console.log(this.item);
                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoUpdt(" + this.dindex + ");'> 수정 </button>";
                        }
                    },
                    {
                        key: "jewMdisSno", label: "삭제", width:60 ,formatter: function () {

                            // console.log(this.item);
                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoDelete(" + this.dindex + ");'> 삭제 </button>";
                        }
                    },
                    {key: "mdisNum", label: "자료번호"},
                    {key: "mdisType", label: "자료유형"},
                    {key: "mdisKorNm", label: "자료명"},
                    {key: "mdisEnNm", label: "자료명"},
                    {key: "dataOfForm", label: "자료인용서식"},
                    {key: "projectKorNm", label: "연구과제명"},
                    {key: "projectEnNm", label: "연구과제명"},
                    {key: "pi", label: "연구책임자"},
                    {key: "coPi", label: "공동연구자"},
                    {key: "organization", label: "연구수행기관"},
                    {key: "supportingOrganization", label: "연구비지원기관"},
                    {key: "copyrightHolder", label: "저작권자"},
                    {key: "investigatePurpose", label: "조사목적"},
                    {key: "investigateContent", label: "조사내용"},
                    {key: "keyword", label: "키워드"},
                    {key: "collectionStrDay", label: "자료수집시작일"},
                    {key: "collectionEndDay", label: "자료수집종료일"},
                    {key: "investigateArea", label: "조사지역"},
                    {key: "analysisUnit", label: "분석단위"},
                    {key: "subject", label: "조사대상"},
                    {key: "mdisTime", label: "시간적차원"},
                    {key: "researchOrganization", label: "새"},
                    {key: "interviewSurvey", label: "조사방법_면대면"},
                    {key: "selfAdministeredSurvey", label: "조사방법_자기기입식"},
                    {key: "mailSurvey", label: "조사방법_우편조사"},
                    {key: "phoneSurvey", label: "조사방법_전화조사"},
                    {key: "onlineSurvey", label: "조사방법_온라인조사"},
                    {key: "etc", label: "조사방법_기타"},
                    {key: "extraction", label: "표본추출방법"},
                    {key: "caseNumber", label: "사례수"},
                    {key: "weight", label: "가중치"},
                    {key: "dataForm", label: "자료형식"},
                    {key: "dataStructure", label: "자료구성"},
                    {key: "mdisLanguage", label: "사용언어"},
                    {key: "publicYn", label: "자료공개여부"},
                    {key: "publicReasons", label: "자료공개_사유"},
                    {key: "remark", label: "비고"}


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

        $ifx.ajax('<c:url value='/cms/gsis/stats/jewMdisObject.do' />', {
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
        var API_SERVER = "<c:url value='/cms/gsis/stats/jewMdisRegistView.do' />";
        ax5modal.open({
            theme: "primary",
            height: 791,
            width: 930,
            header: {
                title: '마이크로데이터 등록',
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

        var jewMdisSno = firstGrid.getList()[row].jewMdisSno;

        var p = {
            jewMdisSno: jewMdisSno
        };

        var API_SERVER = "<c:url value='/cms/gsis/stats/jewMdisUpdtView.do' />";
        ax5modal.open({
            theme: "primary",
            height: 791,
            width: 930,
            header: {
                title: '마이크로데이터 수정',
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

            jewMdisSno : firstGrid.getList()[row].jewMdisSno

        }

        var API_SERVER = "<c:url value='/cms/gsis/stats/jewMdisDelete.do' />";
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
        <i class='bx bxs-dashboard'></i>마이크로데이터 목록
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
                       <option value="1">자료명</option>
                       <option value="2">연구책임자</option>
               </select>
         </span>

            <span class="select-outline">
                <select name="searchUseYn" id="searchUseYn" title="사용여부">
                       <option selected value=''>전체</option><!-- 선택하세요 -->
                       <option value="Y">사용</option><!-- 코드ID -->
                       <option value="N">사용안함</option><!-- 코드ID -->
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
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 300px;"></div>
    </div>
</div>