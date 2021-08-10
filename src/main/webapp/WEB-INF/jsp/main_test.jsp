<div id="content" class="main">


    <section class="main-issue">
        <div class="container">

            <c:forEach items="${imageList}" var="item">
                <img src="/site/info/file/ImageView.do?atchFileId=${item.bannerImage}" >

            </c:forEach>
            <div class="slider">


                <c:forEach items="${imageList}" var="item">





                    <div class="slide">
                        <img src="<c:url value="/site/info/file/ImageView.do?atchFileId=${item.bannerImage}"/>" data-for-pc="<c:url value="/site/info/file/ImageView.do?atchFileId=${item.bannerImage}"/>" data-for-mobile="<c:url value="/site/info/file/ImageThumbnailView.do?width=400&height=400&atchFileId=${item.bannerImage}"/>" alt="">
                    </div>
                </c:forEach>

            </div>

            <div class="etc-issue">
                <ul>
                    <c:forEach items="${txtList}" var="item">
                        <li>
                            <span>${item.bannerNm}</span>
                            <i>${item.bannerMemo}
                                <strong>${item.bannerTxt}</strong>
                            </i>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </div>
    </section>


    <section class="main-category">
        <div class="container">

            <ul>
                <li>
                    <a class="category01" href="/statsdb/list.do?parent1id=CT_01">
                        인구
                    </a>
                </li>
                <li>
                    <a class="category02" href="/statsdb/list.do?parent1id=CT_02">
                        가족
                    </a>
                </li>
                <li>
                    <a class="category03" href="/statsdb/list.do?parent1id=CT_03">
                        보육
                    </a>
                </li>
                <li>
                    <a class="category04" href="/statsdb/list.do?parent1id=CT_04">
                        교육
                    </a>
                </li>
                <li>
                    <a class="category05" href="/statsdb/list.do?parent1id=CT_05">
                        경제활동
                    </a>
                </li>
                <li>
                    <a class="category06" href="/statsdb/list.do?parent1id=CT_06">
                        보건
                    </a>
                </li>
                <li>
                    <a class="category07" href="/statsdb/list.do?parent1id=CT_07">
                        복지
                    </a>
                </li>
                <li>
                    <a class="category08" href="/statsdb/list.do?parent1id=CT_08">
                        정치 및 사회참여
                    </a>
                </li>
                <li>
                    <a class="category09" href="/statsdb/list.do?parent1id=CT_09">
                        문화 및 정보
                    </a>
                </li>
                <li>
                    <a class="category10" href="/statsdb/list.do?parent1id=CT_10">
                        안전
                    </a>
                </li>
            </ul>

        </div>
    </section>


    <section class="main-content">
        <div class="container">

            <div class="theme-chart">
                <h3 class="title">
                    여성취업자의 직업분포

                    <div class="btns">
                        <button class="prev"><i class='bx bx-chevron-left' ></i>이전</button>
                        <button class="next on"><i class='bx bx-chevron-right' ></i>다음</button>
                    </div>

                </h3>
                <div class="chart-outline">
                    챠트가 들어갑니다.
                </div>
            </div>

            <div class="update-info">
                <h3 class="title">
                    업데이트안내

                    <div class="btns">
                        <a href=""><i class='bx bx-chevron-right' ></i>다음</a>
                    </div>

                </h3>
                <ul class="list">

                    <c:forEach items="${updateList}" var="item">

                        <li>
                            <a href="${item.updateKeepUrl}${item.updateKeepId}">
                                <strong>${item.updateKeepNm}(${item.regDt})</strong>
                                <span>${item.themaNm}</span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </div>
    </section>

    <section class="outsite-slider">
        <div class="container">
            <div class="slider">


                <div class="slides">
                    <a href="https://www.jeju.go.kr" target="_BLANK"><img src="/site/images/banner01.png" alt="제주특별자치도" />제주특별자치도</a>
                </div>
                <div class="slides">
                    <a href="https://www.jeju.go.kr" target="_BLANK"><img src="/site/images/banner01.png" alt="제주특별자치도" />제주특별자치도</a>
                </div>
                <div class="slides">
                    <a href="https://www.jeju.go.kr" target="_BLANK"><img src="/site/images/banner01.png" alt="제주특별자치도" />제주특별자치도</a>
                </div>
                <div class="slides">
                    <a href="https://www.jeju.go.kr" target="_BLANK"><img src="/site/images/banner01.png" alt="제주특별자치도" />제주특별자치도</a>
                </div>
                <div class="slides">
                    <a href="https://www.jeju.go.kr" target="_BLANK"><img src="/site/images/banner01.png" alt="제주특별자치도" />제주특별자치도</a>
                </div>

            </div>
            <button class="arrow left">이전<i class='bx bx-chevron-left'></i></button>
            <button class="arrow right">다음<i class='bx bx-chevron-right'></i></button>
        </div>

    </section>
</div>
<!-- E:mainContent -->
