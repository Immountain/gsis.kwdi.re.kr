<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-mask/ax5mask.css"/>">
<script type="text/javascript">

    var mask = new ax5.ui.mask();
    var categoryGrid = new ax5.ui.grid();
    var categorySubGrid = new ax5.ui.grid();

    $(document).ready(function () {

        // 그리드세팅
        categoryGrid.setConfig({
                target: $('[data-ax5grid="first-grid"]'),
                sortable: true,
                showRowSelector: false,
                multipleSelect: false,
                showLineNumber: false,
                lineNumberColumnWidth: 40,

                header: {
                    align: "center",
                    columnHeight: 40
                },
                body: {
                    align: "center",
                    columnHeight: 28,
                    onClick: function(e) {

                        var parentId = this.item.categoryId;
                        categoryGrid.select(this.dindex);
                        $('#parentSubId').val(parentId);
                        SearchSecondGrid(parentId);

                    },
                    onDBLClick: function () {},
                    onDataChanged: function(){
                        console.log(this);
                    }
                },

                columns: [
                    {key: "categoryId", label: "카테고리아이디"},
                    {key: "categoryIdNm", label: "카테고리명"},
                    {key: "vwCd", label: "kosis서비스코드"},
                    {key: "tblId", label: "kosis통계아이디"},
                    {key: "statsUrl", label: "통계URL"},
                    {key: "useYn", label: "사용여부", width: 100},
                    {
                        key: "categoryId", label: "수정", width:60, formatter: function () {

                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoUpdt(" + this.dindex + ");'> 수정 </button>";
                        }
                    }
                ]
            }
        );



        $('#btn_search').click(function () {
            Search();
            SearchSecondGrid();
        });

        $('#btn_regist').click(function () {
            var parentId = $('#parentId').val()

            if(!parentId){
                alert('부모카테고리 선택하세요')
                return;
            }

            gotoRegist();
        });

        $('#SearchSecondGrid').click(function () {
            SearchSecondGrid();
        });

        $('#btn_regist_sub').click(function(){
            var parentId = $('#parentSubId').val()

            if(!parentId){
                alert('2차카테고리 선택하세요')
                return;
            }
            gotoSubRegist()
        });

        Search();


        // 3차카테고리 그리드세팅
        categorySubGrid.setConfig({
                target: $('[data-ax5grid="second-grid"]'),
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
                        //saveView1(this.item);
                    }

                },

                columns: [
                    {key: "categoryId", label: "카테고리아이디"},
                    {key: "categoryIdNm", label: "카테고리명"},
                    {key: "vwCd", label: "kosis서비스코드"},
                    {key: "tblId", label: "kosis통계아이디"},
                    {key: "statsUrl", label: "통계URL"},
                    {key: "useYn", label: "사용여부", width: 100},
                    {
                        key: "categoryId", label: "수정",width: 60, formatter: function () {

                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoSubPdt(" + this.dindex + ");'> 수정 </button>";
                        }
                    }

                ]
            }
        );


    });

    // 조회
    function Search() {

        var searchCondition = $("#searchCondition option:selected").val();
        var searchKeyword = $("#searchKeyword").val();
        var searchUseYn = $("#searchUseYn option:selected").val();
        var parentId = $("#parentId").val();

        var p = {
            searchCondition: searchCondition,
            searchKeyword: searchKeyword,
            searchUseYn: searchUseYn,
            parentId : parentId

        };

        $ifx.ajax('<c:url value='/cms/gsis/stats/jewStatsCategoryObject.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                categoryGrid.setData(res.list);
            }
        })
    }

    // categorySubGrid조회
    function SearchSecondGrid(_parentId) {
        var searchCondition = $("#searchConditionThird option:selected").val();
        var searchKeyword = $("#searchKeywordPort").val();
        var searchUseYn = $("#searchUseYnPort option:selected").val();
        var parentId = _parentId;

        var p = {
            searchCondition: searchCondition,
            searchKeyword: searchKeyword,
            searchUseYn: searchUseYn,
            parentId : parentId

        };

        $ifx.ajax('<c:url value='/cms/gsis/stats/jewStatsCategoryObject.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                categorySubGrid.setData(res.list);
                mask.close();

            }
        })

    }

    //2차카테고리 등록화면
    function gotoRegist() {

        var parentId = $('#parentId').val()

        var p = {
            parentId : parentId
        };
        var API_SERVER = "<c:url value='/cms/gsis/stats/jewStatsCategorySubRegistView.do' />";
        ax5modal.open({
            theme: "primary",
            height: 401,
            width: 763,
            header: {
                title: '하위카테고리 등록',
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
            SearchSecondGrid();
        });
    }

    //3차카테고리 등록화면
    function gotoSubRegist() {
        var data = categoryGrid.getList("selected");

        if(data.length<1){
            alert("2차카테고리 선택하세요")
            return false;
        }

        var parentId = $('#parentSubId').val()

        var p = {
            parentId : parentId
        };
        var API_SERVER = "<c:url value='/cms/gsis/stats/jewStatsCategorySubRegistView.do' />";
        ax5modal.open({
            theme: "primary",
            height: 401,
            width: 763,
            header: {
                title: '하위카테고리 등록',
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
            SearchSecondGrid();
        });
    }



    function gotoSubPdt(row) {


        var categoryId = categorySubGrid.getList()[row].categoryId;

        var p = {
            categoryId : categoryId,
            parentId : parentId
        };


        var API_SERVER = "<c:url value='/cms/gsis/stats/jewStatsCategorySubUpdtView.do' />";
        ax5modal.open({
            theme: "primary",
            height: 402,
            width: 830,
            header: {
                title: '하위카테고리 수정',
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
            SearchSecondGrid();
        });


    }





    //2차카테고리 수정
    function gotoUpdt(row) {

        var categoryId = categoryGrid.getList()[row].categoryId;

        var p = {
            categoryId : categoryId,
            parentId : parentId
        };


        var API_SERVER = "<c:url value='/cms/gsis/stats/jewStatsCategorySubUpdtView.do' />";
        ax5modal.open({
            theme: "primary",
            height: 402,
            width: 830,
            header: {
                title: '하위카테고리 수정',
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
            SearchSecondGrid();
        });

    }



</script>
<div class="sub subView dashboard-inside">
    <nav class="navigation">
        <i class='bx bxs-home'></i>${menuInfo.depthFullname}
    </nav>
    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i>통계DB 서브카테고리 <spring:message code="title.list"/>
    </h2>
    <div style="width:50%" class="widget c05">
        <h3 class="btitle">
            2차카테고리검색
        </h3>
        <!-- 검색조건선택 -->
        <div class="white-box">
            <div class="rows">
                 <span class="select-outline">
                    <select id="parentId">
                           <option selected value=''>부모카테고리</option>
                           <c:forEach items="${jsCategoryList}" var="item">
                               <option value="${item.categoryId}">${item.categoryIdNm}</option>
                           </c:forEach>
                    </select>
                 </span>

                    <select name="searchCondition" id="searchCondition" title="사용여부">
                           <option selected value=''>전체</option><!-- 선택하세요 -->
                           <option value="1">카테고리아이디</option><!-- 코드ID -->
                           <option value="2">카테고리명</option><!-- 코드ID -->
                   </select>

                <input type="text" class="w100" class="main" name="searchKeyword"  size="35" id="searchKeyword"
                       title="<spring:message code="title.search" /> <spring:message code="input.input" />" value=''
                       maxlength="155">
                <button type="button" class="button" name="btn_search" id="btn_search"
                        value="<spring:message code="button.inquire" />"
                        title="<spring:message code="title.inquire" /> <spring:message code="input.button" />"><spring:message code="title.inquire"/></button>
                <button type="button" class="button main" name="btn_regist" id="btn_regist"
                        title="<spring:message code="button.create" /> <spring:message code="input.button" />">
                    <spring:message code="button.create"/></button>
            </div>


        </div>


        <h3 class="btitle">
            2차카테고리목록
        </h3>

        <div class="rows white-box">
            <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 300px;"></div>
        </div>
    </div>

    <div style="width:49%" class="widget c05" id="secondView">
        <h3 class="btitle">
            3차카테고리 검색
        </h3>
        <!-- 검색조건선택 -->
        <div class="white-box">
            <div class="rows">

         <span class="select-outline">
            <select name="searchConditionThird" id="searchConditionThird"
                title="<spring:message code="title.searchCondition" />">
                <option value="1">카테고리아이디</option>
                <option value="2">카테고리명</option>
            </select>
         </span>

                <input type="hidden" id="parentSubId"/>
                <input type="text" class="w100" class="main" name="searchKeywordPort" id="searchKeywordPort" size="35"
                       title="<spring:message code="title.search" /> <spring:message code="input.input" />" value=''
                       maxlength="155">
                <button type="button" class="button" name="SearchSecondGrid" id="SearchSecondGrid"
                        value="<spring:message code="button.inquire" />"
                        title="<spring:message code="title.inquire" /> <spring:message code="input.button" />"><spring:message code="title.inquire"/></button>
                <button type="button" class="button main" name="btn_regist_sub" id="btn_regist_sub"
                        title="<spring:message code="button.create" /> <spring:message code="input.button" />">
                    <spring:message code="button.create"/></button>
            </div>
        </div>


        <h3 class="btitle">
            3차카테고리목록
        </h3>

        <div class="rows white-box">
            <div data-ax5grid="second-grid" data-ax5grid-config="{}" style="height: 300px;"></div>
        </div>
    </div>
</div>
