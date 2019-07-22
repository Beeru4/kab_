/**
 * pulic js code
 */
function addFav(url) { // 加入收藏夹 
	if (document.all) {
		window.external.addFavorite(url,document.title);
	} else if (window.sidebar) { 
		window.sidebar.addPanel(document.title, url , "");
	}else{ 
		alert("加入收藏失败，请使用ctrl+D 的方式添加");
	}
}
function setHomepage(url) { // 设置首页 
	if (document.all) {
		document.body.style.behavior = 'url(#default#homepage)';
		document.body.setHomePage(url);
	} else if (window.sidebar) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				alert("该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true");
			}
		}
		var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
		prefs.setCharPref('browser.startup.homepage', url);
	}else{
		alert("抱歉，该浏览器无法完成操作。\n\n请手动将【"+url+"】设置为首页。");
	}
}
(function ($) {
	$.fn.sGallery = function (o) {
	    return  new $sG(this, o);
				//alert('do');
	    };

		var settings = {
			thumbObj:null,//预览对象
			titleObj:null,//标题
			botLast:null,//按钮上一个
			botNext:null,//按钮下一个
			thumbNowClass:'now',//预览对象当前的class,默认为now
			slideTime:800,//平滑过渡时间
			autoChange:true,//是否自动切换
			changeTime:5000,//自动切换时间
			delayTime:100//鼠标经过时反应的延迟时间
		};

	 $.sGalleryLong = function(e, o) {
	    this.options = $.extend({}, settings, o || {});
		var _self = $(e);
		var set = this.options;
		var thumb;
		var size = _self.size();
		var nowIndex = 0; //定义全局指针
		var index;//定义全局指针
		var startRun;//预定义自动运行参数
		var delayRun;//预定义延迟运行参数

	//初始化
		_self.eq(0).show();

	//主切换函数
	function fadeAB () {
		if (nowIndex != index) {
			if (set.thumbObj!=null) {
			$(set.thumbObj).removeClass().eq(index).addClass(set.thumbNowClass);}
			_self.eq(nowIndex).stop(false,true).fadeOut(set.slideTime);
			_self.eq(index).stop(true,true).fadeIn(set.slideTime);
			$(set.titleObj).eq(nowIndex).hide();//新增加title
			$(set.titleObj).eq(index).show();//新增加title
			nowIndex = index;
			if (set.autoChange==true) {
			clearInterval(startRun);//重置自动切换函数
			startRun = setInterval(runNext,set.changeTime);}
			}
	}

	//切换到下一个
	function runNext() {
		index =  (nowIndex+1)%size;
		fadeAB();
	}

	//点击任一图片
		if (set.thumbObj!=null) {
		thumb = $(set.thumbObj);
	//初始化
		thumb.eq(0).addClass(set.thumbNowClass);
			thumb.bind("mousemove",function(event){
				index = thumb.index($(this));
				fadeAB();
				delayRun = setTimeout(fadeAB,set.delayTime);
				clearTimeout(delayRun);
				event.stopPropagation();
			})
		}

	//点击上一个
		if (set.botNext!=null) {
			var botNext = $(set.botNext);
			botNext.mousemove(function () {
				runNext();
				return false;
			});
		}

	//点击下一个
		if (set.botLast!=null) {
			var botLast = $(set.botLast);
			botLast.mousemove(function () {
				index = (nowIndex+size-1)%size;
				fadeAB();
				return false;
		});
		}

	//自动运行
		if (set.autoChange==true) {
		startRun = setInterval(runNext,set.changeTime);
		}

	}

	var $sG = $.sGalleryLong;

	})(jQuery);
function slide(Name,Class,Width,Height,fun){
	$(Name).width(Width);
	$(Name).height(Height);
	if(fun == true){
		$(Name).append('<div class="title-bg"></div><div class="title"></div><div class="change"></div>')
		var atr = $(Name+' div.changeDiv a');
		var sum = atr.length;
		for(i=1;i<=sum;i++){
			var title = atr.eq(i-1).attr("title");
			var href = atr.eq(i-1).attr("href");
			$(Name+' .change').append('<i>'+i+'</i>');
			$(Name+' .title').append('<a href="'+href+'">'+title+'</a>');
		}
		$(Name+' .change i').eq(0).addClass('cur');
	}
	$(Name+' div.changeDiv a').sGallery({//对象指向层，层内包含图片及标题
		titleObj:Name+' div.title a',
		thumbObj:Name+' .change i',
		thumbNowClass:Class
	});
	$(Name+" .title-bg").width(Width);
}
 
/*
 * 登录框 
 * checkbox
 * */

function checkLogin(form){ 
	
	var form = $(form);
	var ouser = form.find("input[name='username']");
	var opwd = form.find("input[name='pwd']");
	var code = form.find("input[name='code']");
	var msg = form.find(".msg");
	var red ="red";
	if(ouser.val().match(/^\w{2,20}$/)== null){  
		msg.text("·请输入2至20位间的用户名").css('color',red);
		ouser.focus();
		return false;
	}  
	if(opwd.val().match(/^\w{1,20}$/)== null){ 
		msg.text("·请输入密码").css('color',red);
		opwd.focus();
		return false;
	} 
	if(code.val().match(/^\w{4}$/)== null){ 
		msg.text("·请输入4位验证码").css('color',red);
		code.focus();
		return false;
	} 
	msg.text("");
	return true;
	
}



