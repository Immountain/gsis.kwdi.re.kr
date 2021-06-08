$(document).ready(function() {

    $("header button.menu").on("click", function(){
        $("body").toggleClass("open");
        
        $("nav#main-menu ul li").removeClass("active");
        $("nav#main-menu ul li ul li a").removeClass("on");
        $("nav#main-menu ul li ul li ul").removeClass("on");

    });



    $(".modal-open").on("click", function(){
        $("#modal."+ $(this).attr("data")).addClass("open");

    });

    $("#modal .close").on("click", function(){
        $(this).parent().parent().parent().removeClass("open");
    });






    
    $("nav#main-menu h3").each(function () {
        if ($(this).parent().find("ul").length) {
            $(this).find("a").attr("href", "#");

        }
    })


    $("nav#main-menu h3").on("click", function () {

        if($(this).parent().hasClass("active")) {

            $(this).parent().removeClass("active");

        } else {
            $("nav#main-menu>ul>li").removeClass("active");
            $(this).parent().addClass("active");

        }

        
        $("nav#main-menu>ul>li>ul>li>a").removeClass("on");
        $("nav#main-menu>ul>li>ul>li>ul").removeClass("on");
        

        if ($(this).parent().find("ul").length) {
            $("body").addClass("open");
        }

    });


    
    $("nav#main-menu>ul>li>ul>li").each(function () {

        if ($(this).find("ul").length) {

            $(this).find(">a").attr("href", "#");

        }
    });


    $("nav#main-menu>ul>li>ul>li>a").on("click", function () {





        if($(this).hasClass("on")) {
            
            $(this).parent().find("ul").removeClass("on");
            $(this).removeClass("on");

        } else {


            $("nav#main-menu>ul>li>ul>li>a").removeClass("on");
            $("nav#main-menu>ul>li>ul>li>ul").removeClass("on");
            $(this).addClass("on")
            $(this).parent().find("ul").addClass("on");

        }








    });

});

