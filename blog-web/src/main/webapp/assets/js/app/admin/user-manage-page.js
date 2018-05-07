define(function(require, exports, module){
	
	var $ = require('jquery');
    require('bootstrap');
	require('bootstrap.select');
    var List = require('../../util/list.js');
	
	$('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });
    exports.loadList = function(param){
        var list = new List();
        list.options = {
            template : "list-template",
            container : "list-data",
            url : $CONFIG.base_url + "/api/user/list",
            param : param
        };
        list.load();
    };
    exports.loadList();

    $(".btn-search-submit").click(function(){
        var $form = $("#search-form");

        var postData = $form.serialize();
        exports.loadList(postData);
    });

    $("#list-data").on("click",".user-del",function(){
		var data = $.parseJSON($(this).attr("data"));
		
		$.jBox.confirm("要删除该用户？",'系统提示',function(v,h,f){
			if(v=='ok'){
				var posting = $.post($CONFIG.base_url + "/api/user/del", data);
				posting.done(function(data) {
                    if(data == 'true'){
                        $.jBox.tip('删除成功');
                    }else{
                        $.jBox.tip('删除失败，没有权限');
                    }

					window.location.reload();
				});
			}
		},{buttonsFocus:1});
	});
	
});
