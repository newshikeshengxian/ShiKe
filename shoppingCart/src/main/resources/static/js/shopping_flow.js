/* $Id : shopping_flow.auth 4865 2007-01-31 14:04:10Z paulgao $ */

var selectedShipping = null;
var selectedPayment = null;
var selectedPack = null;
var selectedCard = null;
var selectedSurplus = '';
var selectedBonus = 0;
var selectedIntegral = 0;
var selectedOOS = null;
var alertedSurplus = false;

var groupBuyShipping = null;
var groupBuyPayment = null;

var selected_pay_code = null;

// 页面加载时加载图片方法
function showLoading(){
    var loadingImg = '<div style="width:280px;height:210px;position:fixed;top:380px; left:45%;"><img src="/images/loading_circle.png" style="display:block;width:280px;height:210px;z-index: 999999;-webkit-animation-name:flash-rotate;-webkit-animation-duration:1s;  -webkit-animation-timing-function:linear;  -webkit-animation-iteration-count:infinite;"/></div>' ;
    $('.pop-mask').show();
    $(document.body).append(loadingImg);
}

function selectShipping(recid, suppid) {
    $('#desc_'+suppid).html($('#ship_'+suppid+'_'+recid).attr('title'));//显示配送描述的地方
    var oldselcart_checkout = $("#oldselcart_checkout").val();
    var sessionCode = $("#sessionCode").val();
    url = 'flow.php?step=select_shipping';
    Ajax.call(url, 'recid=' + recid + '&suppid=' + suppid + '&oldselcart_checkout='+oldselcart_checkout+"&sessionCode="+sessionCode, orderShipping, 'GET', 'JSON');

}
function orderShipping(result) {
    if (result.error) {
        if(result.error == 'cart_have_change'){
            $('.pop-mask,.pop-compare').show();
            $('.pop-compare .pop-text').html("您的购物车已变更，请重新处理").css('padding-top',10);
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            setTimeout('window.location.href="flow.php";',3000);
            return false;
        }
        $('.pop-mask,.pop-compare').show();
        $('.pop-compare .pop-text').html(result.error).css('padding-top',10);
        $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
    } else {
        if (document.getElementById('picktxt' + result.suppid)) {
            document.getElementById('picktxt' + result.suppid).innerHTML = result.picktxt;
        }
        orderSelectedResponse(result.content);
        document.getElementById("final_total_money").innerHTML=result.final_total_money;
        supplierOrderSelectedResponse(result.supplier_content,result.suppid)
        //联运触发余额如果余额是选中状态
        if($('#issurplus').attr('checked') != 'undefined' && $('#issurplus').attr('checked')){
            $('#issurplus').attr('checked',false).trigger("click").attr('checked',false);
        }
       if(result.msg){
            $('#click_show_'+result.suppid).trigger("click");
            document.getElementById('pickup_point_msg').innerHTML = result.msg;
            $("#pickup_point_msg").show();
        }else{
        	$("#pickup_point_msg").hide();
        }
    }
}

/*******************************************************************************
 * 改变配送方式
 */
function selectShipping_old(obj) {

    var theForm = obj.form;
    var con_country = theForm.elements['have_consignee'].value;
    if (con_country == '0') {
        alert('请先选择配送地址！');
        obj.checked = false;
        return;
    }

    if (selectedShipping == obj) {
        return;
    } else {
        selectedShipping = obj;
    }

    var supportCod = obj.attributes['supportCod'].value * 1;
    var supportPickup = 0;
	/* 代码修改_start by grand */
    if (obj.attributes['supoortPickup']) {
        supportPickup = obj.attributes['supoortPickup'].value * 1;
        if (supportPickup == 0) {
            document.getElementById('pickup_point_box').style.display = 'none';
        } else {
            document.getElementById('pickup_point_box').style.display = '';
        }
    }
	/* 代码修改_end by grand */
    var theForm = obj.form;

    for (var i = 0; i < theForm.elements.length; i++) {
        if (theForm.elements[i].name == 'payment' && theForm.elements[i].attributes['isCod'].value == '1') {
            if (supportCod == 0) {
                theForm.elements[i].checked = false;
                theForm.elements[i].disabled = true;
            } else {
                theForm.elements[i].disabled = false;
            }
        }

    }

    if (obj.attributes['insure'].value + 0 == 0) {
        document.getElementById('ECS_NEEDINSURE').checked = false;
        document.getElementById('ECS_NEEDINSURE').disabled = true;
    } else {
        document.getElementById('ECS_NEEDINSURE').checked = false;
        document.getElementById('ECS_NEEDINSURE').disabled = false;
    }

    var now = new Date();
    if(location.href.match(/flow_type=(\d+)/)[1]){
        url = 'flow.php?step=select_shipping&flow_type='+location.href.match(/flow_type=(\d+)/)[1];
    }else{
        url = 'flow.php?step=select_shipping';
    }
    Ajax.call(url, 'shipping=' + obj.value + '&pickup=' + supportPickup, orderShippingSelectedResponse, 'GET', 'JSON');
}

/**
 *
 */
function orderShippingSelectedResponse(result) {
    if (result.need_insure) {
        try {
            document.getElementById('ECS_NEEDINSURE').checked = true;
        } catch (ex) {
            alert(ex.message);
        }
    }

    try {
        if (document.getElementById('ECS_CODFEE') != undefined) {
            document.getElementById('ECS_CODFEE').innerHTML = result.cod_fee;
        }
    }
    catch (ex) {
        alert(ex.message);
    }


    if (document.getElementById('supplier_shipping') != undefined) {
        document.getElementById('supplier_shipping').innerHTML = result.supplier_shipping;
    }
    if (document.getElementById('pickup_point_box')) {
        document.getElementById('pickup_point_box').innerHTML = result.pickup_content;
    }


    orderSelectedResponse(result);
}

/**
 * 虚拟团购改变支付方式
 *
 * @param {type}
 *            obj
 * @returns {undefined}
 */
function selectVirtualGroupPayment(obj) {

    var theForm = obj.form;
    var mobile_phone = theForm.elements['mobile_phone'].value;
    if (mobile_phone == '') {
        alert('请先选择邦定手机！');
        obj.checked = false;
        return;
    }

    var zc = isZc();

    if (selectedPayment == obj) {
        return;
    } else {
        selectedPayment = obj;
    }
    var oldselcart_checkout = $("#oldselcart_checkout").val();
    Ajax.call('flow.php?step=select_payment', 'payment=' + obj.value + '&oldselcart_checkout=' +oldselcart_checkout + zc, orderSelectedResponse, 'GET', 'JSON');
}
/*******************************************************************************
 * 改变支付方式
 */
function selectPayment(obj) {

    var theForm = obj.form;
    var con_country = theForm.elements['have_consignee'].value;
    if (con_country == '0') {
        alert('请先选择配送地址！');
        obj.checked = false;
        return;
    }
    if (selectedPayment == obj) {
        return;
    } else {
        selectedPayment = obj;
    }

    var zc = isZc();

    //获取选中的商品ID数组
    var sel_goods = new Array();
    var j=0;//数组下标
    $("#goodsBox tr[name='goodsList']").each(function(i){
        var __selfobj = $(this);
        var __self_gift_cart_id = __selfobj.attr('gift_cart_id');
        if(__self_gift_cart_id==0 &&  __selfobj.attr('class')!='order_gift'){
            sel_goods[j] = __selfobj.attr('rec_id');
            j++;
        }
    });

    var oldselcart_checkout = $("#oldselcart_checkout").val();
    var sessionCode = $("#sessionCode").val();
    Ajax.call('flow.php?step=select_payment', 'payment='+obj.value + '&sessionCode='+sessionCode+'&oldselcart_checkout=' +oldselcart_checkout+'&sel_goods='+sel_goods + zc, orderSelectedResponse, 'GET', 'JSON');
}
/**
 * 团购购物流程 --> 改变配送方式
 */
function handleGroupBuyShipping(obj) {
    if (groupBuyShipping == obj) {
        return;
    } else {
        groupBuyShipping = obj;
    }

    var supportCod = obj.attributes['supportCod'].value + 0;
    var theForm = obj.form;

    for (var i = 0; i < theForm.elements.length; i++) {
        if (theForm.elements[i].name == 'payment' && theForm.elements[i].attributes['isCod'].value == '1') {
            if (supportCod == 0) {
                theForm.elements[i].checked = false;
                theForm.elements[i].disabled = true;
            } else {
                theForm.elements[i].disabled = false;
            }
        }
    }

    if (obj.attributes['insure'].value + 0 == 0) {
        document.getElementById('ECS_NEEDINSURE').checked = false;
        document.getElementById('ECS_NEEDINSURE').disabled = true;
    } else {
        document.getElementById('ECS_NEEDINSURE').checked = false;
        document.getElementById('ECS_NEEDINSURE').disabled = false;
    }

    Ajax.call('group_buy.php?act=select_shipping', 'shipping=' + obj.value, orderSelectedResponse, 'GET');
}

/**
 * 团购购物流程 --> 改变支付方式
 */
function handleGroupBuyPayment(obj) {
    if (groupBuyPayment == obj) {
        return;
    } else {
        groupBuyPayment = obj;
    }

    Ajax.call('group_buy.php?act=select_payment', 'payment=' + obj.value, orderSelectedResponse, 'GET');
}

/**
 * 改变商品包装
 */
function selectPack(obj) {

    var theForm = obj.form;
    var con_country = theForm.elements['have_consignee'].value;
    if (con_country == '0') {
        alert('请先选择配送地址！');
        obj.options[0].selected = "selected";
        return;
    }
    var aaa = obj.options[obj.selectedIndex].value;
    document.getElementById('packimage').href = packimage[aaa];

    Ajax.call('flow.php?step=select_pack', 'pack=' + aaa, orderSelectedResponse, 'GET', 'JSON');

}

/**
 * 改变祝福贺卡
 */
function selectCard(obj) {


    var aaa = obj.options[obj.selectedIndex].value;
    document.getElementById('cardimage').href = cardimage[aaa];
    document.getElementById('card_message').disabled = (aaa > 0) ? false : true;
    Ajax.call('flow.php?step=select_card', 'card=' + aaa, orderSelectedResponse, 'GET', 'JSON');

}

/**
 * 选定了配送保价
 */
function selectInsure(needInsure) {
    needInsure = needInsure ? 1 : 0;

    Ajax.call('flow.php?step=select_insure', 'insure=' + needInsure, orderSelectedResponse, 'GET', 'JSON');
}

/**
 * 团购购物流程 --> 选定了配送保价
 */
function handleGroupBuyInsure(needInsure) {
    needInsure = needInsure ? 1 : 0;

    Ajax.call('group_buy.php?act=select_insure', 'insure=' + needInsure, orderSelectedResponse, 'GET', 'JSON');
}

/**
 * 回调函数
 */
function orderSelectedResponse(result) {
    if (result.error) {
        if(result.error == 'cart_have_change'){
            $('.pop-mask,.pop-compare').show();
            $('.pop-compare .pop-text').html("您的购物车已变更，请重新处理").css('padding-top',10);
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            setTimeout('window.location.href="flow.php";',3000);
            return false;
        }
        alert(result.error);
        location.href = './';
    }

    try {

        selected_pay_code = result.pay_code;

        var layer = document.getElementById("ECS_ORDERTOTAL");


        //layer.innerHTML = (typeof result == "object") ? result.content : result;
        //orderSelectedResponse(result.content);
        //document.getElementById("final_total_money").innerHTML=result.final_total_money;

        if (result.payment != undefined) {
            var surplusObj = document.forms['theForm'].elements['surplus'];
            if (surplusObj != undefined) {
                surplusObj.disabled = result.pay_code == 'balance';
            }

        }
        // orderSelectedResponse(result.content);
        // document.getElementById("final_total_money").innerHTML=result.final_total_money;
        // supplierOrderSelectedResponse(result.supplier_content,result.suppid);

    } catch (ex) {
    }
}
/**
 * 供应商回调函数
 */
function supplierOrderSelectedResponse(result,supplier_id) {
    if (result.error) {
        alert(result.error);
        location.href = './';
    }
    try {
        selected_pay_code = result.pay_code;
        var layer = document.getElementById("ECS_ORDERgoodsinfo_"+supplier_id);
        layer.innerHTML = result;
    } catch (ex) {
    }
}

/**
 * 改变余额
 */
function changeSurplus(val) {
    var con_country = document.forms['theForm'].elements['have_consignee'].value;
    if (con_country == '0') {
        alert('请先选择配送地址！');
        obj.checked = false;
        return;
    }
    var zc = isZc();

    if (selectedSurplus == val) {
        // return;
    } else {
        // selectedSurplus = val;
    }
    var rec_id_list = $("#rec_id_list").val();
    var oldselcart_checkout = $("#oldselcart_checkout").val();
    var sessionCode = $("#sessionCode").val();
    Ajax.call('flow.php?step=change_surplus', 'surplus=' + val+ '&oldselcart_checkout=' +oldselcart_checkout+'&sessionCode='+sessionCode+'&sel_goods='+rec_id_list+zc, changeSurplusResponse, 'GET', 'JSON');
}

/*******************************************************************************
 * 改变余额-虚拟团购
 */
function changeSurplusVir(val) {

    var mobile_phone = document.forms['theForm'].elements['mobile_phone'].value;
    if (mobile_phone == '') {
        alert('请先绑定手机！');
        obj.checked = false;
        return;
    }


    if (selectedSurplus == val) {
        // return;
    } else {
        // selectedSurplus = val;
    }

    Ajax.call('flow.php?step=change_surplus', 'surplus=' + val, changeSurplusResponse, 'GET', 'JSON');
}

/**
 * 改变余额回调函数
 */
function changeSurplusResponse(obj) {
    if (obj.error) {
        if(obj.error == 'goods_price_change'){
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html('您的商品活动状态有变动，请重新处理');
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            setTimeout('window.location.href="flow.php";',3000);
            return false;

        }else if(obj.error == 'cart_have_change'){
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html('您的购物车已变更，请重新处理');
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            setTimeout('window.location.href="flow.php";',3000);
            return false;

        }else{
            try {
                alert(obj.error);
                setTimeout('window.location.href="flow.php";',1000);
                return false;
                document.getElementById('ECS_SURPLUS').value = '0';
                document.getElementById('ECS_SURPLUS').focus();
            } catch (ex) {
            }
        }

    } else {
        try {
            document.getElementById('ECS_SURPLUS').value = obj.surplus;
            if(obj.show){
                //如果余额完全支付订单金额
                $('#pay_div').hide();
                $('#payment_other_input').attr("checked", true).val(pay_balance_id);//默认选择余额支付方式
            }else{
                $('#pay_div').show();
                $("#pay_div input[type='radio']").attr("checked", false);//将之前选择的支付方式去掉
                $('#payment_other_input').val('0');


            }
        } catch (ex) {
        }
        orderSelectedResponse(obj.content);
        document.getElementById("final_total_money").innerHTML=obj.final_total_money;
    }
}

/**
 * 改变积分
 */
function changeIntegral(val, suppid) {

    var con_country = document.forms['theForm'].elements['have_consignee'].value;
    if (con_country == '0') {
        alert('请先选择配送地址！');
        obj.checked = false;
        return;
    }

    if (selectedIntegral == val) {
        return;
    } else {
        selectedIntegral = val;
    }
    var oldselcart_checkout = $("#oldselcart_checkout").val();
    var sessionCode = $("#sessionCode").val();
    Ajax.call('flow.php?step=change_integral', 'points=' + val + '&suppid=' + suppid + '&oldselcart_checkout=' +oldselcart_checkout+'&sessionCode='+sessionCode, changeIntegralResponse, 'GET', 'JSON');
}

/**
 * 改变积分回调函数
 */
function changeIntegralResponse(obj) {
    if (obj.error) {
        try {
            alert(obj.error);
            //document.getElementById('ECS_INTEGRAL_NOTICE_' + obj.suppid).innerHTML = obj.error;
            document.getElementById('ECS_INTEGRAL_' + obj.suppid).value = '0';
            document.getElementById('ECS_INTEGRAL_' + obj.suppid).focus();
        } catch (ex) {
        }
        if(obj.error == 'goods_price_change'){
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html('您的商品活动状态有变动，请重新处理');
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            setTimeout('window.location.href="flow.php";',3000);
            return false;
        }else if(obj.error == 'cart_have_change'){
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html('您的购物车已变更，请重新处理');
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            setTimeout('window.location.href="flow.php";',3000);
            return false;
        }

    } else {
        try {
            document.getElementById('ECS_INTEGRAL_NOTICE_' + obj.suppid).innerHTML = '';

            //为0启用，不为0禁用积分
            if(document.getElementById('ECS_INTEGRAL_' + obj.suppid).value == 0){
                //启用优惠券
                enuseBonus("bonus",obj);
            }
            else{
                //禁用优惠券
                unuseBonus("bonus",obj);
            }


        } catch (ex) {
        }

        orderSelectedResponse(obj.content);
        document.getElementById("final_total_money").innerHTML=obj.final_total_money;
        supplierOrderSelectedResponse(obj.supplier_content,obj.suppid);

    }

    //联运触发余额如果余额是选中状态
    if($('#issurplus').prop('checked')){
        $('#issurplus').prop('checked',false).trigger('click');
    }
}
/**
 * 禁止使用优惠券／积分，且选中余额时候刷新余额和价格
 */
function unuseBonus(type,obj){
    var type = type || "bonus"  //integral,bonus
    //禁用优惠券
    if(type == "bonus"){
        document.getElementById('ECS_BONUS_' + obj.suppid).value = '0';
        $('#ECS_BONUS_' + obj.suppid).attr("disabled","disabled");
        $('.input_addr2').attr("disabled","disabled");
    }
    else if(type == "integral"){
        //禁用积分
        document.getElementById('ECS_INTEGRAL_' + obj.suppid).value = '0';
        $('#ECS_INTEGRAL_'+ obj.suppid).attr("disabled","disabled");
    }
}


function enuseBonus(type,obj){
    var type = type || "bonus"  //integral,bonus
    //禁用优惠券
    if(type == "bonus"){
        //document.getElementById('ECS_BONUS_' + obj.suppid).value = '0';
        $('#ECS_BONUS_' + obj.suppid).removeAttr("disabled");
        $('.input_addr2').removeAttr("disabled");
    }
    else if(type == "integral"){
        //禁用积分
        //document.getElementById('ECS_INTEGRAL_' + obj.suppid).value = '0';
        $('#ECS_INTEGRAL_'+ obj.suppid).removeAttr("disabled");
    }
}


/**
 * 改变优惠券
 */
function changeBonus(val, suppid) {

    var con_country = document.forms['theForm'].elements['have_consignee'].value;
    if (con_country == '0') {
        alert('请先选择配送地址！');
        obj.checked = false;
        return;
    }

    if (selectedBonus == val) {
        // return;
    } else {
        // selectedBonus = val;
    }
    var oldselcart_checkout = $("#oldselcart_checkout").val();
    var sessionCode = $("#sessionCode").val();
    Ajax.call('flow.php?step=change_bonus', 'bonus=' + val + '&suppid=' + suppid + '&oldselcart_checkout=' +oldselcart_checkout + "&sessionCode=" + sessionCode, changeBonusResponse, 'GET', 'JSON');
}

/**
 * 改变优惠券的回调函数
 */
function changeBonusResponse(obj) {
    if (obj.error) {
        if(obj.error == 'goods_price_change'){
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html('您的商品活动状态有变动，请重新处理');
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            setTimeout('window.location.href="flow.php";',3000);
            return false;
        }else if(obj.error == 'cart_have_change'){
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html('您的购物车商品已改变，请重新选择');
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            setTimeout('window.location.href="flow.php";',3000);
            return false;
        }else{
            alert(obj.error);
            try {
                document.getElementById('ECS_BONUS_' + obj.suppid).value = '0';
            } catch (ex) {
            }
        }

    } else {
        document.getElementById('bonus_sn_' + obj.suppid).value = '';
        document.getElementById('bonus_input_' + obj.suppid).value = '';
        orderSelectedResponse(obj.content);
        supplierOrderSelectedResponse(obj.supplier_content,obj.suppid)
        document.getElementById("final_total_money").innerHTML=obj.final_total_money;

        if(document.getElementById('ECS_BONUS_' + obj.suppid).value == "0"){
            //启用积分
            enuseBonus("integral",obj);
            //启用优惠券码
            $("#bonus_input_"+obj.suppid).removeAttr("readonly");
        }
        else{
            //禁用积分
            unuseBonus("integral",obj);
        }


    }
    //联运触发余额如果余额是选中状态
    if($('#issurplus').prop('checked')){
        $('#issurplus').prop('checked',false).trigger('click');
    }
}

/**
 * 验证优惠券序列号
 */
function validateBonus(val, suppid) {

    var con_country = document.forms['theForm'].elements['have_consignee'].value;
    if (con_country == '0') {
        alert('请先选择配送地址！');
        obj.checked = false;
        return false;
    }
    val = val.replace(/(^\s*)|(\s*$)/g, "");
    if (val == '输入优惠券' || val.length == 0) {
        return false;
    }
    var oldselcart_checkout = $("#oldselcart_checkout").val();
    var sessionCode = $("#sessionCode").val();
    Ajax.call('flow.php?step=validate_bonus', 'bonus_sn=' + val + '&suppid=' + suppid + '&oldselcart_checkout=' +oldselcart_checkout+"&sessionCode="+sessionCode, validateBonusResponse, 'GET', 'JSON');
}

function validateBonusResponse(obj) {
    var suppid = obj.suppid;
    if (obj.error) {
        if(obj.error == 'goods_price_change'){
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html('您的商品活动状态有变动，请重新处理');
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            setTimeout('window.location.href="flow.php";',3000);
            return false;
        }else if(obj.error == 'cart_have_change'){
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html('您的购物车已变更，请重新处理');
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            setTimeout('window.location.href="flow.php";',3000);
            return false;
        }else{
            alert(obj.error);
            // orderSelectedResponse(obj.content);
            try {
                document.getElementById('bonus_sn_' + obj.suppid).value = '';
            } catch (ex) {
            }
            return false;
        }

    } else {
        var bbsn = $("#bonus_input_"+suppid).val();
        $("#bonus_sn_"+suppid).val(bbsn);
        orderSelectedResponse(obj.content);
        supplierOrderSelectedResponse(obj.supplier_content,obj.suppid)
        document.getElementById("final_total_money").innerHTML=obj.final_total_money;
        document.getElementById('ECS_BONUS_'+obj.suppid).selectedIndex = '0';
        $("#bonus_input_"+suppid).attr("readonly","readonly");
        if(document.getElementById('bonus_sn_' + obj.suppid).value == ""){
            //启用积分
            enuseBonus("integral",obj);
        }
        else{
            //禁用积分
            unuseBonus("integral",obj);
        }
    }

    //联运触发余额如果余额是选中状态
    if($('#issurplus').prop('checked')){
        $('#issurplus').prop('checked',false).trigger('click');
    }
}

/**
 * 改变发票的方式
 */
function changeNeedInv(invID) {
    var con_country = document.forms['theForm'].elements['have_consignee'].value;
    if (con_country == '0') {
        alert('请先选择配送地址！');
        obj.checked = false;
        return;
    }
    var obj = document.getElementById('ECS_NEEDINV_'+invID);
    var objType = document.getElementById('ECS_INVTYPE_'+invID);
    var objPayee = document.getElementById('ECS_INVPAYEE_'+invID);
    var objTaxNumber = document.getElementById('ECS_INVTAXNUMBER_'+invID);
    var objContent = document.getElementById('ECS_INVCONTENT_'+invID);
    var needInv = obj.checked ? 1 : 0;
    var invType = obj.checked ? (objType != undefined ? objType.value : '') : '';
    var invPayee = obj.checked ? objPayee.value : '';
    var invTaxNumber = obj.checked ? objTaxNumber.value : '';
    var invContent = obj.checked ? objContent.value : '';
    objType.disabled = objPayee.disabled = objTaxNumber.disabled = objContent.disabled = !obj.checked;
    if (objType != null) {
        objType.disabled = !obj.checked;
    }
    //增加去重复动作
    if(needInv==1){
        document.getElementById('normal_invoice_tbody_'+invID).style.display="block";
        //发票内容：
        $('#ECS_INVTYPE option[value="normal_invoice"]').attr("selected", true);
        setTimeout(function(){$('#ECS_INVCONTENT option[value="其他"]').attr("selected", true);}, 500);
    }
    else{
        document.getElementById('normal_invoice_tbody_'+invID).style.display="none";
    }
    //去重复动作结束
    // 增值税发票_添加_START_
    var individual_inv = document.forms['theForm'].elements['inv_payee_type['+invID+']'].item(0);
    var unit_inv = document.forms['theForm'].elements['inv_payee_type['+invID+']'].item(1);
    var inv_payee_type = individual_inv.checked ? individual_inv.value : '';
    if (!individual_inv.checked) {
        inv_payee_type = unit_inv.checked ? unit_inv.value : '';
    }
    var normal_invoice_tbody = document.getElementById('normal_invoice_tbody');
    var vat_invoice_tbody = document.getElementById('vat_invoice_tbody');
    var b1 = false;
    var b2 = false;
    if (invType == 'normal_invoice') {
        b1 = true;
    } else if (invType == 'vat_invoice') {
        b2 = true;
    }
	/* 	if (normal_invoice_tbody != null) {
	 normal_invoice_tbody.style.display = b1 ? '' : 'block';
	 } */
    if (vat_invoice_tbody != null) {
        vat_invoice_tbody.style.display = b2 ? '' : 'none';
    }
    if (inv_payee_type == 'unit') {
        objPayee.style.display = '';
        objTaxNumber.style.display = '';
    } else {
        objPayee.style.display = 'none';
        objTaxNumber.style.display = 'none';
    }

    // 增值税发票_添加_END_

    Ajax.call('flow.php?step=change_needinv', 'need_inv=' + needInv + '&inv_type=' + encodeURIComponent(invType) + '&inv_payee=' + encodeURIComponent(invPayee) + '&inv_tax_number=' + encodeURIComponent(invTaxNumber) + '&inv_content=' + encodeURIComponent(invContent), changeNeedInvResponse, 'GET');
}

function changeNeedInvResponse(obj) {

    orderSelectedResponse(obj);
    //联运触发余额如果余额是选中状态
    if($('#issurplus').attr('checked') != 'undefined' && $('#issurplus').attr('checked')){
        $('#issurplus').attr('checked',false).trigger("click").attr('checked',false);
    }
}

/*******************************************************************************
 * 改变发票的方式
 */
function groupBuyChangeNeedInv() {
    var obj = document.getElementById('ECS_NEEDINV');
    var objPayee = document.getElementById('ECS_INVPAYEE');
    var objTaxNumber = document.getElementById('ECS_INVTAXNUMBER');
    var objContent = document.getElementById('ECS_INVCONTENT');
    var needInv = obj.checked ? 1 : 0;
    var invPayee = obj.checked ? objPayee.value : '';
    var invTaxNumber = obj.checked ? objTaxNumber.value : '';
    var invContent = obj.checked ? objContent.value : '';
    objPayee.disabled = objTaxNumber.disable = objContent.disabled = !obj.checked;

    Ajax.call('group_buy.php?act=change_needinv', 'need_idv=' + needInv + '&amp;payee=' + invPayee + '&amp;taxNumber=' + invTaxNumber + '&amp;content=' + invContent, null, 'GET');
}

/*******************************************************************************
 * 改变缺货处理时的处理方式
 */
function changeOOS(obj) {
    if (selectedOOS == obj) {
        return;
    } else {
        selectedOOS = obj;
    }

    Ajax.call('flow.php?step=change_oos', 'oos=' + obj.value, null, 'GET');
}


function check_before_submit() {
    var the_form = document.getElementById("theForm");
    if (checkOrderForm(the_form)) {

        $("#ordersubimtx").attr("disabled",true);
        $("#ordersubimtx").hide();
        $("#ordersubimty").show();

        Ajax.call('flow.php?step=check_surplus_open', '', is_surplus_open, 'GET', 'TEXT', true, true);


    }
    return false;
}

function check_before_submit_vir() {
    Ajax.call('flow.php?step=check_surplus_open', '', is_surplus_open, 'GET', 'TEXT', true, true);
    return false;
}

function is_surplus_open(result) {
    if (result == '1') {
        open_surplus_window();
    } else {
        submit_the_form();
    }
}

function open_surplus_window() {
    document.getElementById("popup_window").style.display = "";
    document.getElementById('bg').style.display = "";
    $('.surplus_password_input_notice').css('visibility','hidden').hide().find('.notice-text').html('');
}

function end_input_surplus() {
    var surplus_password = document.getElementById("surplus_password_input").value;
    Ajax.call('flow.php?step=verify_surplus_password', 'surplus_password=' + surplus_password, check_surplus_response, 'GET', 'TEXT', true, true);
}

function check_surplus_response(result) {
    if (result == '1') {
        submit_the_form();
        $('.surplus_password_input_notice').css('visibility','hidden').hide().find('.notice-text').html('');
    } else {
        //alert('密码错误！');
        $('.surplus_password_input_notice').css('visibility','visible').show().find('.notice-text').html('密码错误');
    }
}

function cancel_input_surplus() {
    document.getElementById("surplus_password_input").value = "";
    document.getElementById("popup_window").style.display = "none";
    document.getElementById('bg').style.display = "none";
    $('.surplus_password_input_notice').css('visibility','hidden').hide().find('.notice-text').html('');
}

function submit_the_form() {
    var the_form = document.getElementById("theForm");
    the_form.submit();
}
function inputbonus(supid){
    $("#bonus_sn_"+supid).val('');
}
/*******************************************************************************
 * 检查提交的订单表单
 */
function checkOrderForm(frm) {
    //判断商品价格是否变动
    var cansubmit = 1;
    //验证商品活动有无变化
    var oldselcart_checkout = $("#oldselcart_checkout").val();
    $.ajax({
        url:"flow.php?step=goods_activity_change",
        async:false,
        data:{"oldselcart_checkout":oldselcart_checkout},
        success:function(rere){
            if(rere == 1){
                cansubmit = 0;
                $('.pop-compare,.pop-mask').show();
                $('.pop-compare .pop-text').html('您的商品活动状态有变动，请重新处理');
                $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
                $('.cancel-btn').addClass('none');
                setTimeout('window.location.href="flow.php";',3000);
                return false;
            }else if(rere == 2){
                cansubmit = 0;
                $('.pop-compare,.pop-mask').show();
                $('.pop-compare .pop-text').html('您的购物车已变更，请重新处理');
                $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
                $('.cancel-btn').addClass('none');
                setTimeout('window.location.href="flow.php";',3000);
                return false;
            }
        }
    })
    if(cansubmit == 0){
        return false;
    }
    var mk_total = parseFloat($("input[name=mk_t]").val());
    var mk_price = $('#issurplus:checked').val();
    var mk_surplus = parseFloat($('#ECS_SURPLUS').val());
    var mk_pay = $('.mk_pay:checked').val();
    var mk_yue = parseFloat($('#mk_yue').text());
    var bonus_sn_list = $(".bonus_sn_list").val();
    var bon = 0;
    if(mk_price){
        if(mk_surplus > mk_yue){
            return false;
        }
        if(mk_surplus < mk_total){
            if(!mk_pay){
                $('.pop-compare,.pop-mask').show();
                $('.pop-compare .pop-text').html("请选择第三方支付方式");
                $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
                $('.cancel-btn').addClass('none');
                $('#ordersubimtx').show();
                $('#ordersubimty').hide();
                return false;
            }
        }
    }

    $('#ordersubimtx').hide();
    $('#ordersubimty').show();

    var paymentSelected = false;
    var shippingSelected = true;


    have_other = false;
    if (frm.elements['payment_other']) {
        for (var iii = 0; iii < frm.elements['payment_other'].length; iii++) {
            if (frm.elements['payment_other'][iii].checked) {
                have_other = true;
            }
        }
    }

	/*送货时间选择*/
    for(var j=0;j<frm.elements['best_time'].length;j++){
        if(frm.elements['best_time'][j].checked && frm.elements['best_time'][j].value==''){
            document.getElementById('definetime_input').focus();
            document.getElementById('time_id_4').className='curr';
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html('请选择送货时间');
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            return false;
        }

    }

    have_alipay = false;
    if (frm.elements['www_68ecshop_com_bank']) {
        for (var iii = 0; iii < frm.elements['www_68ecshop_com_bank'].length; iii++) {
            if (frm.elements['www_68ecshop_com_bank'][iii].checked) {
                have_alipay = true;
            }
        }

    }

    // 检查是否选择了支付配送方式
    var issurplusSelected = paySelected = paymentSelected  = true;
    if($("#issurplus").attr('checked')!='checked'){
        issurplusSelected = false;
    }

    var total_amount= document.getElementById("final_total_money").textContent;
    if (total_amount!=="¥0.00"){
        if (!$("#payment_other_input").prop('checked')){
            paymentSelected = false;
        }else{
            if($("#payment_other1").attr('checked')!='checked' &&
                $("#payment_other3").attr('checked')!='checked' &&
                $("#payment_other4").attr('checked')!='checked' &&
                $("#payment_other5").attr('checked')!='checked' &&
                $("#payment_other6").attr('checked')!='checked'){
                paymentSelected = false;
            }
        }
    }
    if($("#payment_other1").attr('checked')!='checked' &&
        $("#payment_other3").attr('checked')!='checked' &&
        $("#payment_other4").attr('checked')!='checked' &&
        $("#payment_other5").attr('checked')!='checked' &&
        $("#payment_other6").attr('checked')!='checked'){
        paySelected = false;
    }

    if(!issurplusSelected && !paySelected){
        paymentSelected = false;
    }

    if (!paymentSelected) {
            //alert(flow_no_payment);
            $('.pop-compare,.pop-mask').show();
            $('.pop-compare .pop-text').html(flow_no_payment);
            $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
            $('.cancel-btn').addClass('none');
            $('#ordersubimtx').show();
            $('#ordersubimty').hide();
            return false;
        }
    // 检查用户输入的余额
    if (document.getElementById("ECS_SURPLUS")) {
        var surplus = document.getElementById("ECS_SURPLUS").value;
        var error = Utils.trim(Ajax.call('flow.php?step=check_surplus', 'surplus=' + surplus, null, 'GET', 'TEXT', false));

        if (error) {
            try {
                document.getElementById("ECS_SURPLUS_NOTICE").innerHTML = error;
            } catch (ex) {
            }
            return false;
        }
    }

    // 检查用户输入的积分
    if (document.getElementById("ECS_INTEGRAL")) {
        var integral = document.getElementById("ECS_INTEGRAL").value;
        var error = Utils.trim(Ajax.call('flow.php?step=check_integral', 'integral=' + integral, null, 'GET', 'TEXT', false));

        if (error) {
            return false;
            try {
                document.getElementById("ECS_INTEGRAL_NOTICE").innerHTML = error;
            } catch (ex) {
            }
        }
    }
    // 增值税发票_添加_START_
	/* 检查发票 */
	/* if (document.getElementById('ECS_NEEDINV').checked) {
	 if (frm.elements['inv_content'].value == '0' || frm.elements['inv_content'].value == '') {
	 $('.pop-compare,.pop-mask').show();
	 $('.pop-compare .pop-text').html('请选择发票内容');
	 $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
	 $('.cancel-btn').addClass('none');
	 //alert('请选择发票内容');
	 return false;
	 }
	 if (frm.elements['inv_type'].value == 'vat_invoice') {
	 var check_array = new Array('vat_inv_company_name', 'vat_inv_taxpayer_id', 'vat_inv_registration_address', 'vat_inv_registration_phone', 'vat_inv_deposit_bank', 'vat_inv_bank_account', 'inv_consignee_name', 'inv_consignee_phone', 'inv_consignee_province', 'inv_consignee_city', 'inv_consignee_address');
	 for (id in check_array) {
	 if (frm.elements[check_array[id]].style.display != 'none' && (frm.elements[check_array[id]].value == '0' || frm.elements[check_array[id]].value == '')) {
	 //alert('请输入增值税发票相关信息');
	 $('.pop-compare,.pop-mask').show();
	 $('.pop-compare .pop-text').html('请输入增值税发票相关信息');
	 $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
	 $('.cancel-btn').addClass('none');
	 return false;
	 }
	 }
	 } else if (frm.elements['inv_type'].value == 'normal_invoice') {
	 if (document.getElementById('unit_inv').checked) {
	 if (frm.elements['inv_payee'].value == '') {
	 $('.pop-compare,.pop-mask').show();
	 $('.pop-compare .pop-text').html('请输入单位名称');
	 $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
	 $('.cancel-btn').addClass('none');
	 //alert('请输入单位名称');
	 return false;
	 }
	 } else if (!document.getElementById('unit_inv').checked && !document.getElementById('individual_inv').checked) {
	 //alert('请选择发票抬头');
	 $('.pop-compare,.pop-mask').show();
	 $('.pop-compare .pop-text').html('请选择发票抬头');
	 $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
	 $('.cancel-btn').addClass('none');
	 return false;
	 }
	 } else {
	 //alert('请选择发票类型');
	 $('.pop-compare,.pop-mask').show();
	 $('.pop-compare .pop-text').html('请选择发票类型');
	 $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
	 $('.cancel-btn').addClass('none');
	 return false;
	 }
	 }
	 */
    //  display:none; vertical-align:middle
    //  vertical-align: middle;

    var individual_inv = frm.elements['inv_payee_type[0]'].item(0);
    var unit_inv = frm.elements['inv_payee_type[0]'].item(1);
    var inv_payee_type = individual_inv.checked ? individual_inv.value : '';
    if (!individual_inv.checked) {
        inv_payee_type = unit_inv.checked ? unit_inv.value : '';
    }

    if($("#ECS_NEEDINV").prop('checked') == true && inv_payee_type == 'unit' && $("#ECS_INVPAYEE_0").val()=='' ){
        $('.pop-compare,.pop-mask').show();
        $('.pop-compare .pop-text').html('请输入单位名称');
        $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
        $('.cancel-btn').addClass('none');
        $('#ordersubimtx').show();
        $('#ordersubimty').hide();
        return false;
    }
    if($("#ECS_NEEDINV").prop('checked') == true && inv_payee_type == 'unit' && $("#ECS_INVTAXNUMBER_0").val()=='' ){
        $('.pop-compare,.pop-mask').show();
        $('.pop-compare .pop-text').html('请输入公司税号');
        $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
        $('.cancel-btn').addClass('none');
        $('#ordersubimtx').show();
        $('#ordersubimty').hide();
        return false;
    }

    showLoading();
    // 增值税发票_添加_END_
    frm.action = frm.action + '?step=done';
    return true;
}

/*******************************************************************************
 * 检查收货地址信息表单中填写的内容
 */
function checkConsignee(frm) {
    var msg = new Array();
    var err = false;

    if (frm.elements['country'] && frm.elements['country'].value == 0) {
        msg.push(country_not_null);
        err = true;
    }

    if (frm.elements['province'] && frm.elements['province'].value == 0 && frm.elements['province'].length > 1) {
        err = true;
        msg.push(province_not_null);
    }

    if (frm.elements['city'] && frm.elements['city'].value == 0 && frm.elements['city'].length > 1) {
        err = true;
        msg.push(city_not_null);
    }

    if (frm.elements['district'] && frm.elements['district'].length > 1) {
        if (frm.elements['district'].value == 0) {
            err = true;
            msg.push(district_not_null);
        }
    }

    if (Utils.isEmpty(frm.elements['consignee'].value)) {
        err = true;
        msg.push(consignee_not_null);
    }
    //邮箱如果不为空则验证邮箱是否合法
    // if (frm.elements['email'].value.length > 0 &&!Utils.isEmail(frm.elements['email'].value)) {
    // 	err = true;
    // 	msg.push(invalid_email);
    // }

//    if (frm.elements['address'] && Utils.isEmpty(frm.elements['address'].value)) {
//        err = true;
//        msg.push(address_not_null);
//    }

    if(frm.elements['address'] && $.trim(frm.elements['address'].value).length<5)
    {
        err = true;
        msg.push("街道地址不能少于5个字");
    }
    else if(frm.elements['address'] && $.trim(frm.elements['address'].value).length>60)
    {
        err = true;
        msg.push("街道地址不能大于60个字");
    }



    if (frm.elements['zipcode'] && frm.elements['zipcode'].value.length > 0 && (!Utils.isNumber(frm.elements['zipcode'].value))) {
        err = true;
        msg.push(zip_not_num);
    }

	/*
	 * if (Utils.isEmpty(frm.elements['tel'].value)) { err = true;
	 * msg.push(tele_not_null); } else { if
	 * (!Utils.isTel(frm.elements['tel'].value)) { err = true;
	 * msg.push(tele_invaild); } }
	 * 
	 */
//	if (frm.elements['mobile'] && frm.elements['mobile'].value.length > 0 && (!Utils.isMobile(frm.elements['mobile'].value))) {
//		err = true;
//		msg.push(mobile_invaild);
//	}

    if (Utils.isEmpty(frm.elements['mobile'].value) && Utils.isEmpty(frm.elements['tel'].value)) {
        err = true;
        msg.push(mobile_or_tel_not_null);
    } else {
        if (!Utils.isEmpty(frm.elements['mobile'].value) && !Utils.isMobile(frm.elements['mobile'].value)) {
            err = true;
            msg.push(mobile_invaild);
        }
        if (!Utils.isEmpty(frm.elements['tel'].value) && !Utils.isTel(frm.elements['tel'].value)) {
            err = true;
            msg.push(tele_invaild);
        }
    }

    if (err) {
        message = msg.join("\n");
        alert(message);
    }
    return !err;
}
// 增值税发票_添加_START_
function check_taxpayer_id(t, id) {
    if (!check_preg_match(t.value, 'taxpayer_id')) {
        document.getElementById(id).innerHTML = '纳税人识别号错误，请检查！';
    } else {
        document.getElementById(id).innerHTML = '';
    }
}

function check_bank_account(t, id) {
    if (!check_preg_match(t.value, 'back_account')) {
        document.getElementById(id).innerHTML = '银行账户含有非法字符！';
    } else {
        document.getElementById(id).innerHTML = '';
    }
}

function check_phone_number(t, id) {
    if (!check_preg_match(t.value, 'phone_number')) {
        document.getElementById(id).innerHTML = '手机号码格式不正确！';
    } else {
        document.getElementById(id).innerHTML = '';
    }
}

function check_preg_match(v, type) {
    var pattern = '';
    switch (type) {
        case 'taxpayer_id':
            pattern = '^[0-9]{15,}$';
            break;
        case 'back_account':
            pattern = '^[0-9A-z]+ *[0-9A-z]+$';
            break;
        case 'phone_number':
            pattern = '^1[0-9]{10}$';
    }
    var preg = new RegExp(pattern);
    return preg.test(v);
}
// 增值税发票_添加_END_

function doNewMobile() {
    var mobile_phone = $("#mobile_phone").val();
    if (mobile_phone == '' || !Utils.isMobile(mobile_phone)) {
        //alert('请填写正确的手机号');
        $('.pop-compare,.pop-mask').show();
        $('.pop-compare .pop-text').html('请填写正确的手机号');
        $('.pop-compare').css({'top':($(window).height()-$('.pop-compare').outerHeight())/2});
    } else {
        Ajax.call('flow.php?act=edit_user_mobile', 'mobile_phone=' + mobile_phone, get_user_mobile, "GET", "JSON");
    }
}
/**
 *同意预售协议的可选框
 */

function checkAgree(){

    if ($("#pre_agree").attr("checked")) {
        $("[name='pre_agree']").removeAttr("checked");
        $("#ordersubimtx").hide();
        $("#ordersubimty").show();
    } else {
        $("[name='pre_agree']").attr("checked",'true');//全选
        $("#ordersubimtx").show();
        $("#ordersubimty").hide();
    }
}

/**
 * 判断是否是众筹
 * @returns {number}
 */
function isZc()
{
    var result = '';
    var zc_id = 0;
    if($("#zc_buy").val())
    {
        var zc_id = $("#zc_buy").val();
    }

    if(zc_id == 1)
    {
        result = '&zc=1';
    }
    return result;
}