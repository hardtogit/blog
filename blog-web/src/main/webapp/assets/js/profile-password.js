define(function(require, exports, module){

	var $ = require('jquery');
	require('jbox');
	require('jquery/jquery-validation/1.11.0/jquery.validate');

	
	//聚焦第一个输入框
	//为inputForm注册validate函数
	$("#password-form").validate({
        errorContainer: $("div.error"),
        errorLabelContainer: $("div.error"),
        wrapper: 'li',
		rules : {
			pwd : {
                required: true,
				remote :{
					type: "GET",
                    async: false,
                    url: $CONFIG.base_url+"/api/user/pwd/check",
                    dataType: "json",
                    data: {
                        uid:function(){
                            return $CONFIG.uid;
                        }
                    }
				}
			},
            plainPassword : {
                required: true,
                minlength: 6
            },
            confirmPassword: {
                required: true,
                equalTo: "#plainPassword"
            }
		},
		messages : {
			pwd : {
				remote : '原密码不正确'
			},
            plainPassword : {
                required: '请输入新密码',
                minlength: '密码长度至少6个字符'
            },
            confirmPassword: {
                required: "请再次输入新密码",
                equalTo: "两次输入密码不一致"
            }
		}
	});

    $("#password-submit").click(function(){
        var $form = $("#password-form");
        if(!$form.valid()){
            return;
        }

        var postData = $form.serialize();
        var posting = $.post($CONFIG.base_url + "/api/user/pwd/update", postData);
        posting.done(function(data) {
            $.jBox.tip("修改成功，3秒后将跳转至登录界面，请重新登录", 'loading');
            window.setTimeout(function () { window.location.href = $CONFIG.base_url + "/logout"; }, 3000);
        });
    });
});