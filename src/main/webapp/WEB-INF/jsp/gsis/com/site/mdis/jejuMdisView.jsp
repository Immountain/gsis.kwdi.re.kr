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
<script type="text/javascript">
	$(document).ready(function(){



	});



	function  btn_pop_open() {


        <c:if test="${loginVO!= null}">

             $(".download-pop").addClass("on");
        </c:if>
        <c:if test="${loginVO== null}">
                alert("로그인 하셔야 합니다.")
                return;
        </c:if>




    }

    // 조회
    function Search(parentId) {
        var parentId = parentId;

        var p = {
            parentId: parentId
        };

        $ifx.ajax('<c:url value='/statsdb/infoList.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
               console.log(res)
            }
        })
    }


</script>
<div id="content" class="sub">


    <section class="sub-navigation">
        <div class="container">

            <a class="home" href="/">홈으로 <i class='bx bxs-home'></i></a>

            <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>
            <%--<i class='bx bx-chevron-right' ></i>--%>
            <%--<strong>${view.mdisKorNm}</strong>--%>
        </div>
    </section>


    <section class="sub-content">
        <div class="container">

            <h4 class="stitle">${view.mdisKorNm}</h4>

            <article class="micro-view">


                <div class="btn-set">
                    <a class="download" href="javascript:btn_pop_open();"><i class='bx bx-download'></i>데이터 다운로드</a>
                    <a class="list" href="javascript:history.back();"><i class='bx bx-list-ul'></i>목록으로</a>

                </div>
                <div class="tabs">
                    <button class="on" data="data" type="button">메타데이터</button>
                    <button data="files" type="button">관련파일</button>


                </div>



                <div class="tab-contents files">

                    <ul class="list">
                        <li>
                            <a href=""><i class='bx bx-paperclip' ></i> filelist_report.pdf</a>

                            <span>파일유형 : 관련파일 - 기타</span>
                            <span>파일형식 : PDF</span>
                            <span>파일크기 : 1.34MB</span>
                        </li>
                        <li>
                            <a href=""><i class='bx bx-paperclip' ></i> filelist_report.pdf</a>

                            <span>파일유형 : 관련파일 - 기타</span>
                            <span>파일형식 : PDF</span>
                            <span>파일크기 : 1.34MB</span>
                        </li>
                        <li>
                            <a href=""><i class='bx bx-paperclip' ></i> filelist_report.pdf</a>

                            <span>파일유형 : 관련파일 - 기타</span>
                            <span>파일형식 : PDF</span>
                            <span>파일크기 : 1.34MB</span>
                        </li>
                    </ul>
                </div>

                <dl class="tab-contents data on">
                     <dt>
                        자료유형
                    </dt>
                    <dd>
                        ${view.mdisType}

                    </dd>
                    <dt>
                        자료번호
                    </dt>
                    <dd>
                        ${view.mdisNum}

                    </dd>
                    <dt>
                        자료명(국문)
                    </dt>
                    <dd>
                        ${view.mdisKorNm}

                    </dd>
                    <dt>
                        자료명(영문)
                    </dt>
                    <dd>
                        ${view.mdisEnNm}
                    </dd>

                    <dt>
                        연구책임자
                    </dt>
                    <dd>
                        ${view.pi}
                    </dd>

                    <dt>
                        공동연구자
                    </dt>
                    <dd>
                        ${view.coPi}
                    </dd>
                    <dt>
                        연구수행기관
                    </dt>
                    <dd>
                        ${view.organization}
                    </dd>
                    <dt>
                        연구비지원기관
                    </dt>
                    <dd>
                        ${view.supportingOrganization}
                    </dd>
                    <dt>
                        저작권자
                    </dt>
                    <dd>
                        ${view.copyrightHolder}
                    </dd>
                    <dt>
                        조사목적
                    </dt>
                    <dd>
                        ${view.investigatePurpose}
                    </dd>

                    <dt>
                        조사내용
                    </dt>
                    <dd>
                        ${view.investigateContent}
                    </dd>

                    <dt>
                        키워드
                    </dt>
                    <dd>
                        ${view.keyword}
                    </dd>

                    <dt>
                        자료수집기간
                    </dt>
                    <dd>
                        ${view.collectionStrDay}~${view.collectionEndDay}
                    </dd>

                    <dt>
                        조사지역
                    </dt>
                    <dd>
                        ${view.investigateArea}
                    </dd>

                    <dt>
                        분석단위
                    </dt>
                    <dd>
                        ${view.analysisUnit}
                    </dd>

                    <dt>
                        조사대상
                    </dt>
                    <dd>
                        ${view.subject}
                    </dd>
                    <dt>
                        시간적차원
                    </dt>
                    <dd>
                        ${view.mdisTime}
                    </dd>
                    <dt>
                        조사수행기관
                    </dt>
                    <dd>
                        ${view.researchOrganization}
                    </dd>

                    <dt>
                        조사방법
                    </dt>
                    <dd>
                        <c:if test="${view.interviewSurvey =='Y'}">
                            <p>면대면</p>
                        </c:if>
                        <c:if test="${view.selfAdministeredSurvey =='Y'}">
                            <p>자기기입식</p>
                        </c:if>
                        <c:if test="${view.mailSurvey =='Y'}">
                            <p>우편조사</p>
                        </c:if>
                        <c:if test="${view.phoneSurvey =='Y'}">
                            <p>전화조사</p>
                        </c:if>
                        <c:if test="${view.onlineSurvey =='Y'}">
                            <p>온라인조사</p>
                        </c:if>
                    </dd>




                    <dt>
                        표본추출방법
                    </dt>
                    <dd>
                        ${view.extraction}
                    </dd>


                    <dt>
                        사례수
                    </dt>
                    <dd>
                        ${view.caseNumber}
                    </dd>




                    <dt>
                        가중치
                    </dt>
                    <dd>
                        ${view.weight}
                    </dd>



                    <dt>
                        자료형식
                    </dt>
                    <dd>
                        ${view.dataForm}
                    </dd>

                    <dt>
                        자료구성
                    </dt>
                    <dd>
                        ${view.dataStructure}
                    </dd>

                    <dt>
                        사용언어
                    </dt>
                    <dd>
                        ${view.mdisLanguage}
                    </dd>


                    <dt>
                        비고
                    </dt>
                    <dd>
                        ${view.remark}
                    </dd>


                    <%--<dt>--%>
                        <%--자료번호--%>
                    <%--</dt>--%>
                    <%--<dd>--%>
                        <%--<p>a1-2012-0152</p>--%>
                        <%--<p>a1-2012-0152</p>--%>
                        <%--<p>a1-2012-0152</p>--%>
                        <%--<p>a1-2012-0152</p>--%>
                        <%--<p>a1-2012-0152</p>--%>
                    <%--</dd>--%>
                    <%--<dt>--%>
                        <%--자료번호--%>
                    <%--</dt>--%>
                    <%--<dd>--%>
                        <%--<a href=""><i class='bx bx-link-alt' ></i> 링크 링크 링크</a>--%>
                    <%--</dd>--%>


                </dl>

            </article>


        </div>
    </section>
</div>
<!-- E:mainContent -->


<!-- S:popup 팝업창 입니다.-->
<div class="download-pop">
    <div class="outline">
        <h3>
            마이크로데이터 다운로드
        </h3>

        <h4>
            마이크로데이터를 다운로드 하시겠습니까?

            <small>
                데이터의 이용 목적을 알려주시기 바랍니다.
            </small>
        </h4>

        <div class="check-outline">
            <input type="radio" name="use-check" id="use01"  value="A" checked >
            <label for="use01">연구용</label>
            <input type="radio" name="use-check" id="use02" value="B">
            <label for="use02">교육용</label>
            <input type="radio" name="use-check" id="use03" value="C">
            <label for="use03">자료학습탐색용</label>

            <input type="hidden" id="jewMdisSno" name="jewMdisSno" value="${view.jewMdisSno}" >

        </div>


        <button type="button" class="download" id="btn_file_download" name="btn_file_download" ><i class='bx bx-download'></i>데이터 다운로드</button>
        <button type="button" class="close"  id="btn_pop_close" name="btn_pop_close">취소하기</button>

    </div>
</div>
<!-- E:popup -->
