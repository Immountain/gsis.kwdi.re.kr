$(function () {



    var windowh = $(window).height();
    var windowW = $(window).width();


    function pcmenu() {

        $(document).on("mousemove", function(e){
            if (windowW > 1200 && e.pageY > 300) {
                $("nav#main-menu").removeClass("on");
            }
        });
    }
    
    function mainSlide() {
        if (windowW < 1200) {
            $(".main-issue .slider img").attr("src", $(".main-issue .slider img").attr("data-for-mobile"));
        } else {
            $(".main-issue .slider img").attr("src", $(".main-issue .slider img").attr("data-for-pc"));
        }
    }


    pcmenu();
    mainSlide();

    $(window).on("resize", function(){
        
        windowh = $(window).height();
        windowW = $(window).width();
        
        $("nav#main-menu h3 a").unbind();
        pcmenu();


        mainSlide();

    });




    $(".equality .item .outline>ul>li>strong").on("click", function () {


        if ($(this).hasClass("on")) {
            $(this).removeClass("on");
        } else {
            $(".equality .item .outline>ul>li>strong.on").removeClass("on");
            $(this).addClass("on");
        }
    });


    $("button.main-menu").on("click", function () {
        $("nav#main-menu").toggleClass("on");
    });

    $("nav#main-menu button.close").on("click", function () {
        $("nav#main-menu").toggleClass("on");
    });

    $("nav#main-menu h3 a").on("click", function (e) {
        
        $("nav#main-menu h3").removeClass("on");



        if (windowW < 1200 && $(this).parent().parent().find("ul").length) {

            $(this).parent().addClass("on");
            e.preventDefault();

        }
        else {
            $("nav#main-menu h3 a").unbind();

        }
    });

    $("nav#main-menu").on("mouseenter", function(){
        $(this).addClass("on");
    });

    // $(".db-steps .step h5").on("click", function () {
    //     $(this).toggleClass("on");
    // });

    $(".db-steps ul a").on("click", function () {

        if ($(this).hasClass("on")) {
            $(this).removeClass("on");
        } else {
            $(this).parent().parent().find("a").removeClass("on");
            $(this).addClass("on");
        }

        if ($(this).parent().parent().parent().hasClass("step01")) {
            $(".db-steps .step02").addClass("on");
        }

        if ($(this).parent().parent().parent().hasClass("step02")) {
            $(".db-steps .step03").addClass("on");
        }

        if ($(this).parent().parent().parent().hasClass("step03")) {
            $(".layer-pop").addClass("on");
        }
    });



    $(".layer-pop button.close").on("click", function () {
        $(".layer-pop").removeClass("on");
    });



    $(".micro-list button").on("click", function () {
        $(".download-pop").addClass("on");
    });

    $(".download-pop button.close").on("click", function () {
        $(".download-pop").removeClass("on");
    });

    $(".micro-view .tabs button").on("click", function () {
        $(".tab-contents").removeClass("on");

        $(".tab-contents."+ $(this).attr("data")).addClass("on");
        ;
    });


    $(".find .find-type input").on("click", function () {
        $(".find-form").removeClass("on");

        if ($(this).attr("id") == "type01") {


            $("div.form-id").addClass("on");

        } else {

            $("div.form-pw").addClass("on");

        }
    });




    var $mainIssue = $("section.main-issue div.slider");
    $mainIssue.slick({
        arrows:false,
        autoplay:true,
        speed: 1000,
        dots: true,
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,

        

    });

    var $outsite = $("section.outsite-slider .slider");

    $outsite.slick({
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




});
