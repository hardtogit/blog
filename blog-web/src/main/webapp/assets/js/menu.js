define(function(require){

	var $ = require('jquery');
    require('bootstrap');

    // sidebar toggle
    $('#sidebar .sub-menu > a').click(function () {
        var last = $('.sub-menu.open', $('#sidebar'));
        last.removeClass("active");
        $('.arrow', last).removeClass("active");
        $('.sub', last).slideUp(200);
        var sub = $(this).next();
        if (sub.is(":visible")) {
            $('.arrow', $(this)).removeClass("active");
            $(this).parent().removeClass("active");
            sub.slideUp(200);
        } else {
            $('.arrow', $(this)).addClass("active");
            $(this).parent().addClass("active");
            sub.slideDown(200);
        }
        var o = ($(this).offset());
        diff = 200 - o.top;
    });

    $(document).ready(function() {
        var path = document.location.href;

        // ******************顶部导航栏******************
        path = path.substr(path.indexOf($CONFIG.base_url) + 1);
        if (path.indexOf("sum", 0) >= 0) {
            $('li:has([href*="sum"])').addClass('active').siblings().removeClass('active');
        } else if (path.indexOf("search", 0) >= 0) {
            $('li:has([href*="search"])').addClass('active').siblings().removeClass('active');
        }

        // ******************侧边栏选中效果******************
        path=path.substr(path.lastIndexOf('/')-5);
        var index=path.lastIndexOf("?");
        if(index>0){
            path=path.slice(0,index);
        };
        $("#sidebar>ul>li>ul.sub>li a").each(function(){
            var url=$(this).attr("href");
            var tabPath=url.substr(url.lastIndexOf('/')-5);
            if(tabPath==path){
                $(this).parent().addClass('active').siblings().removeClass('active');
                $(this).parents(".sub-menu").addClass("active");
            }
        });

    });



});
