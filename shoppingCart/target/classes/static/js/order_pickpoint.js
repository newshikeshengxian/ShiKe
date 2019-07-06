function show(o,sid){ 
	var o = document.getElementById(o); 
	console.log(o);
	var pid = document.getElementById('point'+sid).value;
	 Ajax.call('flow.php?step=select_pickinfo', 'sid=' + sid + '&pid='+pid, pickinfo, 'POST', 'JSON');
	o.style.display = "";
	$('#bg').show();
}
function hide(o){ 
	var o = document.getElementById(o); 
	o.style.display = "none"; 
	$('#bg').hide();
}
function pickinfo(result){
	document.getElementById('pickcontent').innerHTML = result.content;
}
function showztd(did){
	var num = $("table[class='ztd']").length;
	if(num>0){
		$("table[class='ztd']").each(function(){
			$(this).hide();
		})
	}
	$('#txt'+did).show();
}
function select_point(obj,value){
	$("span[class*='site-in-short']").removeClass("site-in-short");
	$(obj).addClass("site-in-short");
	$('#s_pid').val(value);
}
function save_point(suppid){
	var value = $('#s_pid').val();
	var is_default_point = $('input:radio[name="is_default_point"]:checked').val();
	Ajax.call('flow.php?step=save_point', 'sid=' + suppid + '&pid='+value + '&is_default_point='+is_default_point, selectpickinfo, 'GET', 'JSON');
	hide('pop');
}
function selectpickinfo(result){
	if(document.getElementById('picktxt'+result.suppid)){
		document.getElementById('picktxt'+result.suppid).innerHTML = result.picktxt;
	}
}