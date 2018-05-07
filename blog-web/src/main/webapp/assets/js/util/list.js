define(function(require, exports, module){
	
	var $ = require('jquery');
	var template = require('template');
    require('jquery.layer');
	require('jquery.timeago');
	
	$.ajaxSetup({cache:false});
	
	module.exports = List;
	function List(){
		this.options = {};
	};
	
	/*==加载数据=======================================================================*/
	List.prototype.load = function load(callback) {
        var $this = this;
        var $options = this.options;

        if($options.showLoading == undefined){
            layer.load('正在努力加载中...', 0);
        }
		
		var getting = $.get($options.url, $options.param);
		getting.done(function(data) {
			// 显示数据
			var html = template($options.template, data);
			$("#"+$options.container).empty().append(html);
			
			// time format and img lazy load
			$(".timeago").timeago();

            layer.closeAll();
			
			// page
			$("#"+$options.container + ' .pager li a').click(function(){
				$this.page(this, callback);
			});

            // format number
            $(".data").each(function(){
                if(parseFloat($(this).html()) == 0){
                    $(this).addClass("text-muted");
                }else if(parseFloat($(this).html()) > 0 || parseFloat($(this).html()) < 0){
                    var num = parseFloat($(this).text());
                    $(this).html(Math.round(num*100)/100);
                }
            })
			
			if(typeof(callback) == "function"){
				callback(data);
			}

            return data;
		});
	};
	
	List.prototype.render = function load(data,callback) {
        layer.load('正在努力加载中...', 0);
		
		var $this = this;
		var $options = this.options;

        if($options.showLoading == undefined){
            layer.load('正在努力加载中...', 0);
        }
		
		// 显示数据
		var html = template($options.template, data);
		$("#"+$options.container).empty().append(html);
		
		// time format and img lazy load
		$(".timeago").timeago();

        layer.closeAll();

        // format number
        $(".data").each(function(){
            if(parseFloat($(this).html()) == 0){
                $(this).addClass("text-muted");
            }else if(parseFloat($(this).html()) > 0 || parseFloat($(this).html()) < 0){
                var num = parseFloat($(this).text());
                $(this).html(Math.round(num*100)/100);
            }
        })
		
		if(typeof(callback) == "function"){
			callback(data);
		}
	};
	
	List.prototype.page = function page(obj, callback) {
		var $this =this;
		var data = $.parseJSON($(obj).attr("data"));
		
		if(typeof($this.options.param) == "object"){
			$this.options.param.p=data.p;
		}else if(typeof($this.options.param) == "string"){
			var srt=$this.options.param;
			srt=srt.replace(/\b(p|P)=([0-9]+|\D|\w+)\b/g, function(word){return "";});

			$this.options.param=srt+"&p="+data.p;
		}else{
			$this.options.param="p="+data.p;
		};

		$this.load(callback);
	};
	
});
