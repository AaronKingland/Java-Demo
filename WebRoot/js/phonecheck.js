
function checkMobile(str) {
	if (str == "") {
		alert("手机号不能为空！");
	} else {
		var re = /^1\d{10}$/
		if (re.test(str)) {
			alert("正确");
		} else {
			alert("手机号格式错误！");
		}
	}
}