function getAllImagesDonePromise() {
  // A jQuery-style promise we'll resolve
  var d = $.Deferred();

  // Get all images to start with (even complete ones)
  var imgs = $("img");

  // Add a one-time event handler for the load and error events on them
  imgs.one("load.allimages error.allimages", function() {
      // This one completed, remove it
      imgs = imgs.not(this);
      if (imgs.length == 0) {
          // It was the last, resolve
          d.resolve();
      }
  });

  // Find the completed ones
  var complete = imgs.filter(function() { return this.complete; });

  // Remove our handler from completed ones, remove them from our list
  complete.off(".allimages");
  imgs = imgs.not(complete);
  complete = undefined; // Don't need it anymore

  // If none left, resolve; otherwise wait for events
  if (imgs.length == 0) {
      d.resolve();
  }

  // Return the promise for our deferred
  return d.promise();
}


$(window).on("scroll", function(){
    if ($(window).scrollTop() > 0) {
        $( "body.image #header" ).addClass("fit");
    } else {
        $( "body.image #header" ).removeClass("fit");
    }
});


$(document).ready(function() {


    
    $( "#main-menu" ).on("mouseenter", function() {
        $( "#header" ).addClass("on");
        $( this ).addClass("on");
      });

      
    $( "#main-menu" ).on("mouseleave", function() {
        $( "#header" ).removeClass("on");
        $( this ).removeClass("on");
      });


      
      $( "button.allmenu" ).on("click", function() {
        $( "#header" ).toggleClass("on");
        $( "#main-menu" ).toggleClass("on");
      });


      
      $( "#main-menu .close" ).on("click", function() {
        $( "#header" ).toggleClass("on");
        $( "#main-menu" ).toggleClass("on");
      });


    $('.main-slider').slick({
        autoplay:true,
        speed: 1000,
        dots: true,
        infinite: true,
        nextArrow:$('.dm-next'),
        prevArrow:$('.dm-prev')
    });

    $('.business-slider').slick({
      autoplay:true,
      mobileFirst: true,
      autoplaySpeed:700,
      speed: 1000,
      arrows: false,
      infinite: true,
      responsive: [
        {
          breakpoint: 1200,
          settings: "unslick"
      }
        ]
    });


    $('.slides').slick({
      autoplay:true,
      speed: 1000,
      dots: true,
      dots:false,
      infinite: true,
      nextArrow:$('.next'),
      prevArrow:$('.prev')
  });



    $(window).on('resize orientationchange', function() {
      $('.business-slider').slick('resize');
    });





    $('.main-slider div').each(function() {
        $(this).css("background", "url('" + $(this).find("img").attr("src") + "') no-repeat center / cover");
    });

    $('body.main .news p.img').each(function() {
      $(this).css("background", "url('" + $(this).find("img").attr("src") + "') no-repeat top / cover");
  });



  getAllImagesDonePromise().then(function() { 

    var $grids = $('.portfolio .list').masonry({
      columnWidth: '.grid-item',
      itemSelector: '.grid-item',
      percentPosition: true,
      gutter:'.gutter-sizer'
    });


  });


  function viewMode() {
    
    try {

    if($(window).width() < 1200) {

      $grids.masonry({
        columnWidth: '.grid-item',
        itemSelector: '.grid-item',
        percentPosition: true,
        gutter:'.gutter-sizer'
      });



    } else {
      
      if ($grids.length > 0 && $grids.data('masonry')) {
        // Destroy masonry if exists.
        $grids.masonry('destroy');
      }


    }

  } catch(e) {}
}

viewMode();

$(window).resize(function(){
    viewMode();
});

  
});


