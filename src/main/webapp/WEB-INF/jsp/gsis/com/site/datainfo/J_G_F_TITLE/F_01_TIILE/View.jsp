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
    var chartView2;
    function initChartEl() {
        chartView = Highcharts.chart('chartView', {
            chart: {
                zoomType: 'xy'
            },
            lang: {
                thousandsSep: ','
            },
            title: {
                text: '여성'
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
                    text: '',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                }
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
                    stacking: 'normal',
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


        chartView2 = Highcharts.chart('chartView2', {
            chart: {
                zoomType: 'xy'
            },
            lang: {
                thousandsSep: ','
            },
            title: {
                text: '남자'
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
                    text: '',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                }
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
                    stacking: 'normal',
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
        var cdmData1 = []; //여성 안전
        var cdmData2 = [];//여성 보통
        var cdmData3 = [];//여성 불안



        var cdmData4 = []; //남성 안전
        var cdmData5 = [];//남성 보통
        var cdmData6 = [];//남성 불안

        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value='/site/gsis/f01/List.do' />', {
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





                                if(v['dataGb']=="여자"){

                                    cdmData1.push(Number(v['cdmData1']));
                                    cdmData2.push(Number(v['cdmData2']));
                                    cdmData3.push(Number(v['cdmData3']));

                                }else if(v['dataGb']=="남자"){

                                    cdmData4.push(Number(v['cdmData1']));
                                    cdmData5.push(Number(v['cdmData2']));
                                    cdmData6.push(Number(v['cdmData3']));
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
                        name: '안전',
                        type: 'column',
                        yAxis: 0,
                        color: '#ff8004', //green
                        data: cdmData1,
                        dataLabels: {//바 상단의 수치값 개별 지정.
                            enabled: true,
                            format: '{y}',//수치 표현 포맷
                            align: 'center',
                            verticalAlign: 'top',
                            //위치 지정
                           // y: 10,
                        },



                    },{
                        name: '보통',
                        type: 'column',
                        yAxis: 0,
                        color: '#cca026', //green
                        data: cdmData2,
                        dataLabels: {//바 상단의 수치값 개별 지정.
                            enabled: true,
                            format: '{y}',//수치 표현 포맷
                            align: 'center',
                            verticalAlign: 'top',
                            //위치 지정
                           // y: 10,
                        },
                      },{
                        name: '불안',
                        type: 'column',
                        yAxis: 0,
                        color: '#ccb874', //green
                        data: cdmData3,
                        dataLabels: {//바 상단의 수치값 개별 지정.
                            enabled: true,
                            format: '{y}',//수치 표현 포맷
                            align: 'center',
                            verticalAlign: 'top',
                            //위치 지정
                           // y: 10,
                        },
                    }

                    ]
                }, true, true);


                chartView2.update({

                    xAxis: [{
                        categories: groupArr,
                        crosshair: true
                    }],


                    series: [{
                        name: '안전',
                        type: 'column',
                        yAxis: 0,
                        color: '#1d2bff', //green
                        data: cdmData4,
                        dataLabels: {//바 상단의 수치값 개별 지정.
                            enabled: true,
                            format: '{y}',//수치 표현 포맷
                            align: 'center',
                            verticalAlign: 'top',
                            //위치 지정
                           // y: 10,
                        },



                    },{
                        name: '보통',
                        type: 'column',
                        yAxis: 0,
                        color: '#5a7ecc', //green
                        data: cdmData5,
                        dataLabels: {//바 상단의 수치값 개별 지정.
                            enabled: true,
                            format: '{y}',//수치 표현 포맷
                            align: 'center',
                            verticalAlign: 'top',
                            //위치 지정
                           // y: 10,
                        },
                     },{
                        name: '불안',
                        type: 'column',
                        yAxis: 0,
                        color: '#11aecc', //green
                        data: cdmData6,
                        dataLabels: {//바 상단의 수치값 개별 지정.
                            enabled: true,
                            format: '{y}',//수치 표현 포맷
                            align: 'center',
                            verticalAlign: 'top',
                            //위치 지정
                            //y: 10,
                        },
                    }

                    ]
                }, true, true);

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

                    <div class="text ${view.divCss}">
                        <!-- category 1 ~ 6 까지 클래스명 적용 -->

                        ${viewFile.themaSubTitle}


                        <p class="info">

                            <c:out value="${fn:replace(viewFile.txtContent , crlf , '<br/>') }" escapeXml="false"/>

                        </p>
                    </div>
                </div>

                <div class="chart dual left">
                    <!-- 챠트영역 -->

                    <div id="chartView"></div>
                </div>

                <div class="chart dual right">
                    <!-- 챠트영역 -->

                    <div id="chartView2"></div>
                </div>


                <p class="info">
                    좌우터치로 스크롤 가능합니다.
                    <span>단위 명, %</span>
                </p>
                <div class="table-outline">
                    <table>
                        <thead>
                        <tr>
                            <th colspan="2">구분</th>
                             <th>안전</th>
                             <th>보통</th>
                             <th>불안</th>

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