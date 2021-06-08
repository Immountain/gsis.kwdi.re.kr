<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="pageTitle">사이트 방문 통계</c:set>

<script type="text/javascript">
$(document).ready(function() {
	generateChart()
	searchData()
});
var getSiteMenuStats, getSiteMenuBrowserStats, getSiteMenuDayStats, getSiteMenuMonthStats, getSiteMenuYearStats = null;
function generateChart() {
    getSiteMenuStats = Highcharts.chart('getSiteMenuStats', {
        credits: {
            enabled: false
        },
        chart: {
            height: '400px',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                colors: [],
                dataLabels: {
                    enabled: true,
                    color: '#555',
                    format: '{point.name} {point.percentage:,.1f}%',
                    distance: -60,
                    style: {
                        fontWeight: 400,
                        textOutline: 0,
                        fontSize: '1.5em'
                    },
                    filter: {
                        property: 'percentage',
                        operator: '>',
                        value: 4
                    }
                }
            }
        },
        series: []
    });

    getSiteMenuBrowserStats = Highcharts.chart('getSiteMenuBrowserStats', {
        credits: {
            enabled: false
        },
        chart: {
            height: '400px',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                colors: [],
                dataLabels: {
                    enabled: true,
                    color: '#555',
                    format: '{point.name} {point.percentage:,.1f}%',
                    distance: -60,
                    style: {
                        fontWeight: 400,
                        textOutline: 0,
                        fontSize: '1.5em'
                    },
                    filter: {
                        property: 'percentage',
                        operator: '>',
                        value: 4
                    }
                }
            }
        },
        series: []
    });

    getSiteMenuDayStats = Highcharts.chart('getSiteMenuDayStats', {
        credits: {
            enabled: false
        },
        chart: {
            height: '400px',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'column'
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
        },
        series: []
    });
    getSiteMenuMonthStats = Highcharts.chart('getSiteMenuMonthStats', {
        credits: {
            enabled: false
        },
        chart: {
            height: '400px',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'column'
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {

        },
        series: []
    });

    getSiteMenuYearStats = Highcharts.chart('getSiteMenuYearStats', {
        credits: {
            enabled: false
        },
        chart: {
            height: '400px',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                colors: [],
                dataLabels: {
                    enabled: true,
                    color: '#555',
                    format: '{point.name} {point.percentage:,.1f}%',
                    distance: -60,
                    style: {
                        fontWeight: 400,
                        textOutline: 0,
                        fontSize: '1.5em'
                    },
                    filter: {
                        property: 'percentage',
                        operator: '>',
                        value: 4
                    }
                }
            }
        },
        series: []
    });
}
function searchData() {
	var data = {};
	$ifx.promise()
	.then(function (ok, fail, data) {
		$ifx.ajax('<c:url value="/cms/info/site/stats/getSiteMenuStats.do"/>', {
			method: 'GET',
			data: data,
			success: function (res) {
                getSiteMenuStats.update({
                    series: [{
                        name: '분포도',
                        data: res.map(function(x) {
                            return {
                                name: x.titleName,
                                y: x.cnt
                            }
                        })
                    }]
                }, true, true);
				ok(res);
			}
		})
	})
	.then(function (ok, fail, data) {
		$ifx.ajax('<c:url value="/cms/info/site/stats/getSiteMenuBrowserStats.do"/>', {
			method: 'GET',
			data: data,
			success: function (res) {
                getSiteMenuBrowserStats.update({
                    series: [{
                        name: '분포도',
                        data: res.map(function(x) {
                            return {
                                name: x.titleName,
                                y: x.cnt
                            }
                        })
                    }]
                }, true, true);
				ok(res);
			}
		})
	})
	.then(function (ok, fail, data) {
		$ifx.ajax('<c:url value="/cms/info/site/stats/getSiteMenuDayStats.do"/>', {
			method: 'GET',
			data: data,
			success: function (res) {
                getSiteMenuDayStats.update({
                    xAxis: {
                        categories: res.map(function(x) {
                            return x.titleName
                        })
                    },
                    series: [{
                        name: '날짜',
                        data: res.map(function(x) {
                            return {
                                name: x.titleName,
                                y: x.cnt
                            }
                        })
                    }]
                }, true, true);
				ok(res);
			}
		})
	})
	.then(function (ok, fail, data) {
		$ifx.ajax('<c:url value="/cms/info/site/stats/getSiteMenuMonthStats.do"/>', {
			method: 'GET',
			data: data,
			success: function (res) {
                getSiteMenuMonthStats.update({
                    xAxis: {
                        categories: res.map(function(x) {
                            return x.titleName
                        })
                    },
                    series: [{
                        name: '날짜',
                        data: res.map(function(x) {
                            return {
                                name: x.titleName,
                                y: x.cnt
                            }
                        })
                    }]
                }, true, true);
				ok(res);
			}
		})
	})
	.then(function (ok, fail, data) {
		$ifx.ajax('<c:url value="/cms/info/site/stats/getSiteMenuYearStats.do"/>', {
			method: 'GET',
			data: data,
			success: function (res) {
                getSiteMenuYearStats.update({
                    series: [{
                        name: '분포도',
                        data: res.map(function(x) {
                            return {
                                name: x.titleName,
                                y: x.cnt
                            }
                        })
                    }]
                }, true, true);
				ok(res);
			}
		})
	})
}
function convertObjectToChartData(inputObjList, nameKey, valueKey) {
	var data = [];

	inputObjList.forEach(function(v, i) {
		var row = data.find(function(vo) {
			return vo[0] === v[nameKey]
		})
		if(typeof row === 'undefined') {
			row = [v[nameKey]];
			row.push(v[valueKey])
		}else{
			row.push(v[valueKey])
		}
		data.push(row);
	});
	return data;
}
</script>

<div class="sub subView">
	<nav class="navigation">
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
	</h2>

	<%--<h2 class="stitle">--%>
        <%--<input type="text" class="w100" class="main" name="searchKeyword"> ~ <input type="text" class="w100" class="main" name="searchKeyword">--%>
		<%--<button type="button" class="btn main" onclick="searchData()">조회</button>--%>
	<%--</h3>--%>

	<div>
			<div class="widget c03 h02">
				<div class="inline">
					<h3>연도별</h3>
					<div class="box">
						<div id="getSiteMenuYearStats"></div>
					</div>
				</div>
			</div>
			<div class="widget c03 h02">
				<div class="inline">
					<h3>메뉴별</h3>
					<div class="box">
						<div id="getSiteMenuStats"></div>
					</div>
				</div>
			</div>
			<div class="widget c03 h02">
				<div class="inline">
					<h3>브라우저별</h3>
					<div class="box">
						<div id="getSiteMenuBrowserStats"></div>
					</div>
				</div>
			</div>
		</div>
		<div>
			<div class="widget c05 h02">
				<div class="inline">
					<h3>일별</h3>
					<div class="box">
						<div id="getSiteMenuDayStats"></div>
					</div>
				</div>
			</div>
			<div class="widget c05 h02">
				<div class="inline">
					<h3>월별</h3>
					<div class="box">
						<div id="getSiteMenuMonthStats"></div>
					</div>
				</div>
			</div>
		</div>
</div>
