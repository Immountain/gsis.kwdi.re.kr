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
        treeSearch("ROOT", 0);
    });


    // 분류 조회
    function treeSearch(parentId, idx) {
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
                            treeSearch(v.categoryId, idx + 1);
                        })
                        $li.append($a)
                    }else{
                        var $a = $('<a />', {'href': 'javascript:;'});
                        $a.append("<i class='bx bx-link-external'></i>");
                        $a.append(v.titleNm)
                        $a.append('<span>' + v.subTitleNm + '</span>')
                        $a.on('click', v, viewFrameData);
                        $li.append($a);
                    }
                    $stepList.append($li);
                })

            }
        })
    }

    function keywordSearch() {
        var searchKeyword = $('#searchKeyword').val();
        searchKeyword = searchKeyword.trim();

        if($ifx.isEmpty(searchKeyword)) {
            alert('검색어를 입력하여 주시기 바랍니다.');
            return false;
        }

        var $layer = $('div.layer-pop.list');
        var $h3 = $layer.find('.result h3');
        var $list = $layer.find('.result ul');
        $list.empty();

        $ifx.ajax('<c:url value='/statsdb/infoSearchList.do' />', {
            method: "POST",
            data: JSON.stringify({
                categoryIdNm: searchKeyword
            }),
            success: function (res) {
                $h3.html('검색어 "<strong>' + searchKeyword + '</strong>"에 대한 검색결과 총 <strong>' + res.list.length + '건</strong> 입니다.');

                res.list.forEach(function (v, i) {
                    // <a href="">인구 &gt; 사망 &gt; <strong>직업별</strong> 사망(성/연령별)(1993~2017)</a>
                    var title = v.category1idNm + ' > '+v.category2idNm +" > ";
                    if(v.titleNm.indexOf(searchKeyword) > -1) {
                        var splitStr = v.titleNm.split(searchKeyword);
                        splitStr.forEach(function (v, i) {
                            title += splitStr[i]

                            if(splitStr.length > (i+1))
                                title += '<strong>' + searchKeyword + '</strong>'
                        })
                    }else{
                        title += v.titleNm;
                    }

                    title += v.subTitleNm

                    var $li = $('<li />', {});
                    var $a = $('<a />', {html: title});
                    $a.on('click', v, viewFrameData);
                    $li.append($a);
                    $list.append($li);
                });

                $layer.find('button.close').off().on('click', function(){
                    $layer.hide();
                })
                $layer.show();
                console.log($layer)
            }
        });
    }

    // Iframe Data
    function viewFrameData(e) {
        var $layer = $('div.layer-pop.view');
        var $frameArea = $layer.find('#frameArea');
        $frameArea.empty();
        $frameArea.height($layer.height());
        $frameArea.append($('<iframe />', {
            'style': 'border: none; width: 100%; height: 100%;',
            'src': e.data.statsUrl
        }))

        $layer.show();
        $layer.find('button.close').off().on('click', function(){
            $layer.hide();
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
                    <input type="text" id="searchKeyword" onkeypress="if(event.keyCode==13) keywordSearch();" placeholder="통계표 목록 검색">
                    <button type="button" onclick="keywordSearch();"><i class='bx bx-search'></i>검색</button>
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
<div class="layer-pop list">
    <div class="outline">
        <div class="result">
            <h3>검색어 "<strong>직업별</strong>"에 대한 검색결과 총 <strong>3건</strong> 입니다.</h3>
            <ul>
            </ul>
        </div>
        <button class="close">닫기 <i class='bx bx-x'></i></button>
    </div>
</div>
<div class="layer-pop view">
    <div class="outline">
        <div id="frameArea">
            <iframe
                style="width:100%; height:100%;"
                src="https://gsis.kwdi.re.kr/statHtml/statHtml.do?mode=tab&orgId=338&tblId=DT_1LFB011&vw_cd=MT_ATITLE&list_id=DT_1LFB011&scrId=&seqNo=&language=ko&obj_var_id=131013447B&itm_id=131013447B.20017&conn_path=MT_ATITLE&path=">
            </iframe>
        </div>
        <button class="close">닫기 <i class='bx bx-x'></i></button>
    </div>
</div>
<!-- E:popup -->