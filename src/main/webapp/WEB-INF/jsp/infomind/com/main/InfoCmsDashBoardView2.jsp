<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<script src="<c:url value="/js/infomind/com/axui5modal.js"/>"></script>


<div class="dashboard subView">

    <h2 class="stitle"><i class="bx bxs-dashboard"></i>Dashboard</h2>
    <div class="widget c05 h02 gray-l wall">
        <div class="inline">
            <h3>
                장비 가동률 현황
                <small>
                    ${formatTime} 기준
                    <button type="button" onclick="getSearchChartData()"><i class="bx bx-refresh"></i>새로고침</button>
                </small>
            </h3>
            <div class="box">
                <div class="chart" id="equipmentOperationRateStatus"></div>
            </div>
        </div>
    </div>
    <div class="widget c04 h02 black">
        <div class="inline">
            <h3>
                연구장비등록현황

                <small>
                    ${formatTime} 기준
                    <button><i class="bx bx-refresh"></i>새로고침</button>
                </small>
            </h3>
            <div class="box calc">
                <ul>
                    <li>
                        <span><i class="bx bxs-buildings"></i>등록기관수</span>
                        <strong>
                            <u>${mainEquipReport.organRegistCnt}</u>개
                        </strong>
                    </li>
                    <li>
                        <span><i class="bx bxs-cog"></i>공동활용장비수</span>
                        <strong>
                            <u>${mainEquipReport.publicUseCnt}</u>개
                        </strong>
                    </li>
                    <li>
                        <span><i class="bx bxs-coin-stack"></i>취득금액</span>
                        <strong>
                            <u>${mainEquipReport.takePrice}</u>억원
                        </strong>
                    </li>
                    <li>
                        <span><i class="bx bxs-pie-chart-alt"></i>공동활용허용률</span>
                        <strong>
                            <u>${mainEquipReport.publicUseRate}</u>%
                        </strong>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="widget c03 h02 main">
        <div class="inline">
            <h3>
                이용현황
                <small>
                    2021년 기준
                    <button><i class="bx bx-refresh"></i>새로고침</button>
                </small>
            </h3>
            <div class="box table">

                <table>
                    <thead>
                    <tr>
                        <th>일별</th>
                        <th>월별</th>
                        <th>년간</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="widget c06 ">
        <div class="inline">
            <h3>
                공지사항
                <%--<small>--%>
                    <%--<button><i class="bx bx-plus"></i>더보기</button>--%>
                <%--</small>--%>
            </h3>
            <div class="box board">
                <info:boardLatest boardId="notic" skinName="dashboard" listCount="5" urlType="CMS"/>
            </div>

        </div>
    </div>


    <div class="widget c06 ">
        <div class="inline">
            <h3>
                교육정보
                <%--<small>--%>
                    <%--<button><i class="bx bx-plus"></i>더보기</button>--%>
                <%--</small>--%>
            </h3>
            <div class="box board">
                <info:boardLatest boardId="edu" skinName="dashboard" listCount="5" urlType="CMS"/>
            </div>

        </div>
    </div>


    <%--<div class="widget c06 ">--%>
        <%--<div class="inline">--%>
            <%--<h3>--%>
                <%--예약신청--%>
                <%--<small>--%>
                    <%--<button><i class="bx bx-plus"></i>더보기</button>--%>
                <%--</small>--%>
            <%--</h3>--%>
            <%--<div class="box board">--%>
                <%--<ul>--%>
                    <%--<c:forEach items="${reserveMst}" var="item">--%>
                        <%--<li>--%>
                            <%--<a href="#">--%>
                                    <%--[${item.resvLaststatNm}] ${item.korNm}--%>
                                <%--<i>${item.resvDt}</i>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                    <%--</c:forEach>--%>
                <%--</ul>--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</div>--%>


    <%--<div class="widget c06 ">--%>
        <%--<div class="inline">--%>
            <%--<h3>--%>
                <%--장비상담--%>
                <%--<small>--%>
                    <%--<button><i class="bx bx-plus"></i>더보기</button>--%>
                <%--</small>--%>
            <%--</h3>--%>
            <%--<div class="box board">--%>
                <%--<ul>--%>
                    <%--<c:forEach items="${reserveCounsl}" var="item">--%>
                        <%--<li>--%>
                            <%--<a href="#">--%>
                                <%--[${item.counslStatusCdNm}] ${item.counslTitle}--%>
                                <%--<i>${item.regDt}</i>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                    <%--</c:forEach>--%>
                <%--</ul>--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</div>--%>

</div>
<script src="<c:url value='/js/infomind/com/moment.js'/>" ></script>
<script src="<c:url value='/js/infomind/com/incms.polyfill.js'/>" ></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>" ></script>
<script>
$ifx.contextPath='<c:url value="/"/>';
</script>
<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/highcharts.src.js"/>"></script>
<script>
    $(document.body).ready(function(){
        initChartEl();
        getSearchChartData();
    });

    var chartColor = [
        '#e94235', '#fabb04', '#34a853', '#4285f4',
        '#e94235', '#fabb04', '#34a853', '#4285f4',
        '#e94235', '#fabb04', '#34a853', '#4285f4'
    ];

    var equipmentOperationRateStatus, equipmentPurchaseCost;
    function initChartEl() {
        equipmentOperationRateStatus = Highcharts.chart('equipmentOperationRateStatus', {
            credits: {
                enabled: false
            },
            chart: {
                height: '180px',
                backgroundColor: null,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'column'
            },
            title: {
                text: ''
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.y:,.1f} %</b>'
            },
            accessibility: {
                point: {
                    valueSuffix: '%'
                }
            },
            plotOptions: {},
            series: []
        });
    }
    function getSearchChartData() {
        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value="/equipment/rest/getCloudRateYear.do"/>', {
                    method: 'get',
                    data: {
                        startDd: moment().subtract(2, 'year').format('YYYY'),
                        endDd: moment().format('YYYY'),
                    },
                    success: function(res) {
                        res.data.forEach(function(v){
                            v.cloudNm = v.cloudNm.replace('제주테크노파크 ', '')
                        })

                        ok($ifx.pivot(res.data, ['cloudNm'], ['yr'], {}));
                    }
                })
            })
            .then(function (ok, fail, data) {
                equipmentOperationRateStatus.update({
                    xAxis: {
                        categories: data.columnHeaders.map(function(x) {
                            return x[0]
                        })
                    },
                    series: data.map(function(x, i) {
                        return {
                            color: chartColor[i],
                            name: x[0][0].cloudNm,
                            data: x.map(function(y) {
                                return y[0].rate
                            }),
                            dataLabels: {
                                enabled: true,
                                rotation: -90,
                                color: '#FFFFFF',
                                align: 'right',
                                format: '{point.y:.1f}', // one decimal
                                y: 10, // 10 pixels down from the top
                                style: {
                                    fontSize: '13px',
                                    fontFamily: 'Verdana, sans-serif'
                                }
                            }
                        }
                    })
                }, true, true);
            });
    }




    //공지 교육 팝업
    function gotoPop(boardId,itemId) {

        var p = {boardId:boardId,itemId:itemId};
        var API_SERVER = "<c:url value='/cms/info/board/c/boardPopItemView.do' />";
        ax5modal.open({
            theme: "primary",
            height: 500,
            width: 800,
            header: {
                title: '상세',
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

        }, function (data) {




        });
    }







</script>

