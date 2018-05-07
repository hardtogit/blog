define(function(require){

    var $ = require('jquery');
    require('jquery.validate');
    require('jquery.layer');

    $("#regist-form").validate({
        errorContainer: $("div.error"),
        errorLabelContainer: $("div.error"),
        wrapper: 'li',
        rules : {
            loginName : {
                remote : $CONFIG.base_url+"/api/user/account/check"
            },
            email : {
                remote : $CONFIG.base_url+"/api/user/email/check",
                email: true
            },
            password : {
                required: true,
                minlength: 6
            }
        },
        messages : {
            loginName : {
                remote : '登录名已被使用'
            },
            email : {
                remote : '邮箱已被使用'
            }
        }
    });

    $("#regist-submit").click(function(){
        var $form = $("#regist-form");
        if(!$form.valid()){
            return;
        }

        var postData = $form.serialize();
        var posting = $.post($CONFIG.base_url + "/api/user/save", postData);
        posting.done(function(data) {
            layer.msg('注册成功，3秒后将跳转至登录界面',3,1);
            window.setTimeout(function () { window.location.href = $CONFIG.base_url + "/login"; }, 3000);
        });
    });
});