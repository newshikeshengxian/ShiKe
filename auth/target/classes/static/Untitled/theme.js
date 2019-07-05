$(function(){
	$("#find_goods").mouseover(function(){
		$("#goods_list").show();
		$("#find_goods").addClass('current');
	});
	$("#goods_list").mouseover(function(){
		$("#goods_list").show();
		$("#find_goods").addClass('current');
	});
	$("#goods_list,#find_goods").mouseout(function(){
		$("#goods_list").hide();
		$("#find_goods").removeClass('current');
	});
});








