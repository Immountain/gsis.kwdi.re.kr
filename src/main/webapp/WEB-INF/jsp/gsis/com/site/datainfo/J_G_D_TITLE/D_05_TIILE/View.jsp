<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<script src="<c:url value='/js/infomind/com/moment.js'/>"></script>
<script src="<c:url value='/js/infomind/com/incms.polyfill.js'/>"></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>"></script>

<!-- Highchart -->
<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/highcharts.src.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/Highcharts/code/css/highcharts.css"/>">



<%pageContext.setAttribute("crlf", "\r\n"); %>

<script type="text/javascript">
    $(document).ready(function(){

        initChartEl();
        Search();

    });


    var chartView;
    function initChartEl() {
        chartView = Highcharts.chart('chartView', {
            chart: {
                zoomType: 'xy'
            },
            lang: {
                thousandsSep: ','
            },
            title: {
                text: '출산전･후 휴가, 육아휴직, 육아기근로단축 수급자 현황'
            },
            xAxis: [{
                categories: [],
                crosshair: true
            }],
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
            }, { // Secondary yAxis
                title: {
                    text: '(%)',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                opposite: true,
                min: 0,
              //  tickInterval: 2,
                // tickWidth: 0,
                // gridLineWidth:1,
                // endOnTick:false,
                // startOnTick:false,
                // alignTicks:false,
            }],
            tooltip: {
                shared: true
            },
            plotOptions: {
                spline: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: true
                },
                column: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                align: 'center',
                verticalAlign: 'top',
                borderWidth: 0
            },
            series: []
        });
    }


    function Search() {
        var strYear ="";
        var endYear ="";
        var p = {
            strYear:strYear,endYear:endYear
        };

        var groupArr = [];
        var cdmData1 = []; //출산전 휴사
        var cdmData2 = [];//육아휴직(여)
        var cdmData3 = [];//육아기근로단축(여)
        var cdmData4 = [];//육아휴직(넘성비율)
        var cdmData5 = [];//육아기근로단축(넘성비율)


        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value='/site/gsis/d05/List.do' />', {
                    method: "POST",
                    data: JSON.stringify(p),
                    success: function (res) {
                        var $tbody = $('.table-outline table tbody');
                        $tbody.empty();

                        var groupData = groupBy(res.list, 'dataYear');

                        var count = 0;
                        $.each(groupData, function(key, item) {

                            //  console.log("-->",item[0].dataYear);
                            groupArr.push(item[0].dataYear);

                            item.forEach(function(v, i) {
                                var $tr = $('<tr />');
                                if(i == 0 ) {
                                    $tr.append($('<td />', {
                                        'rowspan': item.length,
                                        'text': key
                                    }))
                                }

                                $tr.append('<td>' + (v['dataGb'] || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData1']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData2']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData3']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData4']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData5']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData6']) || '' ) +  '</td>')


                                if(v['dataGb']=="전체"){

                                    cdmData1.push(Number(v['cdmData1']));


                                }else if(v['dataGb']=="여성"){

                                    cdmData2.push(Number(v['cdmData3']));
                                    cdmData3.push(Number(v['cdmData5']));

                                }else if(v['dataGb']=="남성비율"){

                                    cdmData4.push(Number(v['cdmData3']));
                                    cdmData5.push(Number(v['cdmData5']));
                                }



                                $tbody.append($tr);
                            })
                            // console.log(count, Object.keys(groupData).length)
                            if(count == Object.keys(groupData).length -1) {

                            }
                            count++;
                        });
                        ok(groupData);

                    }
                })
            })
            .then(function (ok, fail, data) {


                chartView.update({

                    xAxis: [{
                        categories: groupArr,
                        crosshair: true
                    }],


                    series: [{
                        name: '출산전후 휴가',
                        type: 'column',
                        yAxis: 0,
                        color: '#ace2ff', //green
                        data: cdmData1,
                        dataLabels: {//바 상단의 수치값 개별 지정.
                            enabled: true,
                            format: '{y}',//수치 표현 포맷
                            align: 'center',
                            verticalAlign: 'top',
                            //위치 지정
                            y: 10,
                        }



                    },{
                        name: '육아휴직(여)',
                        type: 'column',
                        yAxis: 0,
                        color: '#bca3cc', //green
                        pointPlacement: -0.1,
                        data: cdmData2,
                        dataLabels: {//바 상단의 수치값 개별 지정.
                            enabled: true,
                            format: '{y}',//수치 표현 포맷
                            align: 'center',
                            verticalAlign: 'top',
                            //위치 지정
                            y: 10,
                        }



                    },{
                        name: '육아기근로단축(여)',
                        type: 'column',
                        yAxis: 0,
                        color: '#c04848', //green
                        pointPlacement: -0.1,
                        data: cdmData3,
                        dataLabels: {//바 상단의 수치값 개별 지정.
                            enabled: true,
                            format: '{y}',//수치 표현 포맷
                            align: 'center',
                            verticalAlign: 'top',
                            //위치 지정
                            y: 10,
                            }
                        },
                        {
                            name: '육아휴직(남성비율)',
                            type: 'spline',
                            yAxis: 1,
                            color: '#f5c082', //green
                            data: cdmData3,
                            marker: {
                                lineWidth: 1,
                                lineColor: '#f5c082',
                                fillColor: 'white',
                                radius: 7,
                                symbol: 'circle'
                            }

                        },
                        {
                            name: '육아기근로단축(남성 비율)',
                            type: 'spline',
                            yAxis: 1,
                            color: '#499cc0', //green
                            data: cdmData4,
                            marker: {
                                lineWidth: 1,
                                lineColor: '#499cc0',
                                fillColor: 'white',
                                radius: 7,
                                symbol: 'circle'
                            }

                        }

                    ]
                }, true, true);
                // console.log(data)
            })
        ;
    }

    function groupBy (data, key) {
        return data.reduce(function (carry, el) {
            var group = el[key];
            if (carry[group] === undefined) {
                carry[group] = []
            }
            carry[group].push(el)
            return carry
        }, {})
    }



</script>
<div id="content" class="sub">


    <section class="sub-navigation">
        <div class="container">

            <a class="home" href="/">홈으로 <i class='bx bxs-home'></i></a>

            <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>

        </div>
    </section>


    <section class="sub-content">
        <div class="container">

            <h4 class="stitle">${view.themaTitle}</h4>

            <article class="theme-view">

                <div class="head">
                    <h5>
                        ${viewFile.themaTitle}
                    </h5>

                    <div class="text category01">
                        <!-- category 1 ~ 6 까지 클래스명 적용 -->

                        ${viewFile.themaSubTitle}


                        <p class="info">

                            <c:out value="${fn:replace(viewFile.txtContent , crlf , '<br/>') }" escapeXml="false"/>

                        </p>
                    </div>
                </div>

                <div class="chart">
                    <!-- 챠트영역 -->

                    <div id="chartView"></div>
                </div>


                <p class="info">
                    좌우터치로 스크롤 가능합니다.
                    <span>단위 %</span>
                </p>
                <div class="table-outline">
                    <table>
                        <thead>
                        <tr>
                            <th colspan="2">구분</th>

                            <th>출산전후 휴가급여 인원</th>
                            <th>출산전후 휴가급여 지급액</th>
                            <th>육아휴직급여 인원</th>
                            <th>육아휴직급여 지급액</th>
                            <th>육아기근단축급여 인원</th>
                            <th>육아기근단축급여 지급액</th>

                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>

                <div class="information">

                    <c:out value="${fn:replace(viewFile.etc , crlf , '<br/>') }" escapeXml="false"/>

                </div>

            </article>


        </div>
    </section>
</div>
<!-- E:mainContent -->





<!-- S:popup -->
<div class="layer-pop">
    <div class="outline">

        <!--
            최종 링크에 맞는 아이프레임 호출
        -->

        팝업창


        <button class="close">닫기 <i class='bx bx-x' ></i></button>

    </div>
</div>
<!-- E:popup -->