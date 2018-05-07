/*!
 * Image crop v0.0.1
 * Author: thon.ju
 * Email: thon.ju@gmail.com
 * Date: 2014-03-22 13:09
 * Description: crop image
 */

define(function (require, exports, module) {

	var $ = require('jquery');
	var template = require('template');
	require('../lib/jquery-file-upload/8.8.5/vendor/jquery.ui.widget.js');
	require('../lib/jquery-file-upload/8.8.5/jquery.iframe-transport.js');
	require('../lib/jquery-file-upload/8.8.5/jquery.fileupload.js');
	require('../lib/jquery-Jcrop/0.9.12/jquery.Jcrop.min.js');

	module.exports = Imgcrop;
	function Imgcrop() {
		this.options = {
			upload: $CONFIG.base_url + "/commons/attachment/upload",
			crop: $CONFIG.base_url + "/commons/attachment/crop",
			resize: false,
			sizes: {w: 200, h: 200},
			aspectRatio: 1
		};
	};

	/*==加载数据=======================================================================*/
	Imgcrop.prototype.init = function init(params, callback) {
		// 初始化参数
		$options = this.options;
		if (typeof params.sizes !== "undefined" && params.sizes.w != null) {
			$options.sizes.w = params.sizes.w;
		}
		if (typeof params.sizes !== "undefined" && params.sizes.h != null) {
			$options.sizes.h = params.sizes.h;
		}
		if (typeof params.aspectRatio !== "undefined") {
			$options.aspectRatio = params.aspectRatio;
		}

		// 定义模板
		var source =
			'<div class="modal " id="img-crop-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">'
			+ '<div class="modal-dialog" style="padding-top:60px; width:700px;">'
			+ '<div class="modal-content" >'
			+ '<div class="modal-header" style="padding:5px 10px;">'
			+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+ '<h5 class="modal-title" id="myModalLabel">图像裁剪</h5>'
			+ '</div>'
			+ '<div class="modal-body" style="padding:10px 0;height:350px;">'
			+ '<div class="col-sm-8">'
			+ '<div id="img-crop-filepreview" style="overflow:hidden; border: 1px dashed; height: 330px; border-radius: 3px">'
			+ '<p class="text-center text-info" style="padding-top:100px;">'
			+ '<strong>'
			+ '从电脑中选择图片<br>'
			+ '<span>支持 JPEG、PNG、GIF，小于2M</span><br>'
			+ '<span>要求像素至少 640x480 </span>'
			+ '</strong>'
			+ '</p>'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-sm-4" style="padding-left: 0;">'
			+ '<div class="preview-container" style="width: 200px; overflow: hidden;">'
			+ '<img class="img-crop-thumbnail"/>'
			+ '</div>'
			+ '</div>'
			+ '</div>'
			+ '<div class="modal-footer" style="padding:5px 10px;">'
			+ '<span class="btn btn-success btn-sm fileinput-button pull-left">'
			+ '<i class="glyphicon glyphicon-plus"></i>'
			+ '   <span>选择图片...</span>'
			+ '   <input id="img-crop-fileupload" type="file" name="files[]">'
			+ '</span>'
			+ '<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>'
			+ '<button type="button" class="btn btn-primary btn-sm img-crop-submit" >确定</button>'
			+ '</div>'
			+ '</div>'
			+ '</div>'
			+ '</div>';

		var render = template.compile(source);
		var html = render();
		$('body').append('<div id="img-crop-container"></div>');
		$('#img-crop-container').empty().append(html);
		$('#img-crop-modal').modal();


		var x = 0, y = 0, w = 0, h = 0;
		var fileId = 0;
		var api, boundx, boundy;
		var $pcnt = $('.preview-container');
		var $pimg = $('.preview-container img');
		var xsize = $pcnt.width();
		var ysize = Math.round(xsize/$options.aspectRatio );
		$pcnt.css({height: ysize + 'px'});

		exports.updatePreview = function updatePreview(c) {
			if (parseInt(c.w) > 0) {
				var rx = xsize / c.w;
				var ry = ysize / c.h;
				$pimg.css({
					width: Math.round(rx * boundx) + 'px',
					height: Math.round(ry * boundy) + 'px',
					marginLeft: '-' + Math.round(rx * c.x) + 'px',
					marginTop: '-' + Math.round(ry * c.y) + 'px'
				});

				x = c.x;
				y = c.y;
				w = c.w;
				h = c.h;
			}
		};

		$('#img-crop-fileupload').fileupload({
			url: $options.upload,
			dataType: 'json',
			formData: {uid: '2'},
			acceptFileTypes: /(\.|\/)(gif|png)$/i,
			maxNumberOfFiles: 1,
			maxFileSize: 5000000,
			add: function (e, data) {
				var goUpload = true;
				var uploadFile = data.files[0];
				if (!(/\.(gif|jpg|jpeg|png)$/i).test(uploadFile.name)) {
					layer.msg('亲~请选择正确的图片格式', 3, 1);
					goUpload = false;
				}
//		        if (uploadFile.size > 1048576) { // 1mb
//		        	$.jBox.tip('亲~图片太大了，最大不超过1mb哦！');
//		            goUpload = false;
//		        }
				if (goUpload == true) {
					data.submit();
				}
			},
			progressall: function (e, data) {
				var progress = parseInt(data.loaded / data.total * 100, 10);
				layer.msg('正在上传... ' + progress + '%', 3, 9);
			},
			done: function (e, data) {
				$.each(data.result, function (index, file) {
					var html = '<img src="' + $CONFIG.base_url + '/commons/attachment/download/' + file.id + '" id="img-crop-pic"/>';
					$('#img-crop-filepreview').empty().append(html);
					$pimg.attr("src", $CONFIG.base_url + '/commons/attachment/download/' + file.id);
					fileId = file.id;
				});

				$("#img-crop-pic").Jcrop({
					onChange: exports.updatePreview,
					onSelect: exports.updatePreview,
					bgColor: "white", //裁剪时背景颜色设为灰色
					bgOpacity: 0.5, //透明度设为0.1
					boxHeight: 350,
					aspectRatio: $options.aspectRatio
				}, function () {
					var bounds = this.getBounds();
					boundx = bounds[0];
					boundy = bounds[1];
					api = this;
					api.animateTo([30, 30, 30 + $options.sizes.w, 30 + $options.sizes.h]);
					api.ui.selection.addClass('jcrop-selection');
				});
			}
		});

		$('.img-crop-submit').click(function () {
			var $data = {id: fileId, x: x, y: y, width: w, height: h, resizeWidth: boundx};
			if (w > 0 && h > 0) {
				var posting = $.post($options.crop, $data);
				posting.done(function (data) {
					if (typeof(callback) == "function") {
						callback($data);
					}
					$('#img-crop-modal').modal('hide');
				});
			}
		});
	};

});
