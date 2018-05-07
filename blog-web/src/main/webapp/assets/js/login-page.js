define(function(require, exports, module){

    var $ = require('jquery');
    require('jquery.validate');

    $('.img-captcha').on('click', function(){$("#img-captcha").attr("src",$CONFIG.base_url+"/servlet/captcha?t=" + Math.random());});
	
	$("#login-form").validate({
        errorContainer: $("div.error"),
        errorLabelContainer: $("div.error"),
        wrapper: 'li',
		rules : {
			username : {
				required : true
			},
            password : {
                required : true
			}
		},
        messages : {
            username : {
                required : '登录名必须填写'
            },
            password : {
                required : '密码不能为空'
            }
        }
	});

    exports.submitForm = function(){
        var $form = $("#login-form");
        if(!$form.valid()){
            return;
        }

        var postData = $form.serialize();
        var posting = $.post($CONFIG.base_url + "/login", postData);
        posting.done(function(data) {
            if(data == 'false'){
                $("div.error").css("display","block");
                $("div.error").empty().append("<li>登录名或密码错误</li>");
            }else{
                window.location.href = $CONFIG.base_url + "/login";
            }
        }).fail(function() {
            $("div.error").css("display","block");
            $("div.error").empty().append("<li>登录失败</li>");
        });
    };

    $("#login-submit").click(function(){
        exports.submitForm();
    });
    $('#password').bind("enterKey",function(e){
        exports.submitForm();
    });
    $('#password').keyup(function(e){
        if(e.keyCode == 13)
        {
            $(this).trigger("enterKey");
        }
    });

});