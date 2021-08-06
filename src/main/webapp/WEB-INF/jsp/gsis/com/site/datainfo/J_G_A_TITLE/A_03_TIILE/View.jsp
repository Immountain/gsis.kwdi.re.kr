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
    var chartColor = [
        '#e94235', '#fabb04'
        ,'#34a853', '#4285f4'
        ,'#e94235', '#fabb04'
        ,'#34a853', '#4285f4'
        ,'#e94235', '#fabb04'
        , '#34a853', '#4285f4'
    ];
    function initChartEl() {
        chartView = Highcharts.chart('chartView', {
            chart: {
                zoomType: 'xy'
            },
            title: {
                text: '연령별 인구'
            },
            // subtitle: {
            //     text: '서브 타이틀'
            // },
            xAxis: [{
                categories: [
                    '0~4세', '5~9세', '10~14세', '15~19세', '20~24세', '25~29세', '30~34세', '35~39세', '40~44세', '45~49세', '50~54세', '55~59세', '60~64세', '65~69세', '70~74세', '75~79세', '80세이상'
                ],
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
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            plotOptions: {
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
            series: [{
                name: '여성',
                type: 'column',
                color: '#fabb04', //green
                yAxis: 0,
                data: [],

            }, {
                name: '남성',
                type: 'column',
                color: '#4285f4', //green
                yAxis: 0,
                data: [],

            },
            {
                name: '여성비율',
                type: 'spline',
                color: '#e94235', //green
                yAxis: 1,
                data: [],

            }]
        });
    }

    function Search() {
        var strYear ="";
        var endYear ="";
        var p = {
            strYear:strYear,endYear:endYear
        };


        $ifx.promise()
        .then(function (ok, fail, data) {
            $ifx.ajax('<c:url value='/site/gsis/a03/List.do' />', {
                method: "POST",
                data: JSON.stringify(p),
                success: function (res) {
                    var $tbody = $('.table-outline table tbody');
                    $tbody.empty();

                    var groupData = groupBy(res.list, 'dataYear');


                    var count = 0;
                    $.each(groupData, function(key, item) {




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
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData8']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData9']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData10']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData11']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData12']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData13']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData14']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData15']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData16']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData17']) || '' ) +  '</td>')
                            $tr.append('<td>' + ($ifx.numberComma(v['cdmData18']) || '' ) +  '</td>')
                            $tbody.append($tr);
                        })
                        console.log(count, Object.keys(groupData).length)
                        if(count == Object.keys(groupData).length -1) {
                            ok(item);
                        }
                        count++;
                    });
                }
            })
        })
        .then(function (ok, fail, data) {
            /**
             {dataYear: "2020", dataGb: "전체", cdmData1: "671316", cdmData2: "26128", cdmData3: "35316", …}
             {dataYear: "2020", dataGb: "여성", cdmData1: "333973", cdmData2: "12617", cdmData3: "17260", …}
             {dataYear: "2020", dataGb: "남성", cdmData1: "337343", cdmData2: "13511", cdmData3: "18056", …}
             {dataYear: "2020", dataGb: "여성 비율", cdmData1: "49.7", cdmData2: "48.2", cdmData3: "48.8", …}
             */




            chartView.update({
                series: [{
                    name: '여성',
                    type: 'column',
                    color: '#fabb04', //green
                    yAxis: 0,
                    data: function(){
                        var arr = [];
                        for(var i=2; i<=18; i++) arr.push(Number(data[1]['cdmData' + i]))
                        console.log(arr)
                        return arr
                    }(),

                }, {
                    name: '남성',
                    type: 'column',
                    color: '#4285f4', //green
                    yAxis: 0,
                    data: function(){
                        var arr = [];
                        for(var i=2; i<=18; i++) arr.push(Number(data[2]['cdmData' + i]))
                        return arr
                    }()
                },
                {
                    name: '여성비율',
                    type: 'spline',
                    color: '#e94235', //green
                    yAxis: 1,
                    data: function(){
                        var arr = [];
                        for(var i=2; i<=18; i++) arr.push(Number(data[3]['cdmData' + i]))
                        return arr
                    }()
                }]
            }, true, true);
            console.log(data)
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
                            <th>전체</th>
                            <th>0~4세</th>
                            <th>5~9세</th>
                            <th>10~14세</th>
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
                            <th>65~69세</th>
                            <th>70~74세</th>
                            <th>75~79세</th>
                            <th>80세이상</th>
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