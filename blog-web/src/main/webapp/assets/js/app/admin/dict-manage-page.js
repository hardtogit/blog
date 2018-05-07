define(function(require, exports, module){
	
	var $ = require('jquery');
    require('bootstrap');
    require('bootstrap.select');
    var List = require('../../util/list.js');
	
	exports.loadList = function(param){
		var list = new List();
		list.options = {
			template : "list-template",
			container : "list-data",
			url : $CONFIG.base_url + "/api/dict/list",
			param : param
		};
		list.load();
	};
	exports.loadList();
	
	$('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });
	
	$(".btn-search-submit").click(function(){
		var $form = $("#search-form");
		
		var postData = $form.serialize();
		exports.loadList(postData);
	});

    $("#list-data").on("click",".dict-del",function(){
		var data = $.parseJSON($(this).attr("data"));
		
		$.jBox.confirm("要删除该类目及所有子类目吗？",'系统提示',function(v,h,f){
			if(v=='ok'){
				var posting = $.post($CONFIG.base_url + "/api/dict/del", data);
				posting.done(function(data) {
					$.jBox.tip('删除成功');
					window.location.reload();
				});
			}
		},{buttonsFocus:1});
	});
	
	$("#dict-sort-submit").click(function(){
		var $form = $("#dict-form");
		if(!$form.valid()){
			return;
		}
		
		var postData = $form.serialize();
		var posting = $.post($CONFIG.base_url + "/api/dict/sort", postData);
		posting.done(function(data) {
			$.jBox.tip('保存成功');
			window.location.reload();
		});
	});
});
