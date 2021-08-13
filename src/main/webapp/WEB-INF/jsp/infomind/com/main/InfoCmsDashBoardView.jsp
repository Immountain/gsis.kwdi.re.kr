<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<!-- Highchart -->


<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/highcharts.js"/>"></script>
<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/modules/wordcloud.js"/>"></script>
<%--<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/modules/exporting.js"/>"></script>--%>
<%--<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/modules/export-data.js"/>"></script>--%>
<%--<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/modules/accessibility.js"/>"></script>--%>




<div class="dashboard-inside subView">


    <h2 class="stitle"><i class="bx bxs-dashboard"></i>Dashboard</h2>
    <div class="widget c06 h03 wall">
        <div class="inline">
            <h3>
                사이트 방문
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
    <div class="widget c06 h03 wall">
        <div class="inline">
            <h3>
                테마별 관심 방문
                <small>
                    ${formatTime} 기준
                    <button type="button" onclick="getSearchChartData()"><i class="bx bx-refresh"></i>새로고침</button>
                </small>
            </h3>
            <div class="box">
                <div class="chart" id="themaInfoVisitCount"></div>
            </div>
        </div>
    </div>

    <div>
        <div class="widget c03 h04">
            <div class="inline">
                <h3>마이크로파일</h3>
                <div class="box">
                    <div id="getSelectDashboardMdis"></div>
                </div>
            </div>
        </div>
        <div class="widget c03 h04">
            <div class="inline">
                <h3>메뉴별</h3>
                <div class="box">
                    <div id="getSiteMenuYearStats"></div>
                </div>
            </div>
        </div>
    </div>


    <div class="widget c06 h02 ">
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


    <div class="widget c06 h02">
        <div class="inline">
            <h3>
                업데이트 내용
                <%--<small>--%>
                <%--<button><i class="bx bx-plus"></i>더보기</button>--%>
                <%--</small>--%>
            </h3>
            <div class="box board">


                <ul>
                    <c:forEach items="${updateList}" var="item">
                        <li>
                            <a href="#">
                                    ${item.themaNm} (${item.updateKeepNm})
                                <i>${item.regDt}</i>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
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
        getSearchThemaInfoChartData();
        searchData();
    });

    var chartColor = [
        '#e94235', '#fabb04', '#34a853', '#4285f4',
        '#e94235', '#fabb04', '#34a853', '#4285f4',
        '#e94235', '#fabb04', '#34a853', '#4285f4'
    ];
    var getSelectDashboardMdis, getSiteMenuYearStats= null;
    var lastWeekDaysVisitCount;
    var themaInfoVisitCount;

    function initChartEl() {
        lastWeekDaysVisitCount = Highcharts.chart('lastWeekDaysVisitCount', {
            credits: {
                enabled: false
            },
            chart: {
                height: '400px',
                backgroundColor: null,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'line'
            },
            title: {
                text: ''
            },
            yAxis: [{ // Primary yAxis
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                title: {
                    text: '(명)',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                }
            }],
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




        themaInfoVisitCount = Highcharts.chart('themaInfoVisitCount', {
            chart: {
                marginRight: -200,
                marginLeft: -200,
                marginBottom: 0
            },
            accessibility: {
                screenReaderSection: {
                    beforeChartFormat: '<h5>{chartTitle}</h5>' +
                        '<div>{chartSubtitle}</div>' +
                        '<div>{chartLongdesc}</div>' +
                        '<div>{viewTableButton}</div>'
                }
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.value}</b>'
            },
            series: [],
            title: {
                text: '테마별 관심 '
            }
        });



        getSelectDashboardMdis = Highcharts.chart('getSelectDashboardMdis', {
            credits: {
                enabled: false
            },
            chart: {
                height: '400px',
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


        getSiteMenuYearStats = Highcharts.chart('getSiteMenuYearStats', {
            credits: {
                enabled: false
            },
            chart: {
                height: '400px',
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
                $ifx.ajax('<c:url value="/cms/info/site/stats/getLastWeekDaysVisitCount.do"/>', {
                    method: 'get',
                    data: {},
                    success: function (res) {
                     //   console.log(res);
                        ok($ifx.pivot(res, ['siteNm'], ['visitDay'], {}));
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
              //  console.log(convertData)

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

    function getSearchThemaInfoChartData() {
        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value="/cms/gsis/thema/getDashBoardThemaInfo.do"/>', {
                    method: 'get',
                    data: {},
                    success: function (res) {
                       // console.log(res);
                        ok(res);
                    }
                })
            })
            .then(function (ok, fail, data) {

                var convertData = [];
                var cdmData = [];
                var cnt =50
                var size =data.length;
               // var sum =data.length;





                $.each(data, function(key, item) {

                  //  var tempCnt =value:item

                    var data = { name: item.themaNm, weight:cnt+ (key+5),value:item.cnt};

                    convertData.push(data);

                });




                //
                themaInfoVisitCount.update({

                    series: [{
                        type: 'wordcloud',
                        data: convertData,
                        name: '방문수',


                    }],


                  //  series: convertData
                }, true, true);
            });
    }







    function searchData() {
        var data = {};
        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value="/cms/gsis/stats/getSelectDashboardMdis.do"/>', {
                    method: 'GET',
                    data: {},
                    success: function (res) {

                      //  console.log(res);

                        getSelectDashboardMdis.update({
                            series: [{
                                name: '마이크로파일',
                                data: res.map(function (x) {
                                  //  console.log(x.orignlFileNm)
                                   // console.log(x.cnt)

                                    return {
                                        name: x.orignlFileNm,
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
                    data: {siteId: 'jewfri-ko'},
                    success: function (res) {
                        getSiteMenuYearStats.update({
                            series: [{
                                name: '메뉴',
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

