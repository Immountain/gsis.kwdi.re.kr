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
                showRowSelector: true,
                multipleSelect: true,
                showLineNumber: false,
                lineNumberColumnWidth: 40,

                header: {
                    align: "center",
                    columnHeight: 40
                },
                body: {
                    align: "center",
                    columnHeight: 28,
                    onClick: function () {
                        firstGrid.select(this.dindex);
                    },
                    onDBLClick: function () {
                    }
                },
                columns: [
                    {key: "esntlId", label: "esntlId", width: 100, hidden: true},
                    {key: "groupId", label: "groupId", width: 100, hidden: true},
                    {key: "orgnztId", label: "orgnztId", width: 100, hidden: true},
                    {key: "userSe", label: "userSe", width: 100, hidden: true},

                    {key: "", label: "타입", width: 120, formatter: function () {
                        var type = "알수없음";
                        if(this.item.userSe === 'GNR') type = "일반회원";
                        else if(this.item.userSe === 'ENT') type = "기업회원";
                        else if(this.item.userSe === 'USR') type = "업무회원";
                        return type
                    }},
                    {key: "userNm", label: "사용자명", width: 120},
                    {key: "userId", label: "아이디", width: 120},
                    {key: "userEmail", label: "이메일", width: 200, align: 'left'},
                    {key: "userAdres", label: "주소", width: 200, align: 'left'},
                    {key: "userZip", label: "우편번호", width: 120}
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

        $ifx.ajax('<c:url value='/cms/info/mail/selectMailSendListObject.do' />', {
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

    function mailSend() {
        var data = firstGrid.getList("selected");
        if (data.length < 1) {
            alert('메일 발송은 최소 한명 이상 설정되어야합니다.');
            return false;
        }

        var API_SERVER = "<c:url value='/cms/info/mail/selectMailSendForm.do'/>";
        ax5modal.open({
            theme: "primary",
            height: 600,
            width: 920,
            header: {
                title: '메일전송',
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
                method: "POST",
                url: API_SERVER,
                param: {
                    list: JSON.stringify(data)
                }
            },
        }, function (d) {
            Search();
        });
    }

    function mailSendHistory(_email) {
        var API_SERVER = "<c:url value='/cms/info/mail/selectMailSendHistory.do'/>";
        ax5modal.open({
            theme: "primary",
            height: 600,
            width: 920,
            header: {
                title: '메일전송',
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
                method: "POST",
                url: API_SERVER,
                param: {
                }
            },
        }, function (d) {

        });
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
            <button type="button" class="button main" onclick="mailSend(); return false;">메일 발송</button>
            <button type="button" class="button" onclick="mailSendHistory(); return false;">발송이력</button>
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