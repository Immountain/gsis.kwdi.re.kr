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
                text: '연령별 취업자'
            },
            xAxis: [{
                categories: ['15~19세','20~29세','30~39세','40~49세','50~59세','60세 이상'],
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
        var strYear ="";
        var endYear ="";
        var p = {
            strYear:strYear,endYear:endYear
        };

        var groupArr = [];



        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value='/site/gsis/c05/List.do' />', {
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
                                $tr.append('<td>' + ($ifx.numberComma(v['cdmData7']) || '' ) +  '</td>')

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


                var cdmData1 = []; //여자
                var cdmData2 = [];//남자


                $.each(data, function(key, item) {




                    if(item.dataGb=="여자"){

                        cdmData1.push(Number(item.cdmData2))
                        cdmData1.push(Number(item.cdmData3))
                        cdmData1.push(Number(item.cdmData4))
                        cdmData1.push(Number(item.cdmData5))
                        cdmData1.push(Number(item.cdmData6))
                        cdmData1.push(Number(item.cdmData7))


                    }

                    if(item.dataGb=="남자"){

                        cdmData2.push(Number(item.cdmData2))
                        cdmData2.push(Number(item.cdmData3))
                        cdmData2.push(Number(item.cdmData4))
                        cdmData2.push(Number(item.cdmData5))
                        cdmData2.push(Number(item.cdmData6))
                        cdmData2.push(Number(item.cdmData7))

                    }



                });




                chartView.update({

                    // xAxis: [{
                    //     categories: groupArr,
                    //     crosshair: true
                    // }],


                    series: [{
                        name: '여자',
                        type: 'column',
                        yAxis: 0,
                        color: '#ff5232', //green
                        data: cdmData1,
                         },{
                        name: '남자',
                        type: 'column',
                        yAxis: 0,
                        color: '#405eff', //green
                        data: cdmData2,
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
                            <th>계</th>
                            <th>15~19세</th>
                            <th>20~29세</th>
                            <th>30~39세</th>
                            <th>40~49세</th>
                            <th>50~59세</th>
                            <th>60세 이상</th>
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