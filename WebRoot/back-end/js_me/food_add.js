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
		window.location.href = "back-end/Food/food.jsp";
	});
	// 处理foodtype
	$('#span_add_type').hide();
	$('#show_add_type').click(function() {
		$('#span_add_type').toggle(400);
		if ($('#show_add_type').text() == '添加类别>>') {
			$('#show_add_type').text('收起<<');
		} else {
			$('#show_add_type').text('添加类别>>');
		}
	});
	$('#add_type').click(
			function() {
				ftname = $('#txt_add_type').val().trim();
				if (ftname == '') {
					alert('不能添加空类别');
					return;
				}
				if (confirm("确定新增" + ftname + "类吗？")) {
					var url = "TypeAddServlet?ftname=" + ftname;
					// AJAX
					$.get(url, function(ftid) {
						$('select[name="ftid"]').append(
								'<option value="' + ftid + '">' + ftname
										+ '</option>');
						$('select[name="ftid"]').val(ftid);
						$('#txt_add_type').val('');
					});
				}
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
	$('#pic_lib_confirm').click(function() {
		if (pid == 0) {
			alert('未选择图片');
			return;
		}
		$('input[name="pid"]').val(pid);
		$('#pic_show').prop('src', ppath);
		$('#pic_lib').hide(300);
		$('#back_msg').text('');
	});
	// 页面加载后先加载前面10个
	more_pic(page++);
	// 表单验证1
	$('input[name="fprice"]').blur(function() {
		fprice = $(this).val().trim().match(/^\d+\.?\d{0,2}/);
		if (fprice == null) {
			$(this).focus().select();
		} else {
			$(this).val(fprice);
		}

	});
});

// 表单验证2
function checkform() {
	if ($('input[name="fname"]').val().trim() == '') {
		alert('菜品名称不能为空');
		$('input[name="fname"]').focus().select();
		return false;
	}
	if ($('input[name="pid"]').val().trim() == '') {
		alert('未设置图片');
		return false;
	}
	if ($('input[name="fprice"]').val().trim() == '') {
		alert('菜品单价不能为空');
		$('input[name="fprice"]').focus().select();
		return false;
	}
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