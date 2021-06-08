$(function () {



    var windowh = $(window).height();
    var windowW = $(window).width();








    $(window).scroll(function(e) {

        var scrollY = $(document).scrollTop();

        if (scrollY > "50") {
            $(".sub-layout #header, .sub-layout div.sub-head, .sub-layout article.menu-depth2").addClass("fix");

        } else {
            $(".sub-layout #header").removeClass("fix");
            $(".sub-layout #header, .sub-layout div.sub-head, .sub-layout article.menu-depth2").removeClass("fix");

            $("nav.sub-menu").removeClass("active");

        }

    });

    $("article.history-navi i:first-of-type").addClass("off");

    $("article.history-navi .outline").on("scroll", function () {
        if ($(this).scrollLeft() == 0) {
            $(this).parent().find(".bx-chevron-left").addClass("off");
        } else {
            $(this).parent().find(".bx-chevron-left").removeClass("off");

        }

        if ($(this).find("button:last-of-type").offset().left + $(this).find("button").width() + 25 < $(window).width()) {
            $(this).parent().find(".bx-chevron-right").addClass("off");
        } else {
            $(this).parent().find(".bx-chevron-right").removeClass("off");

        }
    });


    $("div.language button").on("click", function () {
        $("div.language").toggleClass("on");
    });


    $("article.history-navi button").on("click", function () {

        $("article.history-navi button").removeClass("active");
        $(this).addClass("active");

        $("article.history div.container div").removeClass("active");

        $("article.history div.container div.year"+ $(this).attr("data")).addClass("active");

    });


    $("button.main-menu").on("click", function () {
        $("nav#main-menu").toggleClass("on");
    });


    $("nav#main-menu button.close").on("click", function () {
        $("nav#main-menu").toggleClass("on");
    });


    $("nav.navi button").on("click", function () {
        $("nav.navi").toggleClass("active");
    });

    $(".menu-depth3 button").on("click", function () {
        $(".menu-depth3").toggleClass("active");
    });



    $("nav.sub-menu button").on("click", function (e) {

        $("nav.sub-menu").toggleClass("active");

    });


    // $("article.menu-depth3 a.active").on("click", function (e) {

    //     if ( (windowW < 1200) || ($("div.sub-head").hasClass("fix")) ) {
    //         console.log("무야호");

    //         $("article.menu-depth3").toggleClass("on");

    //         e.preventDefault();
    //     }

    // });





    $('.event-program-list li p.image').each(function () {
        $(this).css(
            "background",
            "url('" + $(this).find("img").attr("src") + "') no-repeat center / cover"
        );
    });


    $('.event-additional-program li div.photo').each(function () {
        $(this).css(
            "background",
            "url('" + $(this).find("img").attr("src") + "') no-repeat center / cover"
        );
    });


    $('.photo-view .big p.image').each(function () {
        $(this).css(
            "background",
            "url('" + $(this).find("img").attr("src") + "') no-repeat center / cover"
        );
    });


    $('.photo-view .thumb li a').each(function () {
        $(this).css(
            "background",
            "url('" + $(this).find("img").attr("src") + "') no-repeat center / cover"
        );
    });




    $('.mov-list p.image').each(function () {
        $(this).css(
            "background",
            "url('" + $(this).find("img").attr("src") + "') no-repeat center / cover"
        );
    });


    $('.photo-category-list p.image').each(function () {
        $(this).css(
            "background",
            "url('" + $(this).find("img").attr("src") + "') no-repeat center / cover"
        );
    });


    $("div.find-form").last().hide();

    var radioContent = $("div.find-form");


    $("div.find-type input[type='radio']").click(function() {

        radioContent.hide();
        radioContent.eq($("input[type='radio']").index(this)).show();


    });


    var $popStatus = $('.pagingInfo');
    var $popSlickElement = $('.outsite-slider div.slider')

    $popSlickElement.on('init reInit afterChange', function (event, slick, currentSlide, nextSlide) {

        var i = (currentSlide ? currentSlide : 0) + 1;

        $popStatus.html('<strong>'+ i + '</strong> /' + slick.slideCount);

    });


    $popSlickElement.slick({
        mobileFirst: true,
        autoplay:true,
        speed: 1000,
        dots: false,
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        nextArrow:$('.outsite-slider button.right'),
        prevArrow:$('.outsite-slider button.left'),

        responsive: [
            {
                breakpoint: 1200,
                settings: {

                    slidesToShow: 3,
                    slidesToScroll: 1,
                    variableWidth:true,
                    dotsClass: 'custom_paging',
                    customPaging: function (slider, i) {
                        //FYI just have a look at the object to find available information
                        //press f12 to access the console in most browsers
                        //you could also debug or look in the source
                        var slideNumber   = (i + 1),
                            totalSlides = slider.slideCount;
                        return '<a class="custom-dot" role="button" title="' + slideNumber + ' of ' + totalSlides + '"><span class="string">' + slideNumber + '</span></a>';
                    }

                }
            }
        ]



    });

try {
    var wall = new Freewall(".expat-type01 .outline");
    wall.reset({
        selector: '.item',
        animate: true,
        cellW: 200,
        cellH: 'auto',
        gutterX: 30,
        gutterY: 30,
        onResize: function() {
            wall.fitWidth();
        }
    });

    wall.fitWidth();




    var wall2 = new Freewall(".photo-wall .wall");
    wall2.reset({
        selector: '.item',
        animate: true,
        cellW: 200,
        cellH: 'auto',
        gutterX: 30,
        gutterY: 30,
        onResize: function() {
            wall2.fitWidth();
        }
    });

    wall2.fitWidth();


} catch(e) {}





    var $eventDetailPhoto = $('.event-detail-photo div.slider');


    $eventDetailPhoto.slick({
        mobileFirst: true,
        autoplay:true,
        speed: 1000,
        dots: false,
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        centerMode: true,
        adaptiveHeight: true,
        nextArrow:$('.event-detail-photo button.right'),
        prevArrow:$('.event-detail-photo button.left'),

        responsive: [
            {
                breakpoint: 1200,
                settings: {

                    slidesToShow: 3,
                    slidesToScroll: 1,
                    variableWidth:true,
                    dotsClass: 'custom_paging',
                    customPaging: function (slider, i) {
                        //FYI just have a look at the object to find available information
                        //press f12 to access the console in most browsers
                        //you could also debug or look in the source
                        var slideNumber   = (i + 1),
                            totalSlides = slider.slideCount;
                        return '<a class="custom-dot" role="button" title="' + slideNumber + ' of ' + totalSlides + '"><span class="string">' + slideNumber + '</span></a>';
                    }

                }
            }
        ]



    });




});
