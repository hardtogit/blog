define(function(require, exports, module){
	
	var $ = require('jquery');
	require('jbox');
	require('jquery/jquery-validation/1.11.0/jquery.validate');
	
	jQuery.validator.addMethod("isPhone", function(value,element) {   
	    var length = value.length;   
	    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;   
	     var tel = /^\d{3,4}-?\d{7,9}$/;   
	    return this.optional(element) || (tel.test(value) && mobile.test(value));   
	 
	}, "请正确填写您的联系电话");
	
	$("#name").focus();
	
	$("#profile-form").validate({
		rules : {
			name : {
				required : true
			},
			firstName : {
				required : true
			},
			lastName : {
					required : true
			},
			phone :{
					required : true,
					isPhone : true
			}
		},
		messages : {
			name : {
				required : "请输入昵称名称"
			},
			firstName : {
				required : '请输入名字'
			},
			lastName : {
				required : '请输入姓氏'
			},
			phone :{
				required : '请输入电话号码'
			}
		},
		submitHandler:function(form)
		{
			exports.options = {
					addLink : $CONFIG.base_url + "/api/profile/update"
				};
			
			var $form1 = $("#profile-form");
			var posting = $.post(exports.options.addLink, $form1.serialize());
			
			/* Put the results in a div */
			posting.done(function(data) {
				$.jBox.success('保存成功!', '提示');	
			});
		}
	});
});