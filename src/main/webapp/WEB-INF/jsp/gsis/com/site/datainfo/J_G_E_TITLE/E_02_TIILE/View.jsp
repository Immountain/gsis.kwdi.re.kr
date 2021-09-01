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
                type: 'line'
            },
            title: {
                text: '제7회 전국지방동시선거 후보'
            },
            // subtitle: {
            //     text: '서브제목'
            // },
            xAxis: [{
                categories: ['시도지사','교육감','시도의회 의원','비례대표'],
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
                    text: '명',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },

            }, { // Secondary yAxis
                title: {
                    text: '%',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    },
                    // formatter: function() {
                    //     return parseInt((this.value  * 100 ) + 2);
                    // }
                },
                opposite: true,
                min: 0,

            }],
            tooltip: {

                shared: true

            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                },
                column: {
                    // stacking: 'normal',
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
                type: 'line'
            },
            title: {
                text: '제7회 전국지방동시선거 당선인'
            },
            // subtitle: {
            //     text: '서브제목'
            // },
            xAxis: [{
                categories: ['시도지사','교육감','시도의회 의원','비례대표'],
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
                    text: '명',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                 min: 0,


            }, { // Secondary yAxis
                title: {
                    text: '%',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    },
                    // formatter: function() {
                    //     return parseInt((this.value  * 100 ) + 2);
                    // }
                },
                opposite: true,
                min: 0,

            }],
            tooltip: {

                shared: true

            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                },
                column: {
                    // stacking: 'normal',
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
        var strYear =$('#strDt').val();
        var endYear =$('#endDt').val();
        var p = {
            strYear:strYear,endYear:endYear
        };

        var groupArr = [];



        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value='/site/gsis/e02/List.do' />', {
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

                                $tr.append('<td>' + (v['cdmData1'] || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['dataGb']) || '' ) +  '</td>')
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
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData13']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData14']) || '' ) +  '</td>')
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData15']) || '' ) +  '</td>')



                                // if(v['dataGb']=="여성"){
                                //
                                //       cdmData1.push(Number(v['cdmData1']));
                                //       cdmData2.push(Number(v['cdmData2']));
                                //  }else if(v['dataGb']=="남성"){
                                //
                                //     cdmData3.push(Number(v['cdmData1']));
                                //     cdmData4.push(Number(v['cdmData2']));
                                // }else if(v['dataGb']=="여성비율"){
                                //
                                //     cdmData5.push(Number(v['cdmData1']));
                                //     cdmData6.push(Number(v['cdmData2']));
                                // }
                                //


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
             //   console.log(data)
              //  console.log(cdmData1)

                var cdmData1 = []; //여성 후보
                var cdmData2 = [];//남성 후보
                var cdmData3 = [];//여성 비율 후보


                var cdmData4 = [];//여성 당선인
                var cdmData5 = [];//남성 당선인
                var cdmData6 = [];//여성비율 당성인


                $.each(data, function(key, item) {




                    if(item.dataGb=="여성"){

                        cdmData1.push(Number(item.cdmData3))
                        cdmData1.push(Number(item.cdmData4))
                        cdmData1.push(Number(item.cdmData5))
                        cdmData1.push(Number(item.cdmData8))




                        cdmData4.push(Number(item.cdmData10))
                        cdmData4.push(Number(item.cdmData11))
                        cdmData4.push(Number(item.cdmData12))
                        cdmData4.push(Number(item.cdmData15))




                    }

                    if(item.dataGb=="남성"){

                        cdmData2.push(Number(item.cdmData3))
                        cdmData2.push(Number(item.cdmData4))
                        cdmData2.push(Number(item.cdmData5))
                        cdmData2.push(Number(item.cdmData8))


                        cdmData5.push(Number(item.cdmData10))
                        cdmData5.push(Number(item.cdmData11))
                        cdmData5.push(Number(item.cdmData12))
                        cdmData5.push(Number(item.cdmData15))


                    }

                    if(item.dataGb=="여성비율"){

                        cdmData3.push(Number(item.cdmData3))
                        cdmData3.push(Number(item.cdmData4))
                        cdmData3.push(Number(item.cdmData5))
                        cdmData3.push(Number(item.cdmData8))


                        cdmData6.push(Number(item.cdmData10))
                        cdmData6.push(Number(item.cdmData11))
                        cdmData6.push(Number(item.cdmData12))
                        cdmData6.push(Number(item.cdmData15))

                    }





                });







                chartView.update({

                    // xAxis: [{
                    //     categories: groupArr,
                    //     crosshair: true
                    // }],


                    series: [{
                        name: '여성',
                        type: 'column',
                        yAxis: 0,
                        color: '#9f5b4a', //green
                        data: cdmData1,


                    },{
                        name: '남성',
                        type: 'column',
                        yAxis: 0,
                        color: '#45ccd7', //green
                        data: cdmData2,


                    },
                        {
                            name: '여성비율',
                            type: 'line',
                            yAxis: 1,
                            color: '#e0d45b', //green
                            data: cdmData3,
                            marker: {
                                lineWidth: 1,
                                lineColor: Highcharts.getOptions().colors[4],
                                fillColor: 'white',
                                radius: 7,
                                symbol: 'circle'
                            }

                        }

                    ]
                }, true, true);


                chartView2.update({

                    // xAxis: [{
                    //     categories: groupArr,
                    //     crosshair: true
                    // }],
                    //

                    series: [{
                        name: '여성',
                        type: 'column',
                        yAxis: 0,
                        color: '#9f5b4a', //green
                        data: cdmData4,


                    },{
                        name: '남성',
                        type: 'column',
                        yAxis: 0,
                        color: '#45ccd7', //green
                        data: cdmData5,


                    },
                        {
                            name: '여성비율',
                            type: 'line',
                            yAxis: 1,
                            color: '#e0d45b', //green
                            data: cdmData6,
                            marker: {
                                lineWidth: 1,
                                lineColor: Highcharts.getOptions().colors[4],
                                fillColor: 'white',
                                radius: 7,
                                symbol: 'circle'
                            }

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

                <div class="chart count2">
                    <!-- 챠트영역 -->

                    <div id="chartView"></div>

                </div>

                <div class="chart count2">
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
                            <th>구분</th>
                            <th>후보자 수</th>
                            <th>시도지사</th>
                            <th>교육감</th>
                            <th>시도의회의원</th>
                            <th>시도의회의원<br>(지역구)</th>
                            <th>시도의회의원<br>(교육위원)</th>
                            <th>비례대표</th>
                            <th>당선인 수</th>
                            <th>시도지사</th>
                            <th>교육감</th>
                            <th>시도의회의원</th>
                            <th>시도의회의원<br>(지역구)</th>
                            <th>시도의회의원<br>(교육위원)</th>
                            <th>비례대표</th>

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