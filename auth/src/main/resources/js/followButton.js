!function($win,$doc){var STK=(function(){var that={};var errorList=[];that.inc=function(ns,undepended){return true};that.register=function(ns,maker){var NSList=ns.split(".");var step=that;var k=null;while(k=NSList.shift()){if(NSList.length){if(step[k]===undefined){step[k]={}}step=step[k]}else{if(step[k]===undefined){try{step[k]=maker(that)}catch(exp){errorList.push(exp)}}}}};that.regShort=function(sname,sfun){if(that[sname]!==undefined){throw"["+sname+"] : short : has been register"}that[sname]=sfun};that.IE=/msie/i.test(navigator.userAgent);that.E=function(id){if(typeof id==="string"){return document.getElementById(id)}else{return id}};that.C=function(tagName){var dom;tagName=tagName.toUpperCase();if(tagName=="TEXT"){dom=document.createTextNode("")}else{if(tagName=="BUFFER"){dom=document.createDocumentFragment()}else{dom=document.createElement(tagName)}}return dom};that.log=function(str){errorList.push("["+((new Date()).getTime()%100000)+"]: "+str)};that.getErrorLogInformationList=function(n){return errorList.splice(0,n||errorList.length)};return that})();$Import=STK.inc;STK.register("core.evt.addEvent",function($){return function(sNode,sEventType,oFunc){var oElement=$.E(sNode);if(oElement==null){return false}sEventType=sEventType||"click";if((typeof oFunc).toLowerCase()!="function"){return}if(oElement.addEventListener){oElement.addEventListener(sEventType,oFunc,false)}else{if(oElement.attachEvent){oElement.attachEvent("on"+sEventType,oFunc)}else{oElement["on"+sEventType]=oFunc}}return true}});STK.register("core.util.browser",function($){var ua=navigator.userAgent.toLowerCase();var external=window.external||"";var core,m,extra,version,os;var numberify=function(s){var c=0;return parseFloat(s.replace(/\./g,function(){return(c++==1)?"":"."}))};try{if((/windows|win32/i).test(ua)){os="windows"}else{if((/macintosh/i).test(ua)){os="macintosh"}else{if((/rhino/i).test(ua)){os="rhino"}}}if((m=ua.match(/applewebkit\/([^\s]*)/))&&m[1]){core="webkit";version=numberify(m[1])}else{if((m=ua.match(/presto\/([\d.]*)/))&&m[1]){core="presto";version=numberify(m[1])}else{if(m=ua.match(/msie\s([^;]*)/)){core="trident";version=1;if((m=ua.match(/trident\/([\d.]*)/))&&m[1]){version=numberify(m[1])}}else{if(/gecko/.test(ua)){core="gecko";version=1;if((m=ua.match(/rv:([\d.]*)/))&&m[1]){version=numberify(m[1])}}}}}if(/world/.test(ua)){extra="world"}else{if(/360se/.test(ua)){extra="360"}else{if((/maxthon/.test(ua))||typeof external.max_version=="number"){extra="maxthon"}else{if(/tencenttraveler\s([\d.]*)/.test(ua)){extra="tt"}else{if(/se\s([\d.]*)/.test(ua)){extra="sogou"}}}}}}catch(e){}var ret={OS:os,CORE:core,Version:version,EXTRA:(extra?extra:false),IE:/msie/.test(ua),OPERA:/opera/.test(ua),MOZ:/gecko/.test(ua)&&!/(compatible|webkit)/.test(ua),IE5:/msie 5 /.test(ua),IE55:/msie 5.5/.test(ua),IE6:/msie 6/.test(ua),IE7:/msie 7/.test(ua),IE8:/msie 8/.test(ua),IE9:/msie 9/.test(ua),SAFARI:!/chrome\/([\d.]*)/.test(ua)&&/\/([\d.]*) safari/.test(ua),CHROME:/chrome\/([\d.]*)/.test(ua),IPAD:/\(ipad/i.test(ua),IPHONE:/\(iphone/i.test(ua),ITOUCH:/\(itouch/i.test(ua),MOBILE:/mobile/i.test(ua)};return ret});STK.register("core.evt.getEvent",function($){return function(){if($.IE){return window.event}else{if(window.event){return window.event}var o=arguments.callee.caller;var e;var n=0;while(o!=null&&n<40){e=o.arguments[0];if(e&&(e.constructor==Event||e.constructor==MouseEvent||e.constructor==KeyboardEvent)){return e}n++;o=o.caller}return e}}});STK.register("core.evt.stopEvent",function($){return function(e){var ev=e?e:$.core.evt.getEvent();if($.IE){ev.cancelBubble=true;ev.returnValue=false}else{ev.preventDefault();ev.stopPropagation()}return false}});STK.register("core.evt.removeEvent",function($){return function(el,evType,func,useCapture){var _el=$.E(el);if(_el==null){return false}if(typeof func!="function"){return false}if(_el.removeEventListener){_el.removeEventListener(evType,func,useCapture)}else{if(_el.detachEvent){_el.detachEvent("on"+evType,func)}else{_el["on"+evType]=null}}return true}});STK.register("core.io.getXHR",function($){return function(){var _XHR=false;try{_XHR=new XMLHttpRequest()}catch(try_MS){try{_XHR=new ActiveXObject("Msxml2.XMLHTTP")}catch(other_MS){try{_XHR=new ActiveXObject("Microsoft.XMLHTTP")}catch(failed){_XHR=false}}}return _XHR}});STK.register("core.obj.parseParam",function($){return function(oSource,oParams,isown){var key,obj={};oParams=oParams||{};for(key in oSource){obj[key]=oSource[key];if(oParams[key]!=null){if(isown){if(oSource.hasOwnProperty[key]){obj[key]=oParams[key]}}else{obj[key]=oParams[key]}}}return obj}});STK.register("core.str.parseURL",function($){return function(url){var parse_url=/^(?:([A-Za-z]+):(\/{0,3}))?([0-9.\-A-Za-z]+\.[0-9A-Za-z]+)?(?::(\d+))?(?:\/([^?#]*))?(?:\?([^#]*))?(?:#(.*))?$/;var names=["url","scheme","slash","host","port","path","query","hash"];var results=parse_url.exec(url);var that={};for(var i=0,len=names.length;i<len;i+=1){that[names[i]]=results[i]||""}return that}});STK.register("core.arr.isArray",function($){return function(o){return Object.prototype.toString.call(o)==="[object Array]"}});STK.register("core.str.trim",function($){return function(str){if(typeof str!=="string"){throw"trim need a string as parameter"}var len=str.length;var s=0;var reg=/(\u3000|\s|\t|\u00A0)/;while(s<len){if(!reg.test(str.charAt(s))){break}s+=1}while(len>s){if(!reg.test(str.charAt(len-1))){break}len-=1}return str.slice(s,len)}});STK.register("core.json.queryToJson",function($){return function(QS,isDecode){var _Qlist=$.core.str.trim(QS).split("&");var _json={};var _fData=function(data){if(isDecode){return decodeURIComponent(data)}else{return data}};for(var i=0,len=_Qlist.length;i<len;i++){if(_Qlist[i]){var _hsh=_Qlist[i].split("=");var _key=_hsh[0];var _value=_hsh[1];if(_hsh.length<2){_value=_key;_key="$nullName"}if(!_json[_key]){_json[_key]=_fData(_value)}else{if($.core.arr.isArray(_json[_key])!=true){_json[_key]=[_json[_key]]}_json[_key].push(_fData(_value))}}}return _json}});STK.register("core.json.jsonToQuery",function($){var _fdata=function(data,isEncode){data=data==null?"":data;data=$.core.str.trim(data.toString());if(isEncode){return encodeURIComponent(data)}else{return data}};return function(JSON,isEncode){var _Qstring=[];if(typeof JSON=="object"){for(var k in JSON){if(k==="$nullName"){_Qstring=_Qstring.concat(JSON[k]);continue}if(JSON[k] instanceof Array){for(var i=0,len=JSON[k].length;i<len;i++){_Qstring.push(k+"="+_fdata(JSON[k][i],isEncode))}}else{if(typeof JSON[k]!="function"){_Qstring.push(k+"="+_fdata(JSON[k],isEncode))}}}}if(_Qstring.length){return _Qstring.join("&")}else{return""}}});STK.register("core.util.URL",function($){return function(sURL,args){var opts=$.core.obj.parseParam({isEncodeQuery:false,isEncodeHash:false},args||{});var that={};var url_json=$.core.str.parseURL(sURL);var query_json=$.core.json.queryToJson(url_json.query);var hash_json=$.core.json.queryToJson(url_json.hash);that.setParam=function(sKey,sValue){query_json[sKey]=sValue;return this};that.getParam=function(sKey){return query_json[sKey]};that.setParams=function(oJson){for(var key in oJson){that.setParam(key,oJson[key])}return this};that.setHash=function(sKey,sValue){hash_json[sKey]=sValue;return this};that.getHash=function(sKey){return hash_json[sKey]};that.valueOf=that.toString=function(){var url=[];var query=$.core.json.jsonToQuery(query_json,opts.isEncodeQuery);var hash=$.core.json.jsonToQuery(hash_json,opts.isEncodeQuery);if(url_json.scheme!=""){url.push(url_json.scheme+":");url.push(url_json.slash)}if(url_json.host!=""){url.push(url_json.host);if(url_json.port!=""){url.push(":");url.push(url_json.port)}}url.push("/");url.push(url_json.path);if(query!=""){url.push("?"+query)}if(hash!=""){url.push("#"+hash)}return url.join("")};return that}});STK.register("core.func.empty",function(){return function(){}});STK.register("core.io.ajax",function($){return function(oOpts){var opts=$.core.obj.parseParam({url:"",charset:"UTF-8",timeout:30*1000,args:{},onComplete:null,onTimeout:$.core.func.empty,uniqueID:null,onFail:$.core.func.empty,method:"get",asynchronous:true,header:{},isEncode:false,responseType:"json"},oOpts);if(opts.url==""){throw"ajax need url in parameters object"}var tm;var trans=$.core.io.getXHR();var cback=function(){if(trans.readyState==4){clearTimeout(tm);var data="";if(opts.responseType==="xml"){data=trans.responseXML}else{if(opts.responseType==="text"){data=trans.responseText}else{try{if(trans.responseText&&typeof trans.responseText==="string"){data=eval("("+trans.responseText+")")}else{data={}}}catch(exp){data=opts.url+"return error : data error"}}}if(trans.status==200){if(opts.onComplete!=null){opts.onComplete(data)}}else{if(trans.status==0){}else{if(opts.onFail!=null){opts.onFail(data,trans)}}}}else{if(opts.onTraning!=null){opts.onTraning(trans)}}};trans.onreadystatechange=cback;if(!opts.header["Content-Type"]){opts.header["Content-Type"]="application/x-www-form-urlencoded"}if(!opts.header["X-Requested-With"]){opts.header["X-Requested-With"]="XMLHttpRequest"}if(opts.method.toLocaleLowerCase()=="get"){var url=$.core.util.URL(opts.url,{isEncodeQuery:opts.isEncode});url.setParams(opts.args);url.setParam("__rnd",new Date().valueOf());trans.open(opts.method,url,opts.asynchronous);try{for(var k in opts.header){trans.setRequestHeader(k,opts.header[k])}}catch(exp){}trans.send("")}else{trans.open(opts.method,opts.url,opts.asynchronous);try{for(var k in opts.header){trans.setRequestHeader(k,opts.header[k])}}catch(exp){}trans.send($.core.json.jsonToQuery(opts.args,opts.isEncode))}if(opts.timeout){tm=setTimeout(function(){try{trans.abort()}catch(exp){}opts.onTimeout({},trans);opts.onFail(data,trans)},opts.timeout)}return trans}});if(typeof window.App==="undefined"){App={}}STK.register("common.widget.log",function($){var CATEGARY={app_sharebutton:1,app_followbutton:2,app_livestream:4,app_listweibo:5,app_weiboshow:6,app_commentbox:7};return function(opts){var conf=$.core.obj.parseParam({vsrc:"app_weiboshow",refer:"",step:1},opts);var refer=scope.refer||scope.$refer||conf.refer,uid=scope.loginKit().uid||"",appid=(scope.appkey||$CONFIG.$appkey||$CONFIG.appkey||0),cat=CATEGARY[conf.vsrc]||"";var src="//rs.sinajs.cn/r.gif?uid="+uid+"&appid="+appid+"&refer="+refer+"&cat="+cat+"&step="+conf.step+"&rnd="+(+new Date());var img=new Image();img.src=src;img=null}});STK.register("comp.widget.followButton.login",function($){var lang=scope.language?scope.language:"zh_cn";var url="http://"+location.host+"/dialog/follow.php?fuid="+scope.uid+"&refer="+scope.refer+"&language="+lang+"&type=widget_page&vsrc=app_followbutton&backurl="+encodeURIComponent(document.referrer)+"&rnd="+(new Date().valueOf());if(/service/.test(location.host)){url="http://"+location.host+"/widget/dialog/follow.php?fuid="+scope.uid+"&refer="+scope.refer+"&language="+lang+"&type=widget_page&vsrc=app_followbutton&backurl="+encodeURIComponent(document.referrer)+"&rnd="+(new Date().valueOf())}if(scope.$spr){url+="&c="+scope.$spr}return function(){var showLogin=function(cbfun){App.loginBackUrlCallBack=function(){scope.logined=1;cbfun&&cbfun();$.common.widget.log({vsrc:"app_followbutton",step:2})};scope.postLogin=1;var loginPopWindow=window.open(url,"miniblog_login",["toolbar=0,status=0,resizable=1,width=620,height=540,left=",(screen.width-620)/2,",top=",(screen.height-450)/2].join(""));try{loginPopWindow.focus()}catch(e){}App.loginPopWindow=loginPopWindow;$.common.widget.log({vsrc:"app_followbutton"})};return showLogin}});if(typeof scope==="undefined"){scope={}}scope.loginKit=function(){if(window.scope){var uid=window.scope.current_user_weibo||window.scope.current_user_sina;if(uid){return{uid:uid,isLogin:!!uid}}}if(window.$CONFIG){var uid=window.$CONFIG.current_user_weibo||window.$CONFIG.current_user_sina||window.$CONFIG.uid||window.$CONFIG.$uid;if(uid){return{uid:uid,isLogin:!!uid}}}var documentCookie=document.cookie+";";var supRegExp=["SUP","=([^;]*)?;"].join("");var uidRegExp=["(\\?|&)","uid","=([^&]*)(&|$)"].join("");var info=documentCookie.match(new RegExp(supRegExp,"i"));info=(info)?info[1]||"":"";info=unescape(info);var uid=info.match(new RegExp(uidRegExp));uid=(uid)?uid[2]||"":"";var oid=scope["$oid"];return{uid:uid,isLogin:!!uid,isAdmin:uid&&oid&&(uid==oid)}};scope.$isLogin=function(){return scope.loginKit().isLogin};scope.$isAdmin=function(){return scope.loginKit().isAdmin};STK.register("comp.widget.followButton.ssologin",function($){var analytics=function(status){status=(typeof status=="undefined")?-1:status;var uid=scope.loginKit().uid;(new Image()).src="//rs.sinajs.cn/b.gif?uid="+uid+"&refer="+encodeURIComponent(scope.refer)+"&url="+encodeURIComponent(document.referrer)+"&followed="+status+"&login="+(status==-1?0:1)+"&follow_uid="+scope.uid+"&rnd="+(new Date).getTime()};var ajax=$.core.io.ajax;var isfansUrl="//widget.weibo.com/relationship/aj_isfans.php";if(/service/.test(location.host)){isfansUrl="//"+location.host+"/widget/relationship/aj_isfans.php";if(/jssdk/.test(location.href)){isfansUrl="//"+location.host+"/widget/jssdk/aj_isfans.php"}}return function(callback){(function(){this.entry="wbwidget";this.service="miniblog";this.domain="weibo.com";this.appLoginURL["weibo.com"]="http://weibo.com/sso/login.php"}).call(sinaSSOController);sinaSSOController.autoLogin(function(){if(scope.$isLogin()){ajax({method:"get",url:isfansUrl,args:{uid:scope.uid},onComplete:function(json){json.firstAjax=1;(typeof callback=="function")&&(callback(json));analytics(json.code)}})}else{analytics()}})}});STK.register("comp.widget.followButton.lang",function($){return function(){var sysLang={zh_cn:{n001:"已关注",n002:"你已经关注",n003:"的微博",n004:"你自己",n005:"请重试",n006:"关注",n007:"点此开通微博",n008:"你还未开通微博,不能加关注",n009:"加关注",n010:"取消",n011:"相互关注"},zh_tw:{n001:"已關注",n002:"你已經關注",n003:"的微博",n004:"你自己",n005:"請重試",n006:"關注",n007:"點此開通微博",n008:"你還未開通微博,不能加關注",n009:"加關注",n010:"取消",n011:"相互關注"}},Lang=sysLang[scope.language?scope.language:"zh_cn"];return Lang}});STK.register("common.widget.analytics",function($){var prtl=document.location.protocol.indexOf("https")>-1?"https://":"http://",domain="beacon.sina.com.cn";var SUDA_URL=prtl+domain+"/e.gif";var STATIC_URL=prtl+"rs.sinajs.cn/social.gif";return function(opts){var that={};var conf=$.core.obj.parseParam({gid:"",sid:"",uid:"",sup:"",acode:"weibo_socialshare_widget",aext:"",referer:"",href:document.location.href,requrl:""},opts);var params="?UATrack||"+conf.gid+"||"+conf.sid+"||::"+conf.uid+"::||"+conf.acode+"||"+conf.aext+"||"+conf.referer+"||"+conf.href+"||"+conf.requrl+"||&gUid_"+(new Date().valueOf());var sudaUrl=SUDA_URL+params;var staticUrl=STATIC_URL+params;var SUDA_IMG=new Image();SUDA_IMG.src=sudaUrl;var SUDA_IMG=new Image();SUDA_IMG.src=staticUrl;return that}});var $=STK;var sCore=$.core,login=$.comp.widget.followButton.login(),ssologin=$.comp.widget.followButton.ssologin,removeEvent=sCore.evt.removeEvent,addEvent=sCore.evt.addEvent,stopEvent=sCore.evt.stopEvent,ajax=sCore.io.ajax;var followUrl="http://"+location.host+"/relationship/aj_attention.php",isfansUrl="http://widget.weibo.com/relationship/aj_isfans.php";if(/service/.test(location.host)){followUrl="http://"+location.host+"/widget/relationship/aj_attention.php";isfansUrl="http://"+location.host+"/widget/relationship/aj_isfans.php"}var Lang=$.comp.widget.followButton.lang();var $followBtn=$.E("followBtn"),$fStatus=$.E("fStatus"),$fText=$.E("fText"),$fNum=$.E("fNum");var fClass=$fStatus.className,lock=0;var status={init:function(cls,txt,title){cls=cls?(fClass+" "+cls):fClass;txt&&($fText.innerHTML=txt);$fStatus.className=cls;if(title){$fStatus.title=title}},load:function(){this.init("WB_Bloading",null,"")},followed:function(txt,title){this.init("WB_followed",txt,title);$followBtn.setAttribute("href","http://weibo.com/u/"+scope.uid);$followBtn.setAttribute("target","_blank");addEvent($followBtn,"click",log)}};var log=function(){var SUDA_IMG=new Image();SUDA_IMG.src="//rs.sinajs.cn/social.gif?followbutton=1"};var error=function(){status.init("",Lang.n005)};var numWrite=function(){if($fNum&&scope.write==1){var fans=$fNum.innerHTML;fans=parseInt(fans,10)+1;if(!isNaN(fans)){$fNum.innerHTML=fans}}};var callback=function(json){lock=0;if(json.code=="0"){status.init(null,null,Lang.n006+scope.uname);return}if(json.code==="A00006"||json.code==="A00008"||json.code==="A10007"||json.code==1||json.code==2){if(!json.first){numWrite()}unbindDOM();status.followed(Lang.n001,Lang.n002+scope.uname)}else{if(json.code==="M00006"||json.code==3){unbindDOM();status.followed(Lang.n004,Lang.n004)}else{if(json.code==="M00005"){if(!json.firstAjax){login(addFollow);status.init(null,null,Lang.n006+scope.uname)}return}else{if(!json.firstAjax){error()}}}}};var suda=function(){var URL=$.core.util.URL(document.location.href);var social=URL.getParam("social");var cur_uid=(scope.loginKit().uid||0),_uid=scope.uid||0;if(social){$.common.widget.analytics({uid:cur_uid,aext:"followclk:"+cur_uid+":"+_uid+":"+URL.getParam("domain")})}};var addFollow=function(e){if(lock===1){return}if(parent!=self&&parent!=parent.parent){return}suda();if(scope.logined!=1&&!scope.loginKit().isLogin){login(addFollow);return}lock=1;var data={refer:scope.refer||"",login:scope.postLogin,wsrc:$CONFIG.wsrc||"app_follow_button",uid:scope.uid};var nodeData=$followBtn.getAttribute("action-data");if(!nodeData){return}var nodeJson=$.core.json.queryToJson(nodeData);lock=0;nodeJson.uid&&window.open("http://www.weibo.com/u/"+nodeJson.uid+"?refer_flag=9549880000_guanzhuanniu")};var bindDOM=function(){addEvent($followBtn,"click",addFollow)},unbindDOM=function(){removeEvent($followBtn,"click",addFollow)};bindDOM();ssologin(callback);window.App=window.App||{};window.App.doFollow=addFollow;window.App.loginBackUrlCallBack=function(){}}(window,document);