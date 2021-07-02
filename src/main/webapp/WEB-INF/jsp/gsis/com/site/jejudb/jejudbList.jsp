<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<script src="<c:url value='/js/infomind/com/moment.js'/>"></script>
<script src="<c:url value='/js/infomind/com/incms.polyfill.js'/>"></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function () {

        Search("ROOT", 0);

    });


    // 조회
    function Search(parentId, idx) {
        var parentId = parentId;

        var p = {//root
            parentId: parentId
        };

        $ifx.ajax('<c:url value='/statsdb/infoList.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {

                var $allstep = $('.db-steps .step');

                for(var i = idx; i < 3; i++) {
                    $allstep.eq(i).find('.list li').remove();
                }

                var $step = $allstep.eq(idx);
                var $stepList = $step.find('.list');
                $stepList.empty();

                $step.addClass('on');

                res.list.forEach(function (v, i) {
                    //console.log(v, i)
                    var $li = $('<li />');
                    if(idx < 2) {
                        var $a = $('<a />', {'href': 'javascript:;', 'text': v.categoryIdNm});
                        $a.on('click', function () {
                            $a.closest('ul').find('li a').removeClass('on')
                            $a.addClass('on')
                            Search(v.categoryId, idx + 1);
                        })
                        $li.append($a)
                    }else{
                        var $a = $('<a />', {'href': 'javascript:;'});
                        $a.append("<i class='bx bx-link-external'></i>");
                        $a.append(v.titleNm)
                        $a.append('<span>' + v.subTitleNm + '</span>')
                        $a.on('click', function () {
                            var $layer = $('div.layer-pop');
                            $layer.find('iframe').attr('src', '');
                            $layer.find('iframe').attr('src', v.statsUrl);
                            $layer.show();
                            $layer.find('button.close').off().on('click', function(){
                                $layer.hide();
                            })
                        });
                        $li.append($a);
                    }
                    $stepList.append($li);
                })

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


    <section class="sub-content">
        <div class="container">

            <h4 class="stitle">성인지통계DB</h4>

            <article class="db-steps">


                <fieldset class="search">
                    <legend class="sr-only">검색</legend>

                    <strong>주제별 통계</strong>


                    <input type="text" placeholder="통계표 목록 검색">
                    <button><i class='bx bx-search'></i>검색</button>
                </fieldset>


                <div class="step step01 on">
                    <h5>분류 1<i class='bx bxs-right-arrow'></i></h5>
                    <ul class="list">
                    </ul>
                </div>


                <div class="step step02">
                    <h5>분류 2<i class='bx bxs-right-arrow'></i></h5>
                    <ul class="list">
                    </ul>
                </div>


                <div class="step step03">
                    <h5>분류 3<i class='bx bxs-right-arrow'></i></h5>
                    <ul class="list">
                    </ul>
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
        <iframe style="width:100%; height:100%;"
                src="https://gsis.kwdi.re.kr/statHtml/statHtml.do?mode=tab&orgId=338&tblId=DT_1LFB011&vw_cd=MT_ATITLE&list_id=DT_1LFB011&scrId=&seqNo=&language=ko&obj_var_id=131013447B&itm_id=131013447B.20017&conn_path=MT_ATITLE&path=">
        </iframe>


        <button class="close">닫기 <i class='bx bx-x'></i></button>

    </div>
</div>
<!-- E:popup -->