<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="modal-content big">
    <h2>소속 기업/기관 찾기 <button class="close">닫기</button></h2>
    <div class="inside">

        <div class="co-check">

                    <span class="select-outline">
                        <span>@</span>
                        <select id="mail" name="mail">
                            <option value="">사업자번호</option>
                        </select>
                    </span>
            <div class="search-box">
                <input type="text">
                <button><i class="bx bx-search"></i>검색</button>
            </div>

        </div>
        <ul class="co-list">
            <li>
                <a href="">
                    <p class="select"><span>선택</span><u><button>선택</button></u></p>
                    <p class="num"><span>사업자번호</span><u>03-5624-5982255</u></p>
                    <p class="name"><span>기업/기관명</span><u>제주테크노파크</u></p>
                    <p class="ceo"><span>대표자명</span><u>홍길동</u></p>
                </a>
            </li>
            <li>
                <a href="">
                    <p class="select"><span>선택</span><u><button>선택</button></u></p>
                    <p class="num"><span>사업자번호</span><u>03-5624-5872255</u></p>
                    <p class="name"><span>기업/기관명</span><u>제주테크노파크</u></p>
                    <p class="ceo"><span>대표자명</span><u>홍길동</u></p>
                </a>
            </li>
        </ul>
        <article class="pagenation">
            <a href="?pageIndex=1" title="이전 페이지로 이동" class="move" onclick="linkPage(1);return false; ">이전<i class="bx bx-chevron-left " aria-hidden="true"></i></a>
            <strong>1</strong>
            <a href="?pageIndex=2" onclick="linkPage(2);return false; " title="2 페이지로 이동">2</a>
            <a href="?pageIndex=3" onclick="linkPage(3);return false; " title="3 페이지로 이동">3</a>
            <a href="?pageIndex=11" title="다음 페이지로 이동" class="move" onclick="linkPage(6);return false; ">다음<i class="bx bx-chevron-right" aria-hidden="true"></i></a>
        </article>
    </div>
</div>
