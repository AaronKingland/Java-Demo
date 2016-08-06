var accessToken = "";
var ticket = "";
$
		.ajax({
			url : 'https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb9ad6c0c319cf8c2&secret=15beb7fbf88a6aa1d42149259e9f853a',
			type : 'GET',
			cache : true,
			data : "{}",
			async : false,
			dataType : 'text',
			success : function(data) {
				ajaxobj = eval("(" + data + ")");
				accessToken = data.access_token
				$
						.ajax({
							url : 'https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token='
									+ accessToken + '&type=jsapi',
							type : 'GET',
							cache : true,
							data : "{}",
							async : false,
							dataType : 'text',
							success : function(data) {
								ajaxobj = eval("(" + data + ")");
								ticket = data.ticket
							}
						});
			}
		});

var time = new Date().getTime();
var url = window.location.href;
var string = "jsapi_ticket=" + ticket + "&noncestr+" + time + "&timestamp="
		+ time + "&url=" + url;
var sign = hex_sha1(string);

wx.config({
	debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	appId : 'wxb9ad6c0c319cf8c2', // 必填，公众号的唯一标识
	timestamp : time, // 必填，生成签名的时间戳
	nonceStr : time, // 必填，生成签名的随机串
	signature : sign,// 必填，签名，见附录1
	jsApiList : [ 'chooseWXPay' ]
// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});