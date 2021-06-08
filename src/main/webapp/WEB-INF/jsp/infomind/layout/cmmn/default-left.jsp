<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script>

    function debounce(func) {
        var timer;
        return function (event) {
            if (timer) clearTimeout(timer);
            timer = setTimeout(func, 100, event);
        };
    }

    window.addEventListener("resize", debounce(function (e) {
        $('.frame-child iframe').height($(window).height() - $('#header').height() - $('.tab-navigation').height());
    }));

    $(document).ready(function () {

        $('.frame-child iframe').height($(window).height() - $('#header').height() - $('.tab-navigation').height());

        //요놈 땜에.....내 앞길을 막고 있음....
        if ($('.tab-navigation div.tabs').length > 0) {
            $('.tab-navigation div.tabs').slick({
                infinite: false,
                slidesToShow: 8,
                slidesToScroll: 1,
                variableWidth: true,
                nextArrow: $('.tab-navigation .right'),
                prevArrow: $('.tab-navigation .left')
            });
        }

        var $frameTap = $('#frame-tap');
        var $frameArea = $('#frame-area');
        var $mainMenu = $('#main-menu');

        $mainMenu.find('a').each(function (i, v) {
            var $v = $(v);
            $v.attr('frame-url', $v.attr('href'));
            $v.attr('href', '#');
        });

        var menuClick = function (_frameId) {
            $frameArea.find('div[frame-id]').hide();
            $frameTap.find('div[frame-id]').removeClass('on');

            $frameArea.find('div[frame-id=' + _frameId + ']').show();
            $frameTap.find('div[frame-id=' + _frameId + ']').addClass('on');

            if ($frameTap.find('div[frame-id=' + _frameId + ']').length) {
                return true;
            }
            return false;
        };
        $('#frame-tap').on('click', 'div.item', function (e) {
            var $target = $(e.target).closest('div');
            var frameId = $target.attr('frame-id');

            menuClick(frameId);
        });
        $('#frame-tap').on('click', 'div.item button.close', function (e) {
            e.stopPropagation();
            var currectFrameId = $(e.target).closest('button').attr('frame-id');
            $('#frame-tap div[frame-id=' + currectFrameId + ']').remove();
            $('#frame-area div[frame-id=' + currectFrameId + ']').remove();

            menuClick($('#frame-tap div[frame-id]:last').attr('frame-id'));
        });

        $mainMenu.on('click', 'a[frame-url]', function (e) {
            var $target = $(e.target);
            var frameUrl = $target.attr('frame-url');
            var frameId = $target.attr('frame-id');
            var boardId = $target.attr('board-id');

            var frameLocation = getLocation(frameUrl);
            var url = frameLocation.pathname + '?menuTargetNo=' + frameId + (boardId ? '&boardId=' + boardId : '');


            //   frameUrl =frameUrl+"?menuNo="+frameId;
            if (typeof frameUrl === 'undefined' || frameUrl == '#') {
                return false;
            }
            if (menuClick(frameId)) {
                $frameArea.find('div[frame-id=' + frameId + '] iframe').attr('src', url)
                return false;
            }

            var $frameTapMenu = $('<div />', {'class': 'item on', 'frame-id': frameId});
            $frameTapMenu.append($('<a />', {'href': 'javascript:;', 'html': $target.text()}))
            //.on('click', function(){
            //    menuClick(frameId);
            //}));
            $frameTapMenu.append($('<button />', {'type': 'button', 'class': 'close', 'frame-id': frameId})
                //.on('click',function(b){
                //    var currectFrameId = $(b.target).closest('button').attr('frame-id');
                //    $('#frame-tap div[frame-id='+currectFrameId+']').remove();
                //    $('#frame-area div[frame-id='+currectFrameId+']').remove();
                //})
                .append($('<i />', {'class': 'bx bx-x'})));

            var $frameDiv = $('<div />', {'class': 'frame-child', 'frame-id': frameId});

            var height = $(window).height() - $('#header').height() - $('.tab-navigation').height();

            var $frame = $('<iframe />', {'src': url, 'width': '100%', height: height});
            //var $frame = $('<iframe />', {'src': url});
            //   var $frame = $('<iframe />', {'src': url, 'onload': 'resizeIframe(this)'});

            $frameDiv.append($frame);
            $frameTap.slick('slickAdd', $frameTapMenu);
            $frameArea.append($frameDiv);

            menuClick(frameId);
        });
    });

    function getLocation(href) {

        if (href.substr(0, 1) == '/') {
            href = href.substr(1);
        }else {
            href = '/' + href;
        }

        href = '<c:url value="/"/>' + href;

        var l = document.createElement("a");
        l.href = href;
        return l;
    }

    function makeUrl(url, param) {
        Object.keys(param).forEach(function (key, index) {
            url = url + (index === 0 ? "?" : "&") + key + "=" + param[key];
        });
        return url;
    }


    function resizeIframe(obj) {
        obj.style.height = obj.contentWindow.document.body.scrollHeight + 70 + 'px';
    }
</script>

<aside>
    <%--<div class="profile">--%>
    <%--<p class="photo">--%>
    <%--<img src="<c:url value='/source/images/user.gif'/>">--%>
    <%--</p>--%>
    <%--<p class="userName">관리자</p>--%>
    <%--</div>--%>
    <!-- S:main_menu -->
    <nav id="main-menu" class="typeA">
        <ul>
            <c:forEach var="row" items="${list_headmenu}" varStatus="status">
                <li data-intro="${row.menuDc}" data-step="${status.count}" data-position="right">
                    <h3>
                        <a href="${row.chkURL}" frame-id="${row.menuNo}" board-id="${row.boardId}">
                            <i class='bx bxs-dashboard'></i>
                            <span>${row.menuNm}</span></a>
                    </h3>
                    <c:if test="${fn:length(row.childMenu)>0}">
                        <ul>
                            <c:forEach var="depth2" items="${row.childMenu}" varStatus="status">
                                <li>
                                    <a href="${depth2.chkURL}" frame-id="${depth2.menuNo}" board-id="${depth2.boardId}">
                                            <%--<i class='bx bx-subdirectory-right'></i>--%>
                                            ${depth2.menuNm}</a>

                                    <c:if test="${fn:length(depth2.childMenu)>0}">

                                        <ul>
                                            <c:forEach var="depth3" items="${depth2.childMenu}" varStatus="status">
                                                <li><a href="${depth3.chkURL}" frame-id="${depth3.menuNo}" board-id="${depth3.boardId}">
                                                    <i class='bx bx-subdirectory-right'></i> ${depth3.menuNm}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </nav>
    <!-- E:main_menu -->
</aside>