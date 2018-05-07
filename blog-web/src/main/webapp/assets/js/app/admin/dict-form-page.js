define(function(require, exports, module){

    var $ = require('jquery');
    var List = require('../../util/list.js');
    require('jquery.validate');
    require('jquery.icheck');
    require('bootstrap');
    require('bootstrap.select');
    require('jquery.layer');
	
	$('input').iCheck({
	    checkboxClass: 'icheckbox_flat-blue',
	    radioClass: 'iradio_flat-blue',
	    increaseArea: '20%' // optional
	});
	
	$('.dict-select').on('click', function () {
	   var selected = $("#parent-id").val();
       var dict = new Dict();
       dict.load('', selected, function(ids, names, data){
			$("#parent-id").val(ids);
			$("#parent-name").val(names);
			$("#type").val(data.type);
       });
       
	});
	
	$("#dict-form").validate({
		errorContainer: $("div.error"),
		errorLabelContainer: $("div.error"),
		wrapper: 'li'
	});
	
	$("#dict-submit").click(function(){
		var $form = $("#dict-form");
		if(!$form.valid()){
			return;
		}
		
		var postData = $form.serialize();
		var posting = $.post($CONFIG.base_url + "/api/dict/save", postData);
		posting.done(function(data) {
            layer.msg('保存成功',5,1);
			window.location.href = $CONFIG.base_url + "/admin/dict/manage";
		});
	});
	
	$("#goon-dict-submit").click(function(){
		var $form = $("#dict-form");
		if(!$form.valid()){
			return;
		}
		
		var postData = $form.serialize();
		var posting = $.post($CONFIG.base_url + "/api/dict/save", postData);
		posting.done(function(data) {
            layer.msg('保存成功',5,1);
		});
	});
	
});
