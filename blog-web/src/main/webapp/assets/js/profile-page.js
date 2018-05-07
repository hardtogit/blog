define(function(require, exports, module){
	
	var $ = require('jquery');
	require('jbox');
	require('jquery/jquery-validation/1.11.0/jquery.validate');

    $.validator.addMethod("isPhone", function(value,element) {
	    var length = value.length;   
	    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
	    var tel = /^\d{3,4}-?\d{7,9}$/;
	    return this.optional(element) || (tel.test(value) || mobile.test(value));   
	 
	}, "请正确填写您的联系电话");
	
	$("#profile-form").validate({
        errorContainer: $("div.error"),
        errorLabelContainer: $("div.error"),
        wrapper: 'li',
		rules : {
            email : {
                email: true,
                remote: {
                    url: $CONFIG.base_url+"/api/user/email/check",
                    type: "get",
                    data: {
                        uid: function() {
                            return $CONFIG.uid;
                        }
                    }
                }
			},
			phone :{
                isPhone : true,
                remote: {
                    url: $CONFIG.base_url+"/api/user/phone/check",
                    type: "get",
                    data: {
                        uid: function() {
                            return $CONFIG.uid;
                        }
                    }
                }
			}
		},
		messages : {
			email : {
				required : '请输入邮箱',
                email: '请输入正确的邮箱格式',
                remote : '邮箱已被使用'
			},
			phone :{
                remote : '手机号已被使用'
			}
		}
	});

    $("#profile-submit").click(function(){
        var $form = $("#profile-form");
        if(!$form.valid()){
            return;
        }

        var postData = $form.serialize();
        var posting = $.post($CONFIG.base_url + "/api/user/profile/update", postData);
        posting.done(function(data) {
            $.jBox.tip("保存成功", "提示");
        });
    });
});