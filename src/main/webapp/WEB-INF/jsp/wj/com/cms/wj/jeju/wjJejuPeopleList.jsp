<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<script type="text/javascript">

	var gridList = [];

	var firstGrid = new ax5.ui.grid();

	$(document).ready(function () {
		// 그리드세팅
		firstGrid.setConfig({
					target: $('[data-ax5grid="first-grid"]'),
					sortable: true,
					showRowSelector: true,
					multipleSelect: true,
					showLineNumber: false,
					lineNumberColumnWidth: 40,

					header: {
						align: "center",
						columnHeight: 40
					},
					body: {
						align: "center",
						columnHeight: 28,
						onClick : function(){
							firstGrid.select(this.dindex);
						},
						onDBLClick: function () {}

					},

					columns: [
						{
							key: "noSno", label: "상세보기", width:100 ,formatter: function () {
								// console.log(this.item);
								return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoUpdt(" + this.dindex + ");'> 상세 </button>";
							}
						},
						{key: "userId", label:"등록아이디", width:100,},
						{key: "korName", label:"성명_한글", width:100},
						{key: "enName", label:"성명_영문", width:100},
						{key: "cnName", label:"성명_한자", width:100},
						{key: "atchFileId", label:"사진첨부", width:100},
						{key: "sex", label:"성별", width:100},
						{key: "birth", label:"생년월일", width:100},
						{key: "city", label:"출신지_대", width:100},
						{key: "town", label:"출신지_중", width:100},
						{key: "village", label:"출신지_소", width:100},
						{key: "addressEtc", label:"출신지", width:100},
						{key: "country", label:"거주지_대", width:100},
						{key: "countryCity", label:"거주지_소", width:100},
						{key: "countryEtc", label:"거주지", width:100},
						{key: "phone", label:"휴대폰", width:100},
						{key: "tel1", label:"전화번호_자택", width:100},
						{key: "tel2", label:"전화번호_직장", width:100},
						{key: "emaill", label:"이메일", width:100},
						{key: "company", label:"소속기관", width:100},
						{key: "spot", label:"소속기관_직위", width:100},
						{key: "schoolE", label:"학력_초등학교", width:100},
						{key: "schoolEYear", label:"초등학교_졸업년도", width:100},
						{key: "schoolM", label:"학력_중학교", width:100},
						{key: "schoolMYear", label:"중학교_졸업년도", width:100},
						{key: "schoolH", label:"학력_고등학교", width:100},
						{key: "schoolHYear", label:"고등학교_졸업년도", width:100},
						{key: "schoolU", label:"학력_대학교", width:100},
						{key: "schoolUYear", label:"대학교_졸업년도", width:100},
						{key: "schoolG", label:"학력_대학원이상", width:100},
						{key: "schoolGYear", label:"대학원이상_졸업년도", width:100},
						{key: "edu", label:"최종학력", width:100},
						{key: "religion", label:"종교", width:100},
						{key: "religionEtc", label:"종교_기타", width:100},
						{key: "career", label:"경력사항", width:100},

					]
				}
		);


		$('#btn_search').click(function () {
			Search();
		});

		Search();
	});

	// 조회
	function Search() {
		var searchCondition = $("#searchCondition option:selected").val();
		var searchKeyword = $("#searchKeyword").val();

		var p = {
			searchCondition: searchCondition,
			searchKeyword: searchKeyword,
		};

		$ifx.ajax('<c:url value='/cms/wj/jeju/wjJejuPeopleObject.do' />', {
			method: "POST",
			data: JSON.stringify(p),
			success: function (res) {
				firstGrid.setData(res.list);
			}
		})
	}

	function gridData(row){

		gridList = row;

	}

	function btn_mail_send(){
		var data = firstGrid.getList("selected");
		if(data.length < 1) {
			alert('메일 발송은 최소 한명 이상 설정되어야합니다.');
			return false;
		}

		var API_SERVER = "<c:url value='/cms/wj/jeju/wjJejuPeopleMailSendForm.do'/>";
		ax5modal.open({
			theme: "primary",
			height: 600,
			width: 920,
			header: {
				title: '메일전송',
				btns: {
					close: {
						label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
							// modal.close();
							ax5modal.close();
						}
					}
				}
			},
			iframe: {
				method: "POST",
				url: API_SERVER,
				param: {
					list: JSON.stringify(data)
				}
			},
		}, function (d) {
			Search();
		});
	}

	function gotoUpdt(row) {

		var _userId = firstGrid.getList()[row].userId;

		var p = {
			userId: _userId,
			menuTargetNo : $('#menuTargetNo').val()
		};

		var API_SERVER = "<c:url value='/cms/wj/jeju/wjJejuPeopleDetail.do' />";
		ax5modal.open({
			theme: "primary",
			height: 825,
			width: 1067,
			header: {
				title: '상세보기',
				btns: {
					close: {
						label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
							// modal.close();
							ax5modal.close();
						}
					}
				}
			},
			iframe: {
				method: "get",
				url: API_SERVER,
				param: p
			},
		}, function (d) {
			Search();
		});
	}
	function gridExcelDownLoad() {
		firstGrid.updateColumn($.extend(true, firstGrid.config.columns[0], {hidden: true}), 0);
		firstGrid.exportExcel("wjJejuPeople.xls");
		firstGrid.updateColumn($.extend(true, firstGrid.config.columns[0], {hidden: false}), 0);
	}

</script>
<div class="sub subView">
	<nav class="navigation">
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard'></i>제주인관리 <spring:message code="title.list"/>
	</h2>
	<h3 class="btitle">
		검색
	</h3>
	<!-- 검색영역 -->
	<!-- 검색조건선택 -->
	<div class="white-box">
		<div class="rows">
         <span class="select-outline">
                <select name="searchCondition" id="searchCondition"
						title="<spring:message code="title.searchCondition" />">
                       <option selected value=''><spring:message code="input.select"/></option><!-- 선택하세요 -->
                       <option value="1">아이디</option>
					<!-- 코드ID -->
                       <option value="2">이름</option>
					<!-- 코드ID -->
               </select>
         </span>

			<input type="text" class="w100" class="main" name="searchKeyword" id="searchKeyword" size="35"
				   title="<spring:message code="title.search" /> <spring:message code="input.input" />" value=''
				   maxlength="155">
			<button type="button" class="button" name="btn_search" id="btn_search"
					value="<spring:message code="button.inquire" />"
					title="<spring:message code="title.inquire" /> <spring:message code="input.button" />"><i
					class='bx bx-slider-alt'></i><spring:message code="title.inquire"/></button>
			<button type="button" class="button main" onclick="btn_mail_send(); return false;">
				메일 발송
			</button>
		</div>
	</div>
	<h3 class="btitle">
		목록
	</h3>
	<div class="rows white-box">
		<div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 300px;"></div>
	</div>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	<button type="button" class="button main" onclick="gridExcelDownLoad()"><i class="bx bx-slider-alt"></i>엑셀자료 다운로드</button>
</div>