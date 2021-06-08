<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<div class="subView">
    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i>
        Default Guide
    </h2>

    <nav class="navigation">
        <ol>
            <li>
                <a href="#">
                    <i class="bx bxs-home"></i>Home</a>
            </li>
            <li>
                <a href="#">Dashboard</a>
            </li>
            <li class="active">APP</li>
        </ol>
    </nav>

    <div class="toolarea">
        <button title="도움말"><i class="bx bx-info-circle"></i>도움말</button>
        <button class="excel" title="엑셀">
            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 32 32" style="enable-background:new 0 0 32 32;" xml:space="preserve">
                            <path id="surface1" class="st0" d="M15.9,4L15.9,4L4.8,6.5L4,6.7v18.7l0.8,0.2l11,2.4h0.1H18v-3h10V7H18V4H15.9z M16,6v20L6,23.8
                                V8.2L16,6z M18,9h8v14h-8v-2h2v-2h-2v-1h2v-2h-2v-1h2v-2h-2v-1h2v-2h-2V9z M21,10v2h4v-2H21z M14.2,11l-2.3,0.3L10.6,14
                                c-0.1,0.4-0.2,0.7-0.3,0.9l0,0c-0.1-0.3-0.1-0.6-0.2-0.8l-0.6-2.3L7.4,12l-0.2,0L9,16l-2,4l2.2,0.2l0.9-2.5c0.1-0.3,0.2-0.6,0.2-0.7
                                l0,0c0.1,0.3,0.1,0.6,0.2,0.7l1.3,2.9l2.4,0.3l-2.7-5L14.2,11z M21,13v2h4v-2H21z M21,16v2h4v-2H21z M21,19v2h4v-2H21z"></path>
                            </svg>
            엑셀파일

        </button>
        <button title="다운로드"><i class="bx bxs-download"></i>다운로드</button>
        <button title="프린트"><i class="bx bxs-printer"></i>프린트</button>
        <button class="color-gray" title="검색"><i class="bx bx-search"></i>검색</button>
        <button class="color-main" title="검색"><i class="bx bxs-pencil"></i>등록</button>
    </div>

    <div class="row">
        <div class="col-md-8">
            <h3 class="btitle">
                검색
            </h3>

            <div class="white-box">

                <div class="rows">



                    <button class="button"><i class='bx bx-slider-alt'></i>서브버튼</button>

                    <button class="button main"><i class='bx bx-slider-alt'></i>메인버튼</button>

                    <button class="button sub btn-ms"><i class='bx bx-slider-alt'></i>btn-ms</button>

                    <button class="button sub btn-xs"><i class='bx bx-slider-alt'></i>btn-xs</button>

                    <button class="button modal-open" data="modal01"><i class='bx bx-slider-alt'></i>모달창 열기</button>
                </div>
                <div id="modal" class="modal01 open">
                    <div class="modal_content">
                        <h3>모달입니다.
                            <button class="close">
                                <i class="bx bx-x"></i>창닫기</button>
                        </h3>

                        내용
                    </div>
                </div>

                <div class="rows">


                    <label class="before">Label</label>
                    <input class="w100" class="main" type="date">

                    <label class="before">Label</label>
                    <span class="select-outline">
                                        <select class="w400">
                                            <option>w400</option>
                                        </select>
                                    </span>



                </div>


                <div class="rows">


                    <label class="before">Label</label>
                    <span class="select-outline">
                                        <select class="main">
                                            <option>default</option>
                                        </select>
                                    </span>


                </div>

                <div class="rows">

                    <label class="before">Label</label>
                    <input class="w40" type="text" placeholder="w40">

                    <label class="before">Label</label>
                    <input class="w100 main" type="text" placeholder="w100">

                    <label class="before">Label</label>
                    <input class="w150 main" type="text" placeholder="w150">



                </div>

                <div class="rows">

                    <label class="before">Label</label>
                    <input class="w200 " type="text" placeholder="w200">



                </div>


                <div class="rows">


                    <label class="before">Label</label>
                    <input class="w400 main" type="text" placeholder="w400">



                </div>


                <div class="rows">


                    <label class="before">Label</label>
                    <input class="wfull" class="main" type="text" placeholder="wfull">



                </div>



                <div class="rows">
                    <textarea class="main">
        인풋요소 사이즈 및 컬러는 클래스명으로 제어
        버튼가로사이즈 : w40, w100, w150, w200, w400, wfull
        버튼 크기 : 기본, btn-ms, btn-xs
        컬러 : main, sub, 기본
        버튼에 필요한 아이콘 : https://boxicons.com 코드 복사해서 활용 예: <i class='bx bx-library'></i>
                    </textarea>
                </div>


                <!--
                    width 클래스
                    w40 : 40px
                    w100 : 100pxa
                    w150 : 150px
                    w200 : 200px
                    w400 : 400px
                    wfull : 100%
                -->


                <div class="rows">

                    <input type="checkbox" id="check1">
                    <label for="check1"><i class='bx bxs-checkbox-checked'></i>체크박스</label>

                    <input type="checkbox" id="radio1">
                    <label for="radio1"><i class='bx bx-radio-circle-marked' ></i>라디오버튼</label>
                </div>


            </div>
        </div>


        <div class="col-md-4">
            <h3 class="btitle">
                테이블
            </h3>

            <div class="white-box">
                <table class="basic">
                    <thead>
                    <tr>
                        <th>흠</th>
                        <th>흠</th>
                        <th>흠</th>
                        <th>흠</th>
                        <th>흠</th>
                        <th>흠</th>
                        <th>흠</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>음</td>
                        <td class="center">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                    </tr>
                    <tr>
                        <td>음</td>
                        <td class="center">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                    </tr>
                    <tr>
                        <td>음</td>
                        <td class="center">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                    </tr>
                    <tr>
                        <td>음</td>
                        <td class="center">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                    </tr>
                    <tr>
                        <td>음</td>
                        <td class="center">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                        <td class="right">음</td>
                    </tr>
                    </tbody>

                </table>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-12">

        <h3 class="btitle">
            테이블 가로형
        </h3>


        <div class="white-box">
            <table class="landscape">

                <tbody class="landscape">
                <tr>
                    <th>음</th>
                    <td class="center">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                </tr>
                <tr>
                    <th>음</th>
                    <td class="center">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                </tr>
                <tr>
                    <th>음</td>
                    <td class="center">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                </tr>
                <tr>
                    <th>음</th>
                    <td class="center">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                </tr>
                <tr>
                    <th>음</th>
                    <td class="center">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                    <td class="right">음</td>
                </tr>
                </tbody>

            </table>
        </div>


        </div>
    </div>



    <div class="row">
        <div class="col-md-6">

        <h3 class="btitle">
            테이블 가로형2
        </h3>

        <div class="white-box">
            <table class="landscape">

                <tbody class="landscape">
                <tr>
                    <th>음</th>
                    <td class="center">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                </tr>
                <tr>
                    <th>음</th>
                    <td class="center">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                </tr>
                <tr>
                    <th>음</th>
                    <td class="center">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                </tr>
                <tr>
                    <th>음</th>
                    <td class="center">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                </tr>
                <tr>
                    <th>음</th>
                    <td class="center">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                    <th>음</th>
                    <td class="right">음</td>
                </tr>
                </tbody>

            </table>
        </div>

        </div>

        <div class="col-md-6">

            <h3 class="btitle">
                테이블 가로형2
            </h3>

            <div class="white-box">
                <table class="landscape">

                    <tbody class="landscape">
                    <tr>
                        <th>음</th>
                        <td class="center">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                    </tr>
                    <tr>
                        <th>음</th>
                        <td class="center">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                    </tr>
                    <tr>
                        <th>음</th>
                        <td class="center">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                    </tr>
                    <tr>
                        <th>음</th>
                        <td class="center">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                    </tr>
                    <tr>
                        <th>음</th>
                        <td class="center">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                        <th>음</th>
                        <td class="right">음</td>
                    </tr>
                    </tbody>

                </table>
            </div>

        </div>
    </div>


    <article class="pagenation">
        <a href="?pageIndex=1" title="처음으로 이동" class="jump" onclick="linkPage(1);return false; ">처음<i class="bx bx-chevrons-left " aria-hidden="true"></i></a>
        <a href="?pageIndex=1" title="이전 페이지로 이동" class="move" onclick="linkPage(1);return false; ">이전<i class="bx bx-chevron-left " aria-hidden="true"></i></a>
        <strong>1</strong>
        <a href="?pageIndex=2" onclick="linkPage(2);return false; " title="2 페이지로 이동">2</a>
        <a href="?pageIndex=3" onclick="linkPage(3);return false; " title="3 페이지로 이동">3</a>
        <a href="?pageIndex=4" onclick="linkPage(4);return false; " title="4 페이지로 이동">4</a>
        <a href="?pageIndex=5" onclick="linkPage(5);return false; " title="5 페이지로 이동">5</a>
        <a href="?pageIndex=11" title="다음 페이지로 이동" class="move" onclick="linkPage(6);return false; ">다음<i class="bx bx-chevron-right" aria-hidden="true"></i></a>
        <a href="?pageIndex=17" title="마지막 페이지로 이동" class="jump" onclick="linkPage(17);return false; ">마지막<i class="bx bx-chevrons-right" aria-hidden="true"></i></a>
    </article>

    <div class="btn-set align">
        <div class="left">
            <button class="button btn-xs">수정</button>
            <button class="button btn-ms">삭제</button>
            <button class="button main">등록</button>
        </div>
        <div class="right">
            <button class="button">수정</button>
            <button class="button">삭제</button>
            <button class="button main">등록</button>
        </div>
    </div>

    <div class="btn-set">
        <button class="button">수정</button>
        <button class="button">삭제</button>
        <button class="button main">등록</button>
    </div>

    <div class="btn-set right">
        <button class="button">수정</button>
        <button class="button">삭제</button>
        <button class="button main">등록</button>
    </div>

    <div class="btn-set center big">
        <button class="button">수정</button>
        <button class="button sub">삭제</button>
        <button class="button main">등록</button>
    </div>

    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i>
        Excel Upload Guide
    </h2>
    <div class="row">
        <div class="col-md-12">
            <h3 class="btitle">
                Grid Excel Uploader
                <form name="excelForm" enctype="multipart/form-data">
                    <input type="file" name="uploadFile"/>
                    <button type="button" class="button btn-xs main" onclick="gridExcelUplad()">Excel 업로드</button>
                </form>
            </h3>
        </div>
        <div class="white-box">
            <div id="dataExcelGrid"></div>
        </div>
    </div>

</div>
<script>

    function gridExcelUplad() {
        var formData = new FormData(document.excelForm);
        $ifx.ajax('<c:url value="/cms/info/sample/excelUpload.do"/>', {
            method: 'POST',
            processData: false,
            contentType: false,
            data: formData,
            success: function(res){
                alert(JSON.stringify(res))
            }
        })
    }
</script>

