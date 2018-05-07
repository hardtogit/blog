define(function(require, exports, module){

	var $ = require('jquery');
    var List = require('../../util/list.js');
    var template = require('template');
    require('jquery.validate');
	require('jquery.timeago');
    require('jquery.layer');
    require('uParse');

    // 格式化内容
    uParse('.fmt-post-content', {
        rootPath: $CONFIG.base_url+"/assets/js/lib/ueditor/"
    })

    $('.img-captcha').on('click', function(){$("#img-captcha").attr("src",$CONFIG.base_url+"/servlet/captcha?t=" + Math.random());});

    // 渲染评论
    exports.loadComments = function(){
        var list = new List();
        list.options = {
            template : "comment-list-template",
            container : "comment-list",
            url : $CONFIG.base_url + "/api/post/comment/list",
            param : {"pid":$CONFIG.pid, p : 1, s : 5}
        };
        list.load(function(data){
            $.each(data.result, function(index, e){
                exports.findChild(e);
            })
        });

    };

    exports.findChild = function(data){
        $.each(data.childList, function(index, e){

            var html = template("comment-meta", e);
            $("#comment-"+ e.parentId).after(html);
            var padding = parseFloat($("#comment-"+ e.parentId).css("padding-left"));
            padding += 50;
            $("#comment-"+ e.id).css("padding-left",padding+"px");
            if(e.childList.length > 0){
                exports.findChild(e);
            }
        })
    }

    // 加载数据
    exports.loadComments();


    // 验证提交数据
    $("#comment-form").validate({
        errorContainer: $("div.error"),
        errorLabelContainer: $("div.error"),
        wrapper: 'li'
    });

    $("#comment-submit").click(function(){
        var $form = $("#comment-form");
        if(!$form.valid()){
            return;
        }

        var postData = $form.serialize();
        var posting = $.post($CONFIG.base_url + "/api/post/comment/save", postData);
        posting.done(function(data) {
            if(data.result == 'true'){
                layer.msg('保存成功',5,1);
                exports.loadComments();
                $("#comment-form")[0].reset();
                $("html,body").animate({scrollTop:$("#comment-list").offset().top-100},1000);
            }else{
                layer.msg(data.reason,5,1);
            }
        });
    });

    $("#comment-list").on("click",".comment-to",function(){
        var data = $.parseJSON($(this).attr("data"));
        $("#comment-parent").val(data.id);

        var commentParent = '<a class="btn-link pull-right comment-to-del"><span class="glyphicon glyphicon-trash"></span></a>';
        commentParent += $("#comment-"+data.id).prop("outerHTML");

        $("#comment-to-preview").html(commentParent);
        $("#comment-to-preview .comment-meta").css("padding-left","0px");
        $("html,body").animate({scrollTop:$("#comment-form").offset().top-100},1000);
    });

    $("#comment-to-preview").on("click",".comment-to-del",function(){
        $("#comment-to-preview").empty();
        $("#comment-parent").val(null);
    });
});
