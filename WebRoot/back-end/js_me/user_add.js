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
	$('#backid').click(function() {
		window.location.href = "back-end/User/user.jsp";
	});
	// 处理pic_lib
	$('#pic_lib').hide();
	$('#show_pic_lib').click(function() {
		$('#pic_lib').show(300);
	});
	$('#pic_lib_cannel').click(function() {
		$('#pic_lib').hide(300);
		$('#back_msg').text('');
	});
	$('#more_pic').click(function() {
		more_pic(page++);
	});
	$('#pic_lib_confirm').click(
			function() {
				if (pid == 0) {
					alert('未选择图片');
					return;
				}
				$('input[name="upic"]').val(
						ppath.substring(ppath.indexOf('front-end') + 10));
				$('#pic_show').prop('src', ppath);
				$('#pic_lib').hide(300);
				$('#back_msg').text('');
			});
	// 页面加载后先加载前面10个
	more_pic(page++);
	// 表单相关事件
	$('select[name="utype"]').change(function() {
		var t = $(this).val();
		if (t == 1) {
			$('.utype1').show();
			$('.utype2').hide();
		} else if (t == 2) {
			$('.utype2').show();
			$('.utype1').hide();
			$('input[type="password"]').val('');
		} else {
			$('.utype1').hide();
			$('.utype2').hide();
			$('input[type="password"]').val('');
		}
	});
	if ($('input[name="origin_utype"]').length > 0) {
		$('select[name="utype"]').val($('input[name="origin_utype"]').val());
	}
	$('select[name="utype"]').change();
	$('input[type="password"]').blur(function() {
		var p1 = $('#psw1').val();
		var p2 = $('#psw2').val();
		if (p1 != '' && p2 != '') {
			if (p1 != p2) {
				$('#psw_msg').text('两次输入的密码不一致');
			} else {
				$('#psw_msg').text('');
			}
		} else {
			$('#psw_msg').text('');
		}
	});
});

// 表单验证2
function checkform() {
	if ($('input[name="uname"]').val().trim() == '') {
		alert('用户名不能为空');
		$('input[name="uname"]').focus().select();
		return false;
	}
	var p1 = $('#psw1').val();
	var p2 = $('#psw2').val();
	if (p1 != '' && p2 != '') {
		if (p1 != p2) {
			alert('两次输入的密码不一致');
			$('#psw2').focus().select();
			return false;
		}
	}
	if ($('select[name="utype"]').val() == 2
			&& $('input[name="upic"]').val() == '') {
		alert('员工需要选择或上传照片');
		return false;
	}
	if ($('select[name="utype"]').val() == 1
			&& ($('#psw1').val() == '' || $('#psw2').val() == '')) {
		alert('管理员必须设置密码');
		$('#psw1').focus().select();
		return false;
	}
	// edit时若disable，讲不会提交
	$('select[name="utype"]').attr('disabled', false);
	return true;
}

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