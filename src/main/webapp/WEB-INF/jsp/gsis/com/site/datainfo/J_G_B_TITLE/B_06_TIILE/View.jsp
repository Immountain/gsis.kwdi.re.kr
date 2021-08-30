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
                type: 'column'
            },
            title: {
                text: ''
            },
            // subtitle: {
            //     text: '서브제목'
            // },
            xAxis: [{
                categories: ['초등학교','중학교','고등학교'],
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
                    text: '%',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                min: 0,


            }],
            tooltip: {

                shared: true

            },
            plotOptions: {
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
        var strYear =$('#strDt').val();
        var endYear =$('#endDt').val();
        var p = {
            strYear:strYear,endYear:endYear
        };

        var groupArr = [];



        $ifx.promise()
            .then(function (ok, fail, data) {
                $ifx.ajax('<c:url value='/site/gsis/b06/List.do' />', {
                    method: "POST",
                    data: JSON.stringify(p),
                    success: function (res) {
                        var $tbody = $('.table-outline table tbody');
                        $tbody.empty();

                        var groupData = groupBy(res.list, 'dataYear');

                        var count = 0;
                        $.each(groupData, function(key, item) {

                          //  console.log("-->",item[0].dataYear);
                        //    groupArr.push(item[0].dataYear);

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



                                // if(v['dataGb']=="여성"){
                                //
                                //       cdmData1.push(Number(v['cdmData1']));
                                //       cdmData2.push(Number(v['cdmData2']));
                                //
                                //
                                //
                                //  }else if(v['dataGb']=="여성 비율"){
                                //
                                //
                                //     cdmData3.push(Number(v['cdmData1']));
                                //     cdmData4.push(Number(v['cdmData2']));
                                // }



                                $tbody.append($tr);
                            })
                           // console.log(count, Object.keys(groupData).length)
                            if(count == Object.keys(groupData).length -1) {

                                ok(item);
                            }
                            count++;
                        });
                       // ok(groupData);

                    }
                })
            })
            .then(function (ok, fail, data) {
             //   console.log(data)
              //  console.log(cdmData1)

                var cdmData1 = []; //전체
                var cdmData2 = [];//교장
                var cdmData3 = [];//교감
                var cdmData4 = [];//수석교사
                var cdmData5 = [];//보직교사
                var cdmData6 = [];//평교사
                var title ="초･중･고등학교의 직위별 여성교원 비율";
                var year ="";

                $.each(data, function(key, item) {

                    year =item.dataYear


                    if(item.cdmData1=="초등학교"){
                        if(item.dataGb =="여성비율"){

                            cdmData1.push(Number(item.cdmData7));
                            cdmData2.push(Number(item.cdmData2));
                            cdmData3.push(Number(item.cdmData3));
                            cdmData4.push(Number(item.cdmData4));
                            cdmData5.push(Number(item.cdmData5));
                            cdmData6.push(Number(item.cdmData6));
                        }


                    }else if(item.cdmData1=="중학교"){


                        if(item.dataGb =="여성비율"){

                            cdmData1.push(Number(item.cdmData7));
                            cdmData2.push(Number(item.cdmData2));
                            cdmData3.push(Number(item.cdmData3));
                            cdmData4.push(Number(item.cdmData4));
                            cdmData5.push(Number(item.cdmData5));
                            cdmData6.push(Number(item.cdmData6));
                        }





                    }else if(item.cdmData1=="고등학교"){


                        if(item.dataGb =="여성비율"){

                            cdmData1.push(Number(item.cdmData7));
                            cdmData2.push(Number(item.cdmData2));
                            cdmData3.push(Number(item.cdmData3));
                            cdmData4.push(Number(item.cdmData4));
                            cdmData5.push(Number(item.cdmData5));
                            cdmData6.push(Number(item.cdmData6));
                        }


                    }


                });






                chartView.update({


                    title: {
                        text: title+"("+year+")"
                    },

                    // xAxis: [{
                    //     categories: groupArr,
                    //     crosshair: true
                    // }],


                    series: [{
                        name: '전체',
                        type: 'column',
                        yAxis: 0,
                        color: '#23349f', //green
                        data: cdmData1,


                    },{
                        name: '교장',
                        type: 'column',
                        yAxis: 0,
                        color: '#d7750f', //green
                        data: cdmData2,

                    },{
                        name: '교감',
                        type: 'column',
                        yAxis: 0,
                        color: '#97d3d7', //green
                        data: cdmData3,

                    },{
                        name: '수석교사',
                        type: 'column',
                        yAxis: 0,
                        color: '#d7ae11', //green
                        data: cdmData4,

                    },{
                        name: '보직교사',
                        type: 'column',
                        yAxis: 0,
                        color: '#5132d7', //green
                        data: cdmData5,

                    },{
                        name: '평교사',
                        type: 'column',
                        yAxis: 0,
                        color: '#35d71e', //green
                        data: cdmData6,

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
                    <span>단위 명, %</span>
                </p>
                <div class="table-outline">
                    <table>
                        <thead>
                        <tr>
                            <th>년도</th>
                            <th>학교</th>
                            <th>구분</th>
                            <th>교장</th>
                            <th>교감</th>
                            <th>수석교사</th>
                            <th>보직교사</th>
                            <th>평교사</th>
                            <th>계</th>
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