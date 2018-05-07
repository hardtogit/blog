define(function(require, exports, module){

    var $ = require('jquery');
    require('jquery.validate');
    require('jquery.layer');
    require('jquery.icheck');
    require('ueditor');

    var URL = window.UEDITOR_HOME_URL = $CONFIG.base_url+"/assets/js/lib/ueditor/";
    window.UEDITOR_CONFIG = {

        //为编辑器实例添加一个路径，这个不能被注释
        UEDITOR_HOME_URL: URL
        , serverUrl: $CONFIG.base_url + "/commons/attachment/ueditor?uid=1"
        , toolbars: [
            ['fullscreen', 'source', '|',
                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'blockquote', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'cleardoc', '|',
                'fontsize', '|', 'indent', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
                'link', 'unlink', 'anchor'],
            ['simpleupload', 'insertimage', 'map', '|', 'insertcode', 'pagebreak', '|',
                'horizontal', 'date', 'time', 'spechars', '|',
                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
                'preview', 'drafts']
        ]
        ,zIndex : 999
        ,elementPathEnabled : false
        ,maximumWords:8000
        ,wordCountMsg:'已输入 <span style="color:red; font-weight: 700;font-size: 13px; ">{#count}</span> 个字符，剩余 {#leave}'
    };
    var ue = UE.getEditor('editor');

    // 初始化
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });

    exports.updateTags = function(){
        var tags = "";
        $(".tag-name").each(function(i,v){
            if(tags.length == 0){
                tags = v.innerText;
            }else{
                tags = tags+','+v.innerText;
            }
        });
        $("#post-tags").val(tags);
    }
    $("#post-tag-add").click(function(){
        if($("#post-tag").length > 0){
            $.each($("#post-tag").val().split(","),function(index,e){
                if(e.length > 0){

                    var count = 0;
                    $(".tag-name").each(function(i,v){
                        if(e == v.innerText){
                            count = count + 1;
                        }
                    });
                    if(count == 0){
                        var tag = '<span><a class="btn-link tag-del"><span class="glyphicon glyphicon-remove-sign"></span></a><span class="tag-name">'+e+'</span></span>';
                        $("#post-tags-check").append(tag);
                    }
                }
            });
            exports.updateTags();
            $("#post-tag").val(null);
        }
    });
    $("#post-tags-check").on("click",".tag-del", function(){
        $(this).parent().remove();
        exports.updateTags();
    });


    // 验证提交数据
	$("#post-form").validate({
		errorContainer: $("div.error"),
		errorLabelContainer: $("div.error"),
		wrapper: 'li'
	});
	
	$("#post-submit").click(function(){
		var $form = $("#post-form");
		if(!$form.valid()){
			return;
		}

        var edContent = ue.getContent();;
        if(edContent.length == 0){
            layer.msg('写点什么吧',3,1);
            return;
        }
        $("#postContent").val(edContent);
		
		var postData = $form.serialize();
		var posting = $.post($CONFIG.base_url + "/api/post/save", postData);
		posting.done(function(data) {
            layer.msg('保存成功',3,1);
			window.location.href = $CONFIG.base_url + "/admin/post/manage";
		});
	});
	
});
