<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="content" class="sub sub03">

    <!-- 각 구성요소 article로 모듈화 -->
    <div class="sub-head">

        <nav class="sub-menu">
            <button>${menuInfo.parentNm}</button>
            <info:getMenuModel modelName="subSiteMenu" groupId="${SITEINFO.langCd}-primary" siteMemuId=""/>

            <div class="outline">
                <c:forEach items="${subSiteMenu}" var="item">
                    <c:if test="${item.viewYn eq 'Y'}">
                        <a <c:if test="${item.siteMemuId eq menuInfo.parentId}">class="active"</c:if> href="<info:url value="${item.url}"/>">${item.siteMemuNm}</a>
                    </c:if>
                </c:forEach>
            </div>
        </nav>


        <!-- background-item -->
        <div class='waves'>
            <div class='wave -one'></div>
            <div class='wave -two'></div>
            <div class='wave -three'></div>
        </div>
        <div class="bubble" >
            <ul class="circles">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div >

    </div>




    <div class="content-box">






        <article class="navigation">
            <div class="container">
                <p>Home</p>

                <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>

            </div>

        </article>

        <article class="expat-type01">

            <div class="container">

                <h4>国外</h4>

                <div class="outline">

                    <div class="item">
                        <h5 class="en">
                            アメリカ
                        </h5>

                        <dl>
                            <dt>在米シカゴ済州道民会</dt>
                            <dd>創立年度 : 1987, 会員数 : 300</dd>

                            <dt>在米ミシガン済州道民会</dt>
                            <dd>創立年度 : 1988, 会員数 : 100</dd>

                            <dt>在米済州特別自治道民会 (NY)</dt>
                            <dd>創立年度 : 1978, 会員数 : 500</dd>

                            <dt>在米南カリフォルニア済州道郷友会 (LA)</dt>
                            <dd>創立年度 : 1988, 会員数 : 600</dd>

                            <dt>在米アトランタ済州島特別自治道民会</dt>
                            <dd>創立年度 : 1991년, 会員数 : 400</dd>

                            <dt>在米ワシントン地区済州島特別自治道民会</dt>
                            <dd>創立年度 : 1991, 会員数 : 400</dd>
                        </dl>
                    </div>

                    <div class="item">
                        <h5 class="cn">
                            中国 アメリカ
                        </h5>

                        <dl>
                            <dt>中国上海済州特別自治道民会</dt>
                            <dd>創立年度 : 2006, 会員数 : 50</dd>
                        </dl>
                    </div>

                    <div class="item">
                        <h5 class="jp">
                            日本
                        </h5>

                        <dl>
                            <dt>在日本関西済州特別自治道民協会</dt>
                            <dd>創立年度 : 1994, 会員数 : 80,000</dd>

                            <dt>在日本関東済州特別自治道民協会</dt>
                            <dd>創立年度 : 1961, 会員数 : 43,000</dd>

                            <dt>在日本仙台済州特別自治道民協会</dt>
                            <dd>創立年度 : 1975, 会員数 : 250</dd>
                        </dl>
                    </div>

                    <div class="item">
                        <h5 class="ca">
                            カナダ
                        </h5>

                        <dl>
                            <dt>バンクーバー済州特別自治道民会</dt>
                            <dd>創立年度 : 1999, 会員数 : 50</dd>

                            <dt>カナダ済州郷友会</dt>
                            <dd>創立年度 : 2000, 会員数 : 50</dd>
                        </dl>
                    </div>

                    <div class="item">
                        <h5 class="au">
                            オーストラリア
                        </h5>

                        <dl>
                            <dt>在オーストラリア済州郷友会</dt>
                            <dd>創立年度 : 2007 , 会員数 : 30</dd>
                        </dl>
                    </div>








                </div>
            </div>

        </article>

        <article class="expat-type02">

            <div class="container">

                <h4>国内</h4>

                <div class="outline">

                    <div class="item">


                        <ul>
                            <li>
                                <strong>ソウル済州特別自治道民会</strong>
                                <span>会員数 : 250,000</span>
                            </li>
                            <li>
                                <strong>安山済州特別自治道民</strong>
                                <span>会員数 : 2,000</span>
                            </li>
                            <li>
                                <strong>仁川済州特別自治道民</strong>
                                <span>会員数 : 6,000</span>
                            </li>
                            <li>
                                <strong>水原済州特別自治道民</strong>
                                <span>会員数 : 130</span>
                            </li>
                            <li>
                                <strong>軍浦済州特別自治道民</strong>
                                <span>会員数 : 1,000</span>
                            </li>
                            <li>
                                <strong>春川済州特別自治道民</strong>
                                <span>会員数 : 90</span>
                            </li>
                            <li>
                                <strong>大田済州特別自治道民</strong>
                                <span>会員数 : 400</span>
                            </li>
                            <li>
                                <strong>慶北済州連合道民会</strong>
                                <span>会員数 : 18,000</span>
                            </li>
                            <li>
                                <strong>蔚山済州特別自治道民</strong>
                                <span>会員数 : 30,000</span>
                            </li>
                            <li>
                                <strong>釜山済州特別自治道民</strong>
                                <span>会員数 : 22,000</span>
                            </li>
                            <li>
                                <strong>西部慶南済州特別自治道民</strong>
                                <span>会員数 : 5,000</span>
                            </li>
                            <li>
                                <strong>光州湖南済州特別自治道民</strong>
                                <span>会員数 : 300</span>
                            </li>
                            <li>
                                <strong>嶺北済州道民連合会</strong>
                                <span>会員数 : 500</span>
                            </li>
                            <li>
                                <strong>慶南済州特別自治道民</strong>
                                <span>会員数 : 1,600</span>
                            </li>
                            <li>
                                <strong>大邸済州特別自治道民会</strong>
                                <span>会員数 : 400</span>
                        </ul>
                    </div>




                </div>
            </div>

        </article>






    </div>





</div>
