define(function(require, exports, module){

    var $ = require('jquery');
    var List = require('../../util/list.js');
    require('jquery.validate');
    require('jquery.icheck');
    require('bootstrap');
    require('bootstrap.select');
    require('jquery.layer');

    // 初始化
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });

    $.validator.addMethod("isPhone", function(value,element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        var tel = /^\d{3,4}-?\d{7,9}$/;
        return this.optional(element) || (tel.test(value) || mobile.test(value));

    }, "请正确填写您的联系电话");
	
	$("#user-form").validate({
		errorContainer: $("div.error"),
		errorLabelContainer: $("div.error"),
		wrapper: 'li',
        rules : {
            loginName : {
                remote: {
                    url: $CONFIG.base_url+"/api/user/account/check",
                    type: "get",
                    data: {
                        uid: function() {
                            return $("#id").val() == undefined?0:$("#id").val();
                        }
                    }
                }
            },
            email : {
                email: true,
                remote: {
                    url: $CONFIG.base_url+"/api/user/email/check",
                    type: "get",
                    data: {
                        uid: function() {
                            return $("#id").val() == undefined?0:$("#id").val();
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
                            return $("#id").val() == undefined?0:$("#id").val();
                        }
                    }
                }
            }
        },
        messages : {
            loginName :{
                remote : '登录名已被使用'
            },
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
	
	$("#user-submit").click(function(){
		var $form = $("#user-form");
		if(!$form.valid()){
			return;
		}
		
		var postData = $form.serialize();
		var posting = $.post($CONFIG.base_url + "/api/user/save", postData);
		posting.done(function(data) {
            layer.msg('保存成功',5,1);
			window.location.href = $CONFIG.base_url + "/admin/user/manage";
		});
	});
	
});
