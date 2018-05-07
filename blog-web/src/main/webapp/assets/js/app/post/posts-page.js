define(function(require, exports, module){

	var $ = require('jquery');
	require('jquery.timeago');
    require('uParse');

    // 格式化内容
    uParse('.fmt-post-content', {
        rootPath: $CONFIG.base_url+"/assets/js/lib/ueditor/"
    })

    $(".timeago").timeago();
});
