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
                text: '농가인구'
            },
            // subtitle: {
            //     text: '서브제목'
            // },
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
                    text: '명',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                min: 0,
                tickInterval: 2000,
                // tickWidth: 0,
                // gridLineWidth:1,
                // reversed:false,
                // visible:true,
                // endOnTick:false,
                // startOnTick:false,
                // alignTicks:false,

              //  lineWidth: 5,

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
                type: 'line'
            },
            title: {
                text: '어가인구'
            },
            // subtitle: {
            //     text: '서브제목'
            // },
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
                    text: '명',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },

                min: 0,
                tickInterval: 2000,

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
                tickInterval: 10,
                tickWidth: 0,
                gridLineWidth:1,
                endOnTick:false,
                startOnTick:false,
                alignTicks:false,
                // lineWidth: 5,
                // ceiling: 100,
                // floor: 10
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
        var strYear =$('#strDt').val();
        var endYear =$('#endDt').val();
        var p = {
            strYear:strYear,endYear:endYear
        };

        var groupArr = [];
        var cdmData1 = []; //여성 농가인구
        var cdmData2 = [];//여성 어가인구
        var cdmData3 = [];//남성 농가인구
        var cdmData4 = [];//남성 어가인구


        var cdmData5 = [];//여성 농가인구 비율
        var cdmData6 = [];//여성 농가인구 비율


        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value='/site/gsis/a02/List.do' />', {
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


                                if(v['dataGb']=="여성"){

                                      cdmData1.push(Number(v['cdmData1']));
                                      cdmData2.push(Number(v['cdmData2']));
                                 }else if(v['dataGb']=="남성"){

                                    cdmData3.push(Number(v['cdmData1']));
                                    cdmData4.push(Number(v['cdmData2']));
                                }else if(v['dataGb']=="여성비율"){

                                    cdmData5.push(Number(v['cdmData1']));
                                    cdmData6.push(Number(v['cdmData2']));
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
             //   console.log(data)
              //  console.log(cdmData1)

                chartView.update({

                    xAxis: [{
                        categories: groupArr,
                        crosshair: true
                    }],


                    series: [{
                        name: '여성',
                        type: 'column',
                        yAxis: 0,
                        color: '#989f3b', //green
                        data: cdmData1,


                    },{
                        name: '남성',
                        type: 'column',
                        yAxis: 0,
                        color: '#b3d78b', //green
                        data: cdmData3,


                    },
                        {
                            name: '여성비율',
                            type: 'spline',
                            yAxis: 1,
                            color: '#acbae0', //green
                            data: cdmData5,
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

                    xAxis: [{
                        categories: groupArr,
                        crosshair: true
                    }],


                    series: [{
                        name: '여성',
                        type: 'column',
                        yAxis: 0,
                        color: '#989f3b', //green
                        data: cdmData2,


                    },{
                        name: '남성',
                        type: 'column',
                        yAxis: 0,
                        color: '#b3d78b', //green
                        data: cdmData4,


                    },
                        {
                            name: '여성비율',
                            type: 'spline',
                            yAxis: 1,
                            color: '#acbae0', //green
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
                    <span>단위 가구, 명, %</span>
                </p>
                <div class="table-outline">
                    <table>
                        <thead>
                        <tr>
                            <th >년도</th>
                            <th >구분</th>
                            <th>농가인구</th>
                            <th>어가인구</th>
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