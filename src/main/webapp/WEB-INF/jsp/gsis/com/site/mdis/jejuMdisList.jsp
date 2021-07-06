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



        $('#btn_pop_open').click(function () {


            <c:if test="${loginVO!= null}">
            var mdisSno = $(this).attr('data-mdis');

                $('#jewMdisSno').val(mdisSno);
                $(".download-pop").addClass("on");
            </c:if>
            <c:if test="${loginVO== null}">
                alert("로그인 하셔야 합니다.")
                $('#jewMdisSno').val("");
            </c:if>
        });


        $('#btn_pop_close').click(function () {

            $(".download-pop").removeClass("on");
            $('#jewMdisSno').val("");
        });



        $('#btn_file_download').click(function () {

            var radioVal = $('input[name="use-check"]:checked').val();
            var jewMdisSno =$('#jewMdisSno').val();

            <c:if test="${loginVO!= null}">
                location.href="/mdis/fileDown.do?jewMdisSno="+jewMdisSno+"&downloadType="+radioVal;
            </c:if>
            <c:if test="${loginVO== null}">

            alert("로그인 하셔야 합니다.")
            $('#jewMdisSno').val("");
            </c:if>





        });


	});



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

        </div>
    </section>


    <form name="searchVO" action="<info:url value="/mdis/list.do"/>" method="post" onSubmit="fn_search_page(); return false;">

        <section class="sub-content">
            <div class="container">

                <h4 class="stitle">마이크로데이터</h4>



                <article class="board-tools">
                    <div class="container">

                        <fieldset class="search">
                            <legend class="sr-only">게시판 검색</legend>

                            <input type="text" id="mdisKorNm" name="mdisKorNm">
                            <button type="submit">검색</button>
                        </fieldset>

                    </div>
                </article>

                <article class="micro-list">

                    <ul class="list">

                        <c:forEach items="${list}" var="item" varStatus="status">

                            <li>
                                <h5>
                                    <a href="<info:url value="/mdis/view.do?jewMdisSno=${item.jewMdisSno}"/>">
                                            ${item.mdisKorNm}
                                    </a>
                                </h5>

                                <ul>
                                    <li class="name">
                                        <span><i class='bx bxs-user' ></i>연구책임자</span> <strong>${item.pi}</strong>
                                    </li>
                                    <li class="agency">
                                        <span><i class='bx bx-buildings' ></i>연구수행기관</span> <strong>${item.organization}</strong>
                                    </li>
                                </ul>
                                <button type="button" id="btn_pop_open" data-mdis ="${item.jewMdisSno}" name="btn_pop_open"><i class='bx bx-download'></i>데이터 다운로드</button>
                            </li>

                        </c:forEach>


                    </ul>


                </article>

                <article class="pagenation">
                    <ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_board_select_linkPage"/>
                </article>
                <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
                <input name="siteMemuId" type="hidden" value="<c:out value='${menuInfo.siteMemuId}'/>">

            </div>
        </section>


    </form>


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

            <input type="hidden" id="jewMdisSno" name="jewMdisSno" >

        </div>


        <button type="button" class="download" id="btn_file_download" name="btn_file_download" ><i class='bx bx-download'></i>데이터 다운로드</button>
        <button type="button" class="close"  id="btn_pop_close" name="btn_pop_close">취소하기</button>

    </div>
</div>
<!-- E:popup -->