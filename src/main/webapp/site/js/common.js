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

    pcmenu();


    $(window).on("resize", function(){
        
        windowh = $(window).height();
        windowW = $(window).width();
        
        $("nav#main-menu h3 a").unbind();
        pcmenu();
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





});
