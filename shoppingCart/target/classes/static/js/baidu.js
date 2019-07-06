// 百度地图API功能
	var nowCity = new BMap.LocalCity();

	nowCity.get(bdGetPosition); 
	
function bdGetPosition(result){
		var cityName = result.name; //当前的城市名
		LocalCity.innerHTML = "送至：" +cityName;
		setCookie("LocalCity",cityName,"d30");
		} 
		
		