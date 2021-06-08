<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<%
    /**
     * @Class Name : EgovCcmCmmnClCodeList.jsp
     * @Description : 공통분류코드 목록 화면
     * @Modification Information
     * @
     * @  수정일             수정자                   수정내용
     * @ -------    --------    ---------------------------
     * @ 2009.02.01   박정규              최초 생성
     *   2017.07.20   이정은              표준프레임워크 v3.7 개선
     *  @author 공통서비스팀
     *  @since 2009.02.01
     *  @version 1.0
     *  @see
     *
     */
%>


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
                        //saveView1(this.item);
                    }

                },

                columns: [
                    {key: "clCode", label: "분류코드"},
                    {key: "clCodeNm", label: "분류코드명", width: 200},
                    {key: "useAt", label: "사용여부"},
                    {
                        key: "clCode", label: "상세보기", formatter: function () {

                            // console.log(this.item);
                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoView(" + this.dindex + ");'> 상세보기 </button>";
                        }
                    },

                ]
            }
        );


        $('#btn_search').click(function () {
            Search();
        });

        $('#btn_regist').click(function () {
            gotoRegist();
        });

        $('#btn_report').click(function () {


            var params = [
                {
                    paramsKey: "id", paramsVal: "jin"
                },
                {
                    paramsKey: "userNm", paramsVal: "양진"
                }
            ]
            var p = {

                reportPath: "", reportFileNm: "Receipt", reportDownloadFileNm: "testReport",
                params: params

            }

            // console.log(JSON.stringify(totData))
            console.log(p)

            var API_SERVER = "<c:url value='/cms/report/test2.do' />";
            ax5modal.open({
                theme: "primary",
                height: 500,
                width: 600,
                header: {
                    title: '레포트 뷰',
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
                    method: "post",
                    url: API_SERVER,
                    param: p
                },

            }, function (d) {
                console.log("처리완려==>" + d);
                Search();
            });


        });


        Search();
    });

    ///cms/ccc/InfoCcmCmmnClCodeRegist.do

    // 조회
    function Search() {
        var searchCondition = $("#searchCondition option:selected").val();
        var searchKeyword = $("#searchKeyword").val();
        var searchUseYn = $("#searchUseYn option:selected").val();

        var p = {
            searchCondition: searchCondition,
            searchKeyword: searchKeyword,
            searchUseYn: searchUseYn
        };

        $ifx.ajax('<c:url value='/cms/ccm/ccc/SelectCcmCmmnClCodeObject.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                firstGrid.setData(res.list);
            }
        })

        /* 그리드 데이터 가져오기 */
        /*var API_SERVER = "<c:url value='/cms/ccm/ccc/SelectCcmCmmnClCodeObject.do' />";
        $.ajax({
            method: "POST",
            url: API_SERVER,
            dateType: 'json',
            data: JSON.stringify(p),
            contentType: "application/json",  // ajax 통신으로 보내는 타입

            beforeSend: function (xhr) {

                xhr.setRequestHeader("AJAX", "true");

            },

            success: function (res) {

                console.log("res==>" + res);


                firstGrid.setData(res.list);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("data=1=>" + JSON.stringify(XMLHttpRequest));
                console.log("data=2=>" + XMLHttpRequest);
                console.log("data=3=>" + XMLHttpRequest.status);
            }
        });*/

    }


    //등록 화면
    function gotoRegist() {

        var p = {};
        var API_SERVER = "<c:url value='/cms/ccm/ccc/InfoCcmCmmnClCodeRegist.do' />";
        ax5modal.open({
            theme: "primary",
            height: 500,
            width: 600,
            header: {
                title: '코드분류 등록',
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
            console.log("처리완려==>" + d);
            Search();
        });
    }


    function selectBoxInit() {

        var API_SERVER = "<c:url value='/sym/ccm/ccc/CodeTestList.do' />";
        ajaxLoadSelect({
            url: API_SERVER,
            params: [
                {name: 'p1', value: '0000000000'},
                {name: 'key', value: 'dd'}
            ],
            selectboxNm: 'sggu'
        });


    }


    //뷰
    function gotoView(row) {


        var clCode = firstGrid.getList()[row].clCode;

        var p = {
            clCode: clCode
        };


        //return  gotoModify(clCode);
        //var API_SERVER = "<c:url value='/sym/ccm/ccc/edit.do' />";
        var API_SERVER = "<c:url value='/cms/ccm/ccc/view.do' />";
        ax5modal.open({
            theme: "primary",
            height: 500,
            width: 600,
            header: {
                title: '상세조회',
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

            if (d.modType == "modify") {
                ax5modal.close();
                // alert("수정 화면으로 고고고 ===>"+d.clCode)
                gotoModify(d.clCode)
            }

            //  Search();
        });

    }


    //등록, 수정창 오픈
    function gotoModify(clCode) {
        var p = {
            clCode: clCode
        };
        var API_SERVER = "<c:url value='/cms/ccm/ccc/InfoCcmCmmnClCodeUpdt.do' />";
        ax5modal.open({
            theme: "primary",
            height: 500,
            width: 600,
            header: {
                title: '공통분류 수정',
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

            if (d == "OK") {

                Search();
            }
        });

    }


</script>
<div class="sub subView">
    <nav class="navigation">
        <i class='bx bxs-home'></i>${menuInfo.depthFullname}

    </nav>
    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i><spring:message code="title.list"/>
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
                       <option value="1"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeId"/></option>
                    <!-- 코드ID -->
                       <option value="2"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.code"/></option>
                    <!-- 코드ID -->
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
            <%--<button type="button" class="button main" name="btn_report" id="btn_report" title="레포트 출력">레포트 출력</button>--%>
        </div>


    </div>


    <h3 class="btitle">
        ${menuInfo.menuNm}
    </h3>

    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 300px;"></div>
    </div>


    <article class="pagenation">
        페이지
    </article>


</div>