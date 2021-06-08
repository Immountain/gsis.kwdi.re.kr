<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<div class="dashboard-inside subView">


    <h2 class="stitle"><i class="bx bxs-dashboard"></i>Dashboard</h2>
    <div class="widget c05 h02 gray-l wall">
        <div class="inline">
            <h3>
                사이트별 방문
                <small>
                    ${formatTime} 기준
                    <button type="button" onclick="getSearchChartData()"><i class="bx bx-refresh"></i>새로고침</button>
                </small>
            </h3>
            <div class="box">
                <div class="chart" id="lastWeekDaysVisitCount"></div>
            </div>
        </div>
    </div>
    <div class="widget c04 h02 main">
        <div class="inline">
            <h3>
                가입 현황
                <small>
                    ${formatTime} 기준
                    <button><i class="bx bx-refresh"></i>새로고침</button>
                </small>
            </h3>
            <div class="box table">
                <table>
                    <thead>
                    <tr>
                        <th>싸이트별</th>
                        <th>일별</th>
                        <th>월별</th>
                        <th>년간</th>
                    </tr>
                    </thead>
                    <tbody>


                    <c:forEach items="${wjMainDashBoardVOList}" var="item" varStatus="status">

                        <tr>
                            <td>${item.langCode}</td>
                            <td>${item.dayCnt} 명</td>
                            <td>${item.monthCnt} 명</td>
                            <td>${item.yearCnt} 명</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div>
        <div class="widget c03 h02">
            <div class="inline">
                <h3>국문</h3>
                <div class="box">
                    <div id="getSiteMenuYearStats_ko"></div>
                </div>
            </div>
        </div>
        <div class="widget c03 h02">
            <div class="inline">
                <h3>영문</h3>
                <div class="box">
                    <div id="getSiteMenuYearStats_en"></div>
                </div>
            </div>
        </div>
        <div class="widget c03 h02">
            <div class="inline">
                <h3>일문</h3>
                <div class="box">
                    <div id="getSiteMenuYearStats_ja"></div>
                </div>
            </div>
        </div>
    </div>


    <div class="widget c05 h02 ">
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


    <div class="widget c05 h02">
        <div class="inline">
            <h3>
                언로보도
                <%--<small>--%>
                <%--<button><i class="bx bx-plus"></i>더보기</button>--%>
                <%--</small>--%>
            </h3>
            <div class="box board">
                <info:boardLatest boardId="media" skinName="dashboard" listCount="5" urlType="CMS"/>
            </div>

        </div>
    </div>


</div>
<script src="<c:url value='/js/infomind/com/moment.js'/>"></script>
<script src="<c:url value='/js/infomind/com/incms.polyfill.js'/>"></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>"></script>
<script>
    $ifx.contextPath = '<c:url value="/"/>';
</script>
<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/highcharts.src.js"/>"></script>
<script>
    $(document.body).ready(function () {
        initChartEl();

        getSearchChartData();
        searchData();
    });

    var chartColor = [
        '#e94235', '#fabb04', '#34a853', '#4285f4',
        '#e94235', '#fabb04', '#34a853', '#4285f4',
        '#e94235', '#fabb04', '#34a853', '#4285f4'
    ];
    var getSiteMenuYearStats_ko, getSiteMenuYearStats_en, getSiteMenuYearStats_ja = null;
    var lastWeekDaysVisitCount;

    function initChartEl() {
        lastWeekDaysVisitCount = Highcharts.chart('lastWeekDaysVisitCount', {
            credits: {
                enabled: false
            },
            chart: {
                height: '180px',
                backgroundColor: null,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'line'
            },
            title: {
                text: ''
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.y}</b>'
            },
            accessibility: {
                point: {
                    valueSuffix: '%'
                }
            },
            plotOptions: {},
            series: []
        });


        getSiteMenuYearStats_ko = Highcharts.chart('getSiteMenuYearStats_ko', {
            credits: {
                enabled: false
            },
            chart: {
                height: '300px',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: ''
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            accessibility: {
                point: {
                    valueSuffix: '%'
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    // colors: [],
                    dataLabels: {
                        enabled: true,
                        //color: '#555',
                        format: '{point.name} {point.percentage:,.1f}%',
                        distance: -60,
                        style: {
                            fontWeight: 400,
                            textOutline: 0,
                            fontSize: '1em'
                        },
                        filter: {
                            property: 'percentage',
                            operator: '>',
                            value: 4
                        }
                    }
                }
            },
            series: []
        });


        getSiteMenuYearStats_en = Highcharts.chart('getSiteMenuYearStats_en', {
            credits: {
                enabled: false
            },
            chart: {
                height: '300px',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: ''
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            accessibility: {
                point: {
                    valueSuffix: '%'
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',

                    dataLabels: {
                        enabled: true,
                        //color: '#555',
                        format: '{point.name} {point.percentage:,.1f}%',
                        distance: -60,
                        style: {
                            fontWeight: 400,
                            textOutline: 0,
                            fontSize: '1em'
                        },
                        filter: {
                            property: 'percentage',
                            operator: '>',
                            value: 4
                        }
                    }
                }
            },
            series: []
        });


        getSiteMenuYearStats_ja = Highcharts.chart('getSiteMenuYearStats_ja', {
            credits: {
                enabled: false
            },
            chart: {
                height: '300px',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: ''
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            accessibility: {
                point: {
                    valueSuffix: '%'
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        //color: '#555',
                        format: '{point.name} {point.percentage:,.1f}%',
                        distance: -60,
                        style: {
                            fontWeight: 400,
                            textOutline: 0,
                            fontSize: '1em'
                        },
                        filter: {
                            property: 'percentage',
                            operator: '>',
                            value: 4
                        }
                    }
                }
            },
            series: []
        });


    }

    function getSearchChartData() {
        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value="/cms/wj/dashboard/getLastWeekDaysVisitCount.do"/>', {
                    method: 'get',
                    data: {},
                    success: function (res) {
                        ok($ifx.pivot(res.data, ['siteNm'], ['visitDay'], {}));
                    }
                })
            })
            .then(function (ok, fail, data) {
                var convertData = data.map(function (x, i) {
                    return {
                        name: data.rowHeaders[i][0],
                        data: x.map(function (x1) {
                            return x1[0].visitCount || 0
                        })
                    }
                });
                // 누락데이터 0 처리
                convertData.map(function (x) {
                    for (var i = 0; i < x.data.length; i++) {
                        x.data[i] = Number(x.data[i] || "0")
                    }
                })
                console.log(convertData)

                lastWeekDaysVisitCount.update({
                    xAxis: {
                        categories: data.columnHeaders.map(function (x) {
                            return x[0]
                        })
                    },
                    series: convertData
                }, true, true);
            });
    }

    function searchData() {
        var data = {};
        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value="/cms/info/site/stats/getSiteMenuTotYear.do"/>', {
                    method: 'GET',
                    data: {siteId: 'globaljejuin-ko'},
                    success: function (res) {
                        getSiteMenuYearStats_ko.update({
                            series: [{
                                name: '국',
                                data: res.map(function (x) {
                                    return {
                                        name: x.siteMemuTile,
                                        y: x.cnt
                                    }
                                })
                            }]
                        }, true, true);
                        ok(res);
                    }
                })
            })
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value="/cms/info/site/stats/getSiteMenuTotYear.do"/>', {
                    method: 'GET',
                    data: {siteId: 'globaljejuin-en'},
                    success: function (res) {
                        getSiteMenuYearStats_en.update({
                            series: [{
                                name: '영문',
                                data: res.map(function (x) {
                                    return {
                                        name: x.siteMemuTile,
                                        y: x.cnt
                                    }
                                })
                            }]
                        }, true, true);
                        ok(res);
                    }
                })
            })
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value="/cms/info/site/stats/getSiteMenuTotYear.do"/>', {
                    method: 'GET',
                    data: {siteId: 'globaljejuin-ja'},
                    success: function (res) {
                        getSiteMenuYearStats_ja.update({
                            series: [{
                                name: '일문',
                                data: res.map(function (x) {
                                    return {
                                        name: x.siteMemuTile,
                                        y: x.cnt
                                    }
                                })
                            }]
                        }, true, true);
                        ok(res);
                    }
                })
            })

    }

</script>

