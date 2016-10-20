function add_food(fid) {
	$.post('../MenuChartServlet', {
		fid : fid,
		type : 'add'
	}, function(data) {
		reload_chart(data);
		$('a[href="javascript:add_food(' + fid + ');"]').text('再来一个');
	});
}

function del_food(fid) {
	$.post('../MenuChartServlet', {
		fid : fid,
		type : 'del'
	}, function(data) {
		reload_chart(data);
		$('a[href="javascript:add_food(' + fid + ');"]').text('来一个');
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