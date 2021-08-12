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
                text: '연령별 경제활동인구 및 고용률'
            },
            xAxis: [{
                categories: ['15~19세', '20~24세', '25~29세', '30~34세', '35~39세', '40~44세', '45~49세', '50~54세', '55~59세','60~64세','65세이상'],
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
                    text: '(%)',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                }
            }, { // Secondary yAxis
                title: {
                    text: '(백명)',
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
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            legend: {
                align: 'center',
                verticalAlign: 'top',
                borderWidth: 0
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
            series: []
        });
    }


    function Search() {
        var strYear =$('#strDt').val();
        var endYear =$('#endDt').val();
        var p = {
            strYear:strYear,endYear:endYear
        };

        var groupArr = [];

        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value='/site/gsis/c02/List.do' />', {
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
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData2T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData3T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData4T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData5T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData6T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData7T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData8T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData9T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData10T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData11T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData12T']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData2']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData3']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData4']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData5']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData6']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData7']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData8']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData9']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData10']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData11']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData12']) || '' ) +  '</td>')



                                $tbody.append($tr);
                            })
                            // console.log(count, Object.keys(groupData).length)
                            if(count == Object.keys(groupData).length -1) {
                                ok(item);
                            }
                            count++;
                        });


                    }
                })
            })
            .then(function (ok, fail, data) {


                var cdmData1 = []; //경제활동인구(여성)
                var cdmData2 = [];//경제활동인구(남성)
                var cdmData3 = [];//고용률(여성)
                var cdmData4 = [];//고용률(남성)


                $.each(data, function(key, item) {

                    console.log(key,item);
                    if(item.dataGb=="여성"){


                        console.log(key,item.cdmData2T);



                        cdmData1.push(Number(item.cdmData2T))
                        cdmData1.push(Number(item.cdmData3T))
                        cdmData1.push(Number(item.cdmData4T))
                        cdmData1.push(Number(item.cdmData5T))
                        cdmData1.push(Number(item.cdmData6T))
                        cdmData1.push(Number(item.cdmData7T))
                        cdmData1.push(Number(item.cdmData8T))
                        cdmData1.push(Number(item.cdmData9T))
                        cdmData1.push(Number(item.cdmData10T))
                        cdmData1.push(Number(item.cdmData11T))
                        cdmData1.push(Number(item.cdmData12T))



                        cdmData3.push(Number(item.cdmData2))
                        cdmData3.push(Number(item.cdmData3))
                        cdmData3.push(Number(item.cdmData4))
                        cdmData3.push(Number(item.cdmData5))
                        cdmData3.push(Number(item.cdmData6))
                        cdmData3.push(Number(item.cdmData7))
                        cdmData3.push(Number(item.cdmData8))
                        cdmData3.push(Number(item.cdmData9))
                        cdmData3.push(Number(item.cdmData10))
                        cdmData3.push(Number(item.cdmData11))
                        cdmData3.push(Number(item.cdmData12))




                    }
                   else if(item.dataGb=="남성"){

                      //  for(var i=2; i<=12; i++) cdmData2.push(Number(data[1]['cdmData' + i]));
                       // for(var i=2; i<=12; i++) cdmData4.push(Number(data[1]['cdmDataT' + i]));

                        cdmData2.push(Number(item.cdmData2T))
                        cdmData2.push(Number(item.cdmData3T))
                        cdmData2.push(Number(item.cdmData4T))
                        cdmData2.push(Number(item.cdmData5T))
                        cdmData2.push(Number(item.cdmData6T))
                        cdmData2.push(Number(item.cdmData7T))
                        cdmData2.push(Number(item.cdmData8T))
                        cdmData2.push(Number(item.cdmData9T))
                        cdmData2.push(Number(item.cdmData10T))
                        cdmData2.push(Number(item.cdmData11T))
                        cdmData2.push(Number(item.cdmData12T))



                        cdmData4.push(Number(item.cdmData2))
                        cdmData4.push(Number(item.cdmData3))
                        cdmData4.push(Number(item.cdmData4))
                        cdmData4.push(Number(item.cdmData5))
                        cdmData4.push(Number(item.cdmData6))
                        cdmData4.push(Number(item.cdmData7))
                        cdmData4.push(Number(item.cdmData8))
                        cdmData4.push(Number(item.cdmData9))
                        cdmData4.push(Number(item.cdmData10))
                        cdmData4.push(Number(item.cdmData11))
                        cdmData4.push(Number(item.cdmData12))

                    }


                });

                chartView.update({
                    series: [{
                        name: '경제활동인구(여성)',
                        type: 'column',
                        yAxis: 0,
                        color: '#fae7d0', //green
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
                        name: '경제활동인구(남성)',
                        type: 'column',
                        yAxis: 0,
                        color: '#499cc0', //green
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


                    },
                        {
                            name: '고용률(여성)',
                            type: 'spline',
                            yAxis: 1,
                            color: '#ffb2b1', //green
                            data: cdmData3,
                            marker: {
                                lineWidth: 1,
                                lineColor: '#ffb2b1',
                                radius: 7,
                                symbol: 'circle'
                            }

                        },
                        {
                            name: '고용률(남성)',
                            type: 'spline',
                            yAxis: 1,
                            color: '#ddf1ff', //green
                            data: cdmData4,
                            marker: {
                                lineWidth: 1,
                                lineColor: '#ddf1ff',
                                radius: 7,
                                symbol: 'square'
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

                    <div class="text ${view.divCss}">
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
                    <span>단위 천명</span>
                </p>
                <div class="table-outline">
                    <table>
                        <thead>
                        <tr>
                            <th colspan="2" rowspan="2"></th>
                            <th colspan="11" >경제활동인구</th>
                            <th colspan="11" >고용율</th>

                        </tr>
                        </thead>
                        <thead>
                            <tr>
                                <th colspan="2">구분</th>
                                <th>15~19세</th>
                                <th>20~24세</th>
                                <th>25~29세</th>
                                <th>30~34세</th>
                                <th>35~39세</th>
                                <th>40~44세</th>
                                <th>45~49세</th>
                                <th>50~54세</th>
                                <th>55~59세</th>
                                <th>60~64세</th>
                                <th>65세이상</th>
                                <th>15~19세</th>
                                <th>20~24세</th>
                                <th>25~29세</th>
                                <th>30~34세</th>
                                <th>35~39세</th>
                                <th>40~44세</th>
                                <th>45~49세</th>
                                <th>50~54세</th>
                                <th>55~59세</th>
                                <th>60~64세</th>
                                <th>65세이상</th>

                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>

                <div class="information">

                    <c:out value="${fn:replace(viewFile.etc , crlf , '<br/>') }" escapeXml="false"/>

                </div>
                <input type="hidden" id="strDt" name="strDt" value="${viewFile.strDt}">
                <input type="hidden" id="endDt" name="endDt" value="${viewFile.endDt}">
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