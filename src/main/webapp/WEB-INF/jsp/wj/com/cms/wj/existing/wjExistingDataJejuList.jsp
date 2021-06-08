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
                    onClick: function(){},
                    onDBLClick: function () {}
                },

                columns: [
                    {
                        key: "noSno", label: "상세보기", width:100 ,formatter: function () {

                            // console.log(this.item);
                            return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoUpdt(" + this.dindex + ");'> 상세 </button>";
                        }
                    },
                    {key: "id", label:"등록아이디명", width:100,},
                    {key: "gubun", label:"로그인구분", width:100},
                    {key: "kname", label:"한글이름", width:100},
                    {key: "ename", label:"영문이름", width:100},
                    {key: "cname", label:"한문이름", width:100},
                    {key: "pic", label:"사진경로", width:100},
                    {key: "sex", label:"성별", width:100},
                    {key: "birthYear", label:"생년월일_년도", width:100},
                    {key: "birthMonth", label:"생년월일_월", width:100},
                    {key: "birthDay", label:"생년월일_일", width:100},
                    {key: "address", label:"주소", width:100},
                    {key: "addressEtc", label:"주소기타", width:100},
                    {key: "country", label:"출신지", width:100},
                    {key: "countryEtc", label:"출신지기타", width:100},
                    {key: "hp", label:"핸드폰", width:100},
                    {key: "tel1", label:"전화번호_자택", width:100},
                    {key: "tel2", label:"전화번호_직장", width:100},
                    {key: "email", label:"이메일", width:100},
                    {key: "company", label:"소속기관", width:100},
                    {key: "spot", label:"소속기관_직위", width:100},
                    {key: "schoolE", label:"학력_초등학교", width:100},
                    {key: "schoolEYear", label:"초등학교_졸업년도", width:100},
                    {key: "schoolM", label:"학력_중학교", width:100},
                    {key: "schoolMYear", label:"중학교_졸업년도", width:100},
                    {key: "schoolH", label:"학력_고등학교", width:100},
                    {key: "schoolHYear", label:"고등학교_졸업년도", width:100},
                    {key: "schoolU", label:"학력_대학교", width:100},
                    {key: "schoolUYear", label:"대학교_졸업년도", width:100},
                    {key: "schoolG", label:"학력_대학원이상", width:100},
                    {key: "schoolGYear", label:"대학원이상_졸업년도", width:100},
                    {key: "edu", label:"최종학력", width:100},
                    {key: "activity", label:"활동분야", width:100},
                    {key: "religion", label:"종교", width:100},
                    {key: "religionEtc", label:"종교_기타", width:100},
                    {key: "career", label:"경력사항", width:100},
                    {key: "wdate", label:"등록일", width:100},
                    {key: "udate", label:"수정일", width:100}

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

        $ifx.ajax('<c:url value='/cms/wj/existing/SelectWjExistingDataJejuObject.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                firstGrid.setData(res.list);
            }
        })
    }

    function gotoUpdt(row) {

        var _noSno = firstGrid.getList()[row].noSno;

        var p = {
            noSno: _noSno,
            menuTargetNo : $('#menuTargetNo').val()
        };

        var API_SERVER = "<c:url value='/cms/wj/existing/wjExistingDataJejuDetail.do' />";
        ax5modal.open({
            theme: "primary",
            height: 765,
            width: 1551,
            header: {
                title: '상세보기',
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

    function gridExcelDownLoad() {

        firstGrid.updateColumn($.extend(true, firstGrid.config.columns[0], {hidden: true}), 0);
        firstGrid.exportExcel("wjExistingDataJeju.xls");
        firstGrid.updateColumn($.extend(true, firstGrid.config.columns[0], {hidden: false}), 0);

    }

</script>
<div class="sub subView">
    <nav class="navigation">
        <i class='bx bxs-home'></i>${menuInfo.depthFullname}
    </nav>
    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i>기존제주인데이터 <spring:message code="title.list"/>
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
    <button type="button" class="button main" onclick="gridExcelDownLoad()"><i class="bx bx-slider-alt"></i>엑셀자료 다운로드</button>
</div>