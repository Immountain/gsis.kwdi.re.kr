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
        Search2();

    });


    var chartView;
    var chartView2;
    var chartView3;
    var chartView4;
    function initChartEl() {
        chartView = Highcharts.chart('chartView', {
            chart: {
                type: 'line'
            },
            lang: {
                thousandsSep: ','
            },
            title: {
                text: '야간보행에 대한 두려움(2018)'
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
                text: '야간보행 불안함(2020)'
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


        chartView3 = Highcharts.chart('chartView3', {
            chart: {
                zoomType: 'xy'
            },
            lang: {
                thousandsSep: ','
            },
            title: {
                text: '야간보행 두려운 이유(2018)'
            },
            xAxis: [{
                categories: ['가로등이 없어서','인적이 드물어서','우범지역이므로','CCTV가 없어서','기타'],
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


        chartView4 = Highcharts.chart('chartView4', {
            chart: {
                zoomType: 'xy'
            },
            lang: {
                thousandsSep: ','
            },
            title: {
                text: '야간보행 불안한 이유(2020)'
            },
            xAxis: [{
                categories: ['생활권 내에 보행안전 시설 부족','생활권 내에 보행안전 시설 부족','생활권 내에 인적 드묾','생활권 내에 우범 지역 존재','신문, 뉴스 등에서 사건, 사고 자주 접해서','기타'],
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
        var cdmData1 = []; //여성
        var cdmData2 = [];//남성
        var cdmData3 = [];//남성
        var cdmData4 = [];//남성

        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value='/site/gsis/f02/List2.do' />', {
                    method: "POST",
                    data: JSON.stringify(p),
                    success: function (res) {
                        var $tbody = $('div[data-id=outline1] table tbody');
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
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData7']) || '' ) +  '</td>')


                                if(v['dataGb']=="여성"){
                                   // cdmData1.push(Number(item.cdmData1))
                                    cdmData1.push(Number(v['cdmData1']))


                                    cdmData3.push(Number(v['cdmData1']))
                                    cdmData3.push(Number(v['cdmData2']))
                                    cdmData3.push(Number(v['cdmData3']))
                                    cdmData3.push(Number(v['cdmData4']))
                                    cdmData3.push(Number(v['cdmData5']))
                                    cdmData3.push(Number(v['cdmData6']))
                                    cdmData3.push(Number(v['cdmData7']))


                                }


                                if(v['dataGb']=="남성"){
                                    cdmData2.push(Number(v['cdmData1']))



                                    cdmData4.push(Number(v['cdmData1']))
                                    cdmData4.push(Number(v['cdmData2']))
                                    cdmData4.push(Number(v['cdmData3']))
                                    cdmData4.push(Number(v['cdmData4']))
                                    cdmData4.push(Number(v['cdmData5']))
                                    cdmData4.push(Number(v['cdmData6']))
                                    cdmData4.push(Number(v['cdmData7']))

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




                $.each(data, function(key, item) {






                });




                chartView2.update({

                    xAxis: [{
                        categories: groupArr,
                        crosshair: true
                    }],


                    series: [{
                        name: '여성',
                        type: 'column',
                        yAxis: 0,
                        color: '#d7801e', //green
                        data: cdmData1,
                    },{
                        name: '남성',
                        type: 'column',
                        yAxis: 0,
                        color: '#8994d7', //green
                        data: cdmData2,

                    }

                    ]
                }, true, true);



                chartView4.update({
                    series: [{
                        name: '여성',
                        type: 'column',
                        yAxis: 0,
                        color: '#920fd7', //green
                        data: cdmData3,
                    },{
                        name: '남성',
                        type: 'column',
                        yAxis: 0,
                        color: '#d78dd4', //green
                        data: cdmData4,

                    }

                    ]
                }, true, true);



            }) ;

    }




    function Search2() {
        var strYear ="";
        var endYear ="";
        var p = {
            strYear:strYear,endYear:endYear
        };

        var groupArr = [];
        var cdmData1 = []; //여성
        var cdmData2 = [];//남성


        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value='/site/gsis/f02/List.do' />', {
                    method: "POST",
                    data: JSON.stringify(p),
                    success: function (res) {
                        var $tbody = $('div[data-id=outline2] table tbody');
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
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData7']) || '' ) +  '</td>')



                                if(v['dataGb']=="여성"){
                                    cdmData1.push(Number(v['cdmData1']))




                                }
                                if(v['dataGb']=="남성"){
                                    cdmData2.push(Number(v['cdmData1']))


                                }


                                $tbody.append($tr);
                            })
                            // console.log(count, Object.keys(groupData).length)
                            if(count == Object.keys(groupData).length -1) {
                                ok(item);
                            }
                            count++;
                        });




                      //  ok(groupData);



                    }
                })
            })
            .then(function (ok, fail, data) {

                var cdmData3 = [];//남성
                var cdmData4 = [];//남성


                $.each(data, function(key, item) {


                    if(item.dataGb=="여성"){

                        cdmData3.push(Number(item.cdmData2))
                        cdmData3.push(Number(item.cdmData3))
                        cdmData3.push(Number(item.cdmData4))
                        cdmData3.push(Number(item.cdmData5))
                        cdmData3.push(Number(item.cdmData6))




                    }
                    if(item.dataGb=="남성"){

                        cdmData4.push(Number(item.cdmData2))
                        cdmData4.push(Number(item.cdmData3))
                        cdmData4.push(Number(item.cdmData4))
                        cdmData4.push(Number(item.cdmData5))
                        cdmData4.push(Number(item.cdmData6))
                     }


                });



                chartView.update({

                    xAxis: [{
                        categories: groupArr,
                        crosshair: true
                    }],

                    series: [{
                        name: '여성',
                        data: cdmData1,
                        color: '#d76c2b', //green
                        type: 'spline',
                    }, {
                        name: '남성',
                        data: cdmData2,
                        color: '#a49cd7', //green
                        type: 'spline',
                    }],







                }, true, true);



                chartView3.update({
                    series: [{
                        name: '여성',
                        type: 'column',
                        yAxis: 0,
                        color: '#920fd7', //green
                        data: cdmData3,
                    },{
                        name: '남성',
                        type: 'column',
                        yAxis: 0,
                        color: '#d78dd4', //green
                        data: cdmData4,

                    }

                    ]
                }, true, true);



            }) ;

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

                <div class="chart dual left">
                    <!-- 챠트영역 -->

                    <div id="chartView3"></div>
                </div>

                <div class="chart dual right">
                    <!-- 챠트영역 -->

                    <div id="chartView4"></div>
                </div>



                <p class="info">
                    좌우터치로 스크롤 가능합니다.
                    <span>단위 %</span>
                </p>
                <div class="table-outline"   data-id="outline1">
                    <table>
                        <thead>
                        <tr>
                            <th colspan="2">구분</th>
                             <th>야간에 혼자<br> 걷기 불안함</th>
                             <th>생활권 내에<br> 보행안전 시설 부족</th>
                             <th>생활권 내에<br> 치안 시설 부족</th>
                             <th>생활권 내에<br> 인적 드묾</th>
                             <th>생활권 내에<br> 우범 지역 존재</th>
                             <th>신문, 뉴스 등에서 사건<br> 사고 자주 접해서</th>
                             <th>기타</th>

                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>




                <div class="table-outline" data-id="outline2">
                    <table>
                        <thead>
                        <tr>
                            <th colspan="2">구분</th>
                            <th>야간에 혼자 걷기<br> 두려운 곳이 있음</th>
                            <th>가로등이 없어서</th>
                            <th>인적이 드물어서</th>
                            <th>우범지역이므로</th>
                            <th>CCTV가 없어서</th>
                            <th>기타</th>
                            <th>두려운 곳이 없음</th>


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