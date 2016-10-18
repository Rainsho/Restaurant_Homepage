var page = 1;
var pid = 0;
var ppath = '';

// load more
function more_pic(p) {
	$.post('PicMoreServlet', {
		page : p
	}, function(data) {
		var jsobj = $.parseJSON(data);
		if (jsobj.length == 0) {
			$('#more_pic').text('没有更多了...');
			return;
		}
		$.each(jsobj, function(i, x) {
			$('#more_pic').before(
					'<img class="pic_in_lib" src="front-end\\' + x.ppath
							+ '" alt="' + x.pid + '" />');
		});
		if (jsobj.length < 10) {
			$('#more_pic').text('没有更多了...');
		}
		// 处理选择
		$('.pic_in_lib').mouseover(function() {
			$(this).addClass('red_border');
		});
		$('.pic_in_lib').mouseout(function() {
			$(this).removeClass('red_border');
		});
		$('.pic_in_lib').click(function() {
			$('.pic_in_lib').removeClass('pic_select');
			$(this).addClass('pic_select');
			pid = $(this).prop('alt');
			ppath = $(this).prop('src');
		});
	});
}

// on load
$(function() {
	// radio相关事件
	$('#hp_update').hide();
	$('input[name="hid"]').click(
			function() {
				$('#btn_update').val('更新');
				$('#hp_update').show(300);
				var hid = $(this).val();
				var pic = $(this).parent().parent().children().last()
						.children().last().val();
				var title = $(this).parent().next().children().first().text();
				var contents = $(this).parent().parent().children().last()
						.children().first().text();
				var type = $(this).next('span').text();
				if (type == 'slogan') {
					$('#update_left_div').hide();
				} else {
					$('#update_left_div').show();
				}
				$('#hp_pic').val(pic);
				$('#hp_pic_show').attr('src', 'front-end/' + pic);
				$('#hp_hid').val(hid);
				$('#hp_type').val(type);
				$('#hp_title').val(title);
				$('#hp_contents').val(contents);
			});
	// 处理pic_lib
	$('#pic_lib').hide();
	$('#show_pic_lib').click(function() {
		$('#pic_lib').show(300);
	});
	$('#more_pic').click(function() {
		more_pic(page++);
	});
	$('#pic_lib_cannel').click(function() {
		$('#pic_lib').hide(300);
		$('#back_msg').text('');
	});
	$('#pic_lib_confirm').click(function() {
		if (pid == 0) {
			alert('未选择图片');
			return;
		}
		$('#hp_pic').val(ppath.substring(ppath.indexOf('front-end') + 10));
		$('#hp_pic_show').attr('src', ppath);
		$('#pic_lib').hide(300);
		$('#back_msg').text('');
	});
	// 页面加载后先加载前面10个
	more_pic(page++);
	// 更新事件
	$('#btn_update').click(function() {
		update_hp();
	});
});

// ajak upload pic
function uploadpic() {
	var files = $('#file').prop('files');
	if (files.length == 0) {
		alert('未选择将要上传的图片');
		return false;
	}
	var data = new FormData();
	data.append('file', files[0]);
	$.ajax({
		url : 'FoodUpPicServlet',
		type : 'POST',
		data : data,
		cache : false,
		processData : false,
		contentType : false,
		success : function(data) {
			var msg = $.parseJSON(data);
			$('#back_msg').text(msg.msg);
			$('#pic_display').prepend(
					'<img class="pic_in_lib" src="front-end\\' + msg.obj.ppath
							+ '" alt="' + msg.obj.pid + '" />');
			// 重新绑定事件
			$('.pic_in_lib').mouseover(function() {
				$(this).addClass('red_border');
			});
			$('.pic_in_lib').mouseout(function() {
				$(this).removeClass('red_border');
			});
			$('.pic_in_lib').click(function() {
				$('.pic_in_lib').removeClass('pic_select');
				$(this).addClass('pic_select');
				pid = $(this).prop('alt');
				ppath = $(this).prop('src');
			});
			// 点选新上传的图片
			$('#pic_display img:first-child').click();
			$('#file').val(null);
		}
	});
	return false;
}

// ajak update homepage
function update_hp() {
	var hid = $('#hp_hid').val();
	var pic = $('#hp_pic').val();
	var title = $('#hp_title').val();
	var contents = $('#hp_contents').val();
	var type = $('#hp_type').val();

	var data = {
		hid : hid,
		pic : pic,
		title : title,
		contents : contents,
		type : type
	};

	$.post('HomepageUpdateServlet', data, function() {
		var td = $('input[name="hid"][value=' + hid + ']').parent();
		td.next().children().first().text(title);
		td.next().next().children().first().text(contents);
		td.next().next().children().first().next().val(pic);
		$('#btn_update').val('更新成功');
	});

}
