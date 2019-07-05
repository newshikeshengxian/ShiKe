/**
 * 默认函数
 * @author yyj
 * 20171116
 */
//广告点击次数记载
function loadClickAdNum(adID){
	if(parseInt(adID)>0){
		
		$.ajax({
		     type: "POST",
		     url:  'common.php?act=loadclickadnum',
		     data: {adID:adID},
		     dataType: "json",
		     success: function(data){
		     	return false;
		     }
	    });
	}
}













