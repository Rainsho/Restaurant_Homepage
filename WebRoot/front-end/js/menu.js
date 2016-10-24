function add_food(fid) {
	var quant = $('input[name="n_fid_' + fid + '"]').val();
	$.post('../MenuChartServlet', {
		fid : fid,
		type : 'add',
		quant : quant
	}, function(data) {
		reload_chart(data);
		// $('a[href="javascript:add_food(' + fid + ');"]').text('再来一个');
		$('input[name="n_fid_' + fid + '"]').val(1);
		$('input[name="n_fid_' + fid + '"]').next('span').text(
				'已点' + quant + '份!');
		var img = $('input[name="n_fid_' + fid + '"]').parent().parent()
				.parent().prev().children('img');
		MoveBox(img);
	});
}

function del_food(fid) {
	$.post('../MenuChartServlet', {
		fid : fid,
		type : 'del'
	}, function(data) {
		reload_chart(data);
		// $('a[href="javascript:add_food(' + fid + ');"]').text('来一个');
	});
}

function reload_chart(data) {
	$('#ordered table').children().remove();
	$('#total').children().remove();
	var jsonobj = $.parseJSON(data);
	var total_quant = 0;
	var total_price = 0.0;
	$.each(jsonobj.obj, function(i, x) {
		var fid = x.food.fid;
		var ppath = x.food.picture.ppath;
		var fname = x.food.fname;
		var fprice = x.food.fprice.toFixed(2);
		var quant = x.quant;
		$('#ordered table').append(
				'<tr><td><img src="' + ppath + '"></td><td>' + fname
						+ '</td><td>$' + fprice + ' × ' + quant
						+ '<br/><a href="javascript: del_food(' + fid
						+ ');">删除</a></td></tr>');
		total_quant += quant;
		total_price += fprice * quant;
	});
	if (total_quant != 0) {
		$('#total').append(
				'<div>共' + total_quant + '份菜品 总计$' + total_price.toFixed(2)
						+ '元</div>');
		$('#total')
				.append(
						'<div><a id="btn_menu_chart" href="javascript:void(0);" onclick="order_food()">下单</a></div>');
	}
	$('#shopping-amount').text(total_quant);
}

function order_food() {
	var s = $('#total div').first().text();
	if (s == '') {
		return;
	}
	if (confirm('您' + s + '\n确认下单？')) {
		$.post('../OrderServlet', function() {
			alert('已成功下单，我们将尽快为您备餐！');
			$('#ordered table').children().remove();
			$('#total').children().remove();
			$('#shopping-amount').text(0);
		});
	}
}

// 加点动画
function MoveBox(obj) {
	var conLeft = $('div .wrap-container').offset().left;
	$(obj).css({
		'position' : 'absolute',
		'z-index' : '500'
	});
	$(obj).animate(
			{
				'left' : ($('#menu_chart').offset().left - conLeft
						- $('#menu_chart').width() - 50)
						+ 'px',
				'top' : ($(document).scrollTop() - 200) + 'px',
				'width' : '100px'
			},
			500,
			function() {
				$(obj)
						.animate(
								{
									'left' : ($('#menu_chart').offset().left
											- conLeft + 40)
											+ 'px',
									'top' : '-10px',
									'width' : '50px',
									'opacity' : '0.1'
								}, 500, function() {
									$(obj).removeAttr('style');
								});
			});
}