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

	$("#office-submit").click(function(){
		var $form = $("#office-form");
		if(!$form.valid()){
			return;
		}
		
		var postData = $form.serialize();
		var posting = $.post($CONFIG.base_url + "/api/office/save", postData);
		posting.done(function(data) {
            layer.msg('保存成功',5,1);
			window.location.href = $CONFIG.base_url + "/admin/office/manage";
		});
	});
	
});
