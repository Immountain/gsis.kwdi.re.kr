<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="pageTitle">사이트 메뉴</c:set>

<script src="<c:url value="/assets/ax5/ax5ui-binder/ax5binder.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-layout/ax5layout.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-layout/ax5layout.css"/>">

<script type="text/javascript">
$(document).ready(function() {
	$('[data-ax5layout]').ax5layout();
	genTreeMenu();
	genForm();

	$ifx.uploader('#fileuploader', '#siteMemuImage', {});

	$('#s_siteId').on('change', function(e) {
		getSiteMenuGroup(this.value);
	})
	// searchTreeMenu();
});
var defaultMenuModel = {siteMemuId: '', siteMemuNm: '', siteMemuTile: '', slug: '', url: '', depth: '', ord: '', menuGroupId: '', menuType: '', menuPage: '', siteMemuImage: '', attr: '', target: '', class1: '', class2: '', class3: '', Layout: '', useGrade: '', temp01: '', temp02: '', temp03: '', temp04: '', temp05: '', loginYn: '', viewYn: '', useYn: ''}
var treeMenu, formBinder, tempBinderData;

function genForm() {
	var $formBinderEl = $(document["formBinder"]);
	formBinder = new ax5.ui.binder();
	formBinder.onChange("*", function (n) {
		formBinder.set("__modified__", true);
		formTypeRender();
		treeMenu.zTree.refresh()
	});
	formBinder.setModel(defaultMenuModel, $formBinderEl);
}
function formTypeRender() {
	var $formBinderEl = $(document["formBinder"]);
	var n = formBinder.get();
	if(n.__created__ === true)
		$formBinderEl.find('[data-ax-path=siteMemuId]').removeAttr('readonly')
	else
		$formBinderEl.find('[data-ax-path=siteMemuId]').attr('readonly', 'readonly')

	// 메뉴타입
	$('button.menuPageFind').hide();
	if(n.menuType === 'board' || n.menuType === 'page') {
		$('button.menuPageFind').show();
		$formBinderEl.find('[data-ax-path=menuPage]').removeAttr('readonly')
		$formBinderEl.find('[data-ax-path=slug]').removeAttr('readonly')
	}else if(n.menuType === 'outer') {
		$formBinderEl.find('[data-ax-path=menuPage]').attr('readonly', 'readonly')
		$formBinderEl.find('[data-ax-path=slug]').attr('readonly', 'readonly')
	} else {
		$formBinderEl.find('[data-ax-path=menuPage]').attr('readonly', 'readonly')
		$formBinderEl.find('[data-ax-path=slug]').removeAttr('readonly')
	}
}
function formBinderSetModel(data) {
	formBinder.setModel(data)
	formTypeRender();
}
function getMenuPage() {
	var n = formBinder.get();
	var url = '', title = '';
	if(n.menuType === 'board') {
		title = '게시판 선택'
		url = '/cms/info/board/popBoardList.do'
	}else if(n.menuType === 'page') {
		title = '페이지 선택'
		url = '/cms/info/page/popInfoPageInfoList.do'
	}else{
		alert('메뉴페이지를 입력하지 않는 타입입니다.');
		return false;
	}
	ax5modal.open({
		theme: "primary",
		height: 747,
		width: 1202,
		header: {
			title: title,
			btns: {
				close: {
					label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
						ax5modal.close();
					}
				}
			}
		},
		iframe: {
			method: "get",
			url: url,
			param: {}
		},
		onStateChanged: function(){
			if(this.state === 'load') {
				var $frame = $(this.$.iframe).contents();
				$frame.on('click', '[ax-event]', function(e) {
					formBinder.set('menuPage', $(e.target).attr('ax-event'))
					ax5modal.close();
				})
			}
		}
	}, function (d) {

	});
}
function getMenuAuth() {
	var formData = formBinder.get();
	if(formData['__created__'] === true) {
		alert('신규등록메뉴는 메뉴 등록 처리 후 설정이 가능합니다.');
		return false;
	}
	if(formData['authYn'] === 'N') {
		alert('권한 사용시에만 설정이 가능합니다.');
		return false;
	}
	var p = {
		siteMemuId: formData['siteMemuId']
	};
	ax5modal.open({
		theme: "primary",
		height: 500,
		width: 800,
		header: {
			title: '사이트 메뉴 권한 관리',
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
			url: '<c:url value='/cms/info/site/menu/auth.do' />',
			param: p
		},
	}, function (d) {

	});
}
function genTreeMenu() {
	treeMenu = $ifx.treeBuilder($('[data-z-tree="tree-view-01"]'), {
		data: {
			keep: {
				leaf: false,
				parent: false
			},
			key: {
				name: 'siteMemuNm',
				title: 'siteMemuTile',
				children: 'childMenu',
				url: ''
			},
			simpleData: {
				enable: true,
				idKey: 'siteMemuId',
				pidKey: 'parentId',
				rootPId: 'root'
			}
		},
		view: {
			dblClickExpand: false,
			addHoverDom: function (treeId, treeNode) {
				var sObj = $("#" + treeNode.tId + "_span");
				if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
				if(treeNode.depth < 3) {
					var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
					sObj.after(addStr);
					var btn = $("#addBtn_" + treeNode.tId);
					if (btn) {
						btn.bind("click", function () {
							var newNode = {
								menuGroupId: $('#s_menuGroupId').val(),
								depth: treeNode.depth + 1,
								siteMemuId: "",
								parentId: treeNode.siteMemuId,
								siteMemuNm: "신규 메뉴",
								__created__: true,
								__modified__: true
							};
							treeMenu.zTree.addNodes(
									treeNode,
									newNode
							);
							treeMenu.zTree.selectNode(treeNode.childMenu[treeNode.childMenu.length - 1]);
							//treeMenu.editName();
							//treeMenu.deselectNode();
							formBinderSetModel(newNode)
							return false;
						});
					}
				}
			},
			removeHoverDom: function (treeId, treeNode) {
				$("#addBtn_" + treeNode.tId).unbind().remove();
			}
		},
		edit: {
			enable: false,
			showRenameBtn: false,
			showRemoveBtn: false
		},
		callback: {
			beforeDrop: function(treeId, treeNodes, targetNode, moveType) {
				console.log(treeId, treeNodes, targetNode, moveType)
				if(moveType === 'inner' && targetNode.depth >= 3) {
					alert('3차 메뉴 이상으로 만들 수 없습니다.');
					return false;
				}
			},
			onDrop: function(event, treeId, treeNodes, targetNode, moveType) {
				initNodeValue(treeMenu.getData(), 1)
			},
			beforeDrag: function () {
			},
			beforeClick: function(treeId, treeNode, clickFlag) {
				if(formBinder.get('__modified__') === true) {
					if(!confirm('변경사항이 저장되지 않습니다.\n그대로 진행하시겠습니까?')) {
						return false;
					} else {
						formBinderSetModel(tempBinderData);
					}
				}
			},
			onClick: function (e, treeId, treeNode, isCancel) {
				tempBinderData = $.extend(true, {}, treeNode);
				formBinderSetModel(treeNode);
				return false;
			},
			onRename: function (e, treeId, treeNode, isCancel) {
			},
			onRemove: function (e, treeId, treeNode, isCancel) {
			}
		}
	}, []);
}
function initNodeValue(list, depth) {
	list.forEach(function(v, i) {
		v['siteMemuNm'] = v['siteMemuNm'] + (v['useYn'] === 'Y' ? '' : '(미사용)')
		// 리스트전송인경우만사용 v['ord'] = i;
		v['depth'] = depth
		v['childMenu'] = v['childMenu'] || []
		if(v['childMenu'].length > 0) {
			initNodeValue(v['childMenu'], depth + 1)
		} else {
			delete v['childMenu']
		}
	})
}
function getSiteMenuGroup(_siteId) {
	var $selectMenuGroup = $('#s_menuGroupId');
	$selectMenuGroup.empty();
	$selectMenuGroup.append($('<option />', {'text': '메뉴그룹 선택', 'value': ''}));

	$ifx.ajax('<c:url value='/cms/info/site/InfoSiteMenuGroupListObject.do' />', {
		method: "GET",
		data: {siteId: _siteId},
		success: function (res) {
			res.list.forEach(function(e, i) {
				$selectMenuGroup.append($('<option />', {
					'value': e.menuGroupId,
					'text': e.menuGroupNm
				}));
			});
		}
	});
}
function searchTreeMenu() {
	if($ifx.isEmpty($('#s_siteId').val())) {
		alert('사이트를 선택하여 주시기 바랍니다.');
		return false;
	}
	if($ifx.isEmpty($('#s_menuGroupId').val())) {
		alert('메뉴 그룹을 선택하여 주시기 바랍니다.');
		return false;
	}

	var data = {
		menuGroupId: $('#s_menuGroupId').val()
	};
	if($('#s_useYn').is(':checked')) {
		data['useYn'] = '';
	}else{
		data['useYn'] = 'Y'
	}

	$ifx.ajax('<c:url value='/cms/info/site/menu/menuListObject.do' />', {
		method: "GET",
		data: data,
		success: function (res) {
			initNodeValue(res.list, 1)
			treeMenu.setData(res.list)
			treeMenu.zTree.expandAll(true)
			formBinderSetModel(defaultMenuModel);
		}
	});
}
function cacheMenuReload() {
	$ifx.promise()
			.then(function (ok, fail, data) {
				$ifx.ajax('<c:url value='/cms/info/site/menu/cacheMenuReload.do' />', {
					method: "GET",
					data: {},
					success: function (res) {
						ok(res)
					}
				});
			})
			.then(function (ok, fail, data) {
				searchTreeMenu()
				alert(data.message)
			});
}
function addRootNode() {
	var nodes = treeMenu.zTree.getSelectedNodes();

	if($ifx.isEmpty($('#s_menuGroupId').val())) {
		alert('메뉴 그룹을 선택하여 주시기 바랍니다.');
		return false;
	}

	// root
	var treeNode = treeMenu.zTree.addNodes(null, {
		menuGroupId: $('#s_menuGroupId').val(),
		depth: 1,
		siteMemuId: "",
		parentId: 'root',
		siteMemuNm: "신규 메뉴",
		__created__: true,
		__modified__: true
	});
	console.log(treeNode)
	if (treeNode) {
		treeMenu.zTree.selectNode(treeNode);
	}
}
function genUrl() {
	var model = formBinder.get();
	var url = '/';
	switch(model['menuType']) {
		case 'common':
			alert('해당타입은 수동입력만 가능합니다.');
			return false;
		case 'board':
			url = String.format('/{0}/{1}/{2}', 'board', model['menuPage'], 'list.do');
			break;
		case 'page':
			url = String.format('/{0}/{1}', 'page', model['slug']);
			break;
		case 'outer':
			alert('해당타입은 수동입력만 가능합니다.');
			return false;
	}

	formBinder.set('url', url);
}
function saveOne() {
	var p = formBinder.get();
	// formValid
	var rs = formBinder.validate(), _s;
	if(rs.error) {
		_s = rs.error[0].jquery.attr("title");
		alert("" + _s + "(은)는 필수 입력사항입니다." + _s + "(을)를 입력하세요");
		rs.error[0].el.focus();
		return false;
	}
	if((p['menuType'] == 'board' || p['menuType'] == 'page') && $ifx.isEmpty(p['slug'])) {
		alert('슬러그를 입력하여 주시기 바랍니다.')
		return false;
	}
	if( (p['menuType'] === 'board' || p['menuType'] === 'page') && $ifx.isEmpty(p['menuPage'])) {
		alert('메뉴페이지를 선택하여주시기 바랍니다.')
		return false;
	}
	if($ifx.isEmpty(p['url'])) {
		alert('URL을 입력하여주시기 바랍니다.')
		return false;
	}

	if(!confirm('해당 메뉴를 저장하시겠습니까?')) return false;
	$ifx.ajax('<c:url value='/cms/info/site/menu/menuOneUpdateObject.do' />', {
		method: "POST",
		data: JSON.stringify(p),
		success: function (res) {
			alert(res.message)

			if(res.status === 'success') {
				formBinderSetModel(defaultMenuModel)
				searchTreeMenu()
			}
		}
	})
}
function save() {
	var p = treeMenu.getData();
	initNodeValue(p, 1)

	if(!confirm('정말로 저장하시겠습니까?')) return false;
	$ifx.ajax('<c:url value='/cms/info/site/menu/menuUpdateObject.do' />', {
		method: "POST",
		data: JSON.stringify(p),
		success: function (res) {
			alert(res.message)
			searchTreeMenu()
		}
	})
}
</script>

<div class="sub subView">
	<nav class="navigation">
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}</a>
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
	</h2>

	<h3 class="btitle">
		메뉴 목록
	</h3>
	<div class="row">
		<div class="col-md-12">
			<div class="white-box">
				<div class="rows">


					<span class="select-outline">

						<select id="s_siteId" >
							<option value="">사이트 선택</option>
							<c:forEach var="item" items="${site}" varStatus="vs">
								<option value="${item.siteId}"><c:out value="${item.host}"/><c:if test="${!empty item.subPath}">/</c:if><c:out value="${item.subPath}"/></option>
							</c:forEach>
						</select>

						<select id="s_menuGroupId" >
							<option value="">메뉴그룹 선택</option>
							<%--<c:forEach var="item" items="${group}" varStatus="vs">
								<option value="${item.menuGroupId}">${item.menuGroupNm}</option>
							</c:forEach>--%>
						</select>
                    </span>
					<input type="checkbox" id="s_useYn" value="">
					<label for="s_useYn"><i class="bx bxs-checkbox-checked"></i>미사용 메뉴보기</label>

					<button type="button" class="btn main" onclick="searchTreeMenu()">조회</button>
					<button type="button" class="btn sub" onclick="cacheMenuReload()">메뉴캐시초기화</button>
					<button type="button" class="btn main" onclick="addRootNode()">1차 메뉴 추가</button>
					<button type="button" class="btn sub" onclick="saveOne()">변경 내용 저장</button>
				</div><!-- END: div.rows -->

				<div class="rows">
					<div data-ax5layout="ax1" data-config='{layout:"split-panel", orientation: "vertical"}' style="height: 80vh;border:1px solid #ccc;">
						<div data-split-panel='{width: "25%"}'>
							<div class="white-box">
								<div data-z-tree="tree-view-01" style="height: 80vh;" class="ztree"></div>
							</div>
						</div>
						<div data-splitter="{}"></div>
						<div data-split-panel='{width: "*"}' style="overflow-y: scroll;">
							<form name="formBinder">
								<div class="rows white-box">
									<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
										<colgroup>
											<col width="10%"/>
											<col width="20%"/>
											<col width="10%"/>
											<col width="20%"/>
											<col width="10%"/>
											<col width="20%"/>
										</colgroup>
										<tbody>
										<tr>
											<%--<th>
                                                <label for="menuGroupId">메뉴 그룹 아이디<span class="pilsu">*</span></label>
                                            </th>
                                            <td class="left">
                                                <select name="menuGroupId" id="menuGroupId" data-ax-path="menuGroupId">
                                                    <option>그룹 선택</option>
                                                    <c:forEach var="item" items="${group}" varStatus="vs">
                                                        <option value="${item.menuGroupId}">${item.menuGroupNm}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>--%>
											<th>
												<label for="siteMemuId">메뉴 아이디<span class="pilsu">*</span></label>
											</th>
											<td class="left" >
												<input type="text" name="siteMemuId" id="siteMemuId" title="메뉴아이디" data-ax-path="siteMemuId" data-ax-validate="required" />
											</td>
											<th>
												<label for="siteMemuId">정렬순서</label>
											</th>
											<td class="left" >
												<input type="text" name="ord" id="ord" title="정렬순서" data-ax-path="ord" />
											</td>
											<th>
												<label for="useYn">사용여부</label>
											</th>
											<td class="left" >
												<select name="useYn" id="useYn" title="사용여부" data-ax-path="useYn" >
													<option value="Y">사용</option>
													<option value="N">사용안함</option>
												</select>
											</td>
										</tr>
										<tr>
											<th>
												<label for="siteMemuNm">메뉴명<span class="pilsu">*</span></label>
											</th>
											<td class="left" >
												<input type="text" name="siteMemuNm" id="siteMemuNm" title="메뉴명" data-ax-path="siteMemuNm" data-ax-validate="required"/>
											</td>
											<th>
												<label for="siteMemuTile">메뉴 타이틀<span class="pilsu">*</span></label>
											</th>
											<td class="left" >
												<input type="text" name="siteMemuTile" id="siteMemuTile" title="메뉴 타이틀" data-ax-path="siteMemuTile" data-ax-validate="required"/>
											</td>
											<th>
												<label for="layout">LAYOUT</label>
											</th>
											<td class="left" >
												<select name="layout" id="layout" title="레이아웃" data-ax-path="layout" >
													<option>레이아웃 선택</option>
													<c:forEach var="item" items="${layout}" varStatus="vs">
														<option value="${item.layoutId}">${item.layoutNm}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<th>
												<label for="useYn">권한설정</label>
											</th>
											<td class="left" >
												<select name="authYn" title="권한설정" data-ax-path="authYn">
													<option value="N">사용안함</option>
													<option value="Y">사용</option>
												</select>
												<button type="button" class="btn main" onclick="getMenuAuth()">설정</button>
											</td>
											<th>
												<label for="loginYn">로그인 여부</label>
											</th>
											<td class="left">
												<select name="loginYn" id="loginYn" title="로그인여부" data-ax-path="loginYn">
													<option value="N">사용안함</option>
													<option value="Y">사용</option>
												</select>
											</td>
											<th>
												<label for="viewYn">노출 여부</label>
											</th>
											<td class="left">
												<select name="viewYn" id="viewYn" title="노출여부" data-ax-path="viewYn">
													<option value="Y">사용</option>
													<option value="N">사용안함</option>
												</select>
											</td>
										</tr>
										<tr>
											<th>
												<label for="menuType">메뉴타입</label>
											</th>
											<td class="left" >
												<select name="menuType" id="menuType" title="메뉴타입" data-ax-path="menuType">
													<option value="top">부모타입</option>
													<option value="common">일반</option>
													<option value="board">게시판</option>
													<option value="page">페이지</option>
													<option value="outer">외부</option>
												</select>
											</td>
											<th>
												<label for="slug">슬러그</label>
											</th>
											<td class="left" >
												<input type="text" name="slug" id="slug" title="슬러그" data-ax-path="slug"/>
											</td>
											<th>
												<label for="menuPage">메뉴 페이지</label>
											</th>
											<td class="left" colspan="3">
												<input type="text" name="menuPage" id="menuPage" size="10" data-ax-path="menuPage"/>
												<button type="button" class="btn main menuPageFind" onclick="getMenuPage()">선택</button>
											</td>
										</tr>
										<tr>
											<th>
												<label for="url">URL<span class="pilsu">*</span></label>
											</th>
											<td class="left" colspan="5">
												<input type="text" name="url" id="url" data-ax-path="url" size="70" maxlength="200"/>
												<select name="target" id="target" data-ax-path="target">
													<option value="_self">현재창</option>
													<option value="_blank">새창</option>
												</select>
												<button type="button" class="btn main" onclick="genUrl()">URL자동생성</button>
											</td>
										</tr>
										<tr>
											<th>
												<label for="url">CSS Class</label>
											</th>
											<td class="left" colspan="5">
												<label>Class1: <input type="text" name="class1" id="class1" data-ax-path="class1" /></label>
												<label>Class2: <input type="text" name="class2" id="class2" data-ax-path="class2" /></label>
												<label>Class3: <input type="text" name="class3" id="class3" data-ax-path="class3" /></label>
											</td>
										</tr>
										<tr>
											<th>
												<label for="url">임시필드</label>
											</th>
											<td class="left" colspan="5">
												<label>필드1: <input type="text" name="temp01" id="temp01" data-ax-path="temp01" /></label>
												<label>필드2: <input type="text" name="temp02" id="temp02" data-ax-path="temp02" /></label>
												<label>필드3: <input type="text" name="temp03" id="temp03" data-ax-path="temp03" /></label>
												<label>필드4: <input type="text" name="temp04" id="temp04" data-ax-path="temp04" /></label>
												<label>필드5: <input type="text" name="temp05" id="temp05" data-ax-path="temp05" /></label>
											</td>
										</tr>
										<tr>
											<th>
												<label for="url">평가 기능</label>
											</th>
											<td class="left">
												<select name="useGrade" id="useGrade" data-ax-path="useGrade">
													<option value="N">사용안함</option>
													<option value="Y">사용</option>
												</select>
											</td>
											<th>
												<label for="url">평가부서</label>
											</th>
											<td class="left">
												<input type="text" name="gradeDepart" id="gradeDepart" data-ax-path="gradeDepart" />
											</td>
											<th>
												<label for="url">평가담당자</label>
											</th>
											<td class="left">
												<input type="text" name="gradePerson" id="gradePerson" data-ax-path="gradePerson" />
											</td>
										</tr>
										<tr>
											<th>
												<label for="url">메뉴 이미지</label>
											</th>
											<td class="left" colspan="5">
												<input type="hidden" name="siteMemuImage" id="siteMemuImage" data-ax-path="siteMemuImage"/>
												<div id="fileuploader">Upload</div>
											</td>
										</tr>
										</tbody>
									</table>
								</div>
							</form>
						</div>
					</div>
				</div><!-- END: div.rows -->
			</div><!-- END: div.white-box -->
		</div>
	</div>

</div>
