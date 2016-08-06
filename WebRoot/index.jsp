<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>支付宝支付</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
	jQuery(function() {
		/**
		 *本页面的支付金额及业务识别码可通过上一个自己的支付订单页面来获取到。
		 *payCode 支付唯一识别码，这个根据自己的业务需求来做。一般支付的话有一个唯一识别会方便一些
		 *totalFee 支付总金额，支付宝以“元”为单位，所以如果为一分，即可表示为“0.01”
		 *
		 */
		$("#ali-pay")
				.click(
						function() {
							var payCode = $(".payCode").val();
							var fee = $(".pay-fee").val();
							window.location.href = "/ALIPay/AliPayMethod.action?payCode="
									+ payCode + "&totalFee=" + fee;

						});
	});
</script>
</head>

<body>
	<div id="pay-amount">

		支付金额：￥ <span id="totalFee">0.01</span> <input class="payCode"
			value="0.01" type="hidden" /> <input class="pay-fee"
			value="281721402382772392" type="hidden" />
	</div>

	<div id="pay-choose">
		<div class="radio">
			<div class="pay-label" id="ali-pay">
				<div class="icon zhifubao-icon"></div>
				<div class="pay-title">支付宝支付</div>
				<div class="radio"></div>
			</div>
		</div>
	</div>
	<div id="ali-div"></div>
</body>
</html>
