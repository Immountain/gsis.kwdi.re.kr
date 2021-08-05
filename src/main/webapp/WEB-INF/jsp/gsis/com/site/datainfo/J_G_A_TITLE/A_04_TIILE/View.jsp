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
                text: '제목'
            },
            subtitle: {
                text: '서브제목'
            },
            xAxis: [{
                categories: ['2010', '2011', '2012', '2013', '2015', '2016', '2017', '2018', '2019'],
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
                    text: '왼쪽',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                }
            }, { // Secondary yAxis
                title: {
                    text: '오른쪽',
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
                align: 'right',
                x: -30,
                verticalAlign: 'top',
                y: 25,
                floating: true,
                backgroundColor:
                    Highcharts.defaultOptions.legend.backgroundColor || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            series: [{
                name: '출생아수',
                type: 'column',
                yAxis: 0,
                color: '#989f3b', //green
                data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4],


            },{
                    name: '사망자수',
                    type: 'column',
                    yAxis: 0,
                    color: '#b3d78b', //green
                    pointPlacement: -0.1,
                    data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4],


                },
                {
                    name: '조출생률',
                    type: 'spline',
                    yAxis: 1,
                    color: '#acbae0', //green
                    data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3],
                    marker: {
                        lineWidth: 1,
                        lineColor: Highcharts.getOptions().colors[4],
                        fillColor: 'white',
                        radius: 7,
                        symbol: 'square'
                    }

                },
                {
                    name: '조사망률',
                    type: 'spline',
                    yAxis: 1,
                    color: '#f40d14', //green
                    data: [1.0, 2.9, 3.5, 10.5, 12.2, 18.5, 23.2, 23.5, 10.3],
                    marker: {
                        lineWidth: 1,
                        lineColor: Highcharts.getOptions().colors[5],
                        fillColor: 'white',
                        radius: 7,
                        symbol: 'circle'
                    }

                }

            ]
        });

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
                            <th>출생건수</th>
                            <th>조출생률</th>
                            <th>사망건수</th>
                            <th>조사망률</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td rowspan="3">2014</td>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>


                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>

                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>

                        </tr>
                        <tr>
                            <td rowspan="3">2014</td>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td rowspan="3">2014</td>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td rowspan="3">2014</td>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
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