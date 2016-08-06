package com.alipay.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.opensymphony.xwork2.ActionSupport;

public class ALIPayAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter out;
	private String id;
	private String totalFee;
	private String payCode;

	private void OutWrite() {
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);

	}

	private String sHtmlText;

	/**
	 * 从页面跳转到此处进行支付宝支付的相关配置
	 */
	public void aliPayMethod() {
		try {

			// 我使用唯一识别码作为订单的商户识别号
			String out_trade_no = payCode;

			// 订单名称，必填
			String subject = payCode;

			// 付款金额，必填
			String total_fee = totalFee;

			// 收银台页面上，商品展示的超链接，必填
			String show_url = "http://blog.csdn.net/qq_30997391";// 欢迎关注博客

			// 商品描述，可空
			String body = "支付宝支付Demo，双面人的网络世界，欢迎关注";

			// AlipayConfig.return_url += "?payCode=" + payCode;

			// ////////////////////////////////////////////////////////////////////////////////

			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", AlipayConfig.service);
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("seller_id", AlipayConfig.seller_id);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", AlipayConfig.payment_type);
			sParaTemp.put("notify_url", AlipayConfig.notify_url);
			sParaTemp.put("return_url", AlipayConfig.return_url);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("show_url", show_url);
			// sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
			sParaTemp.put("body", body);
			// 其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
			// 如sParaTemp.put("参数名","参数值");

			// 建立请求
			setsHtmlText(AlipaySubmit.buildRequest(sParaTemp, "get", "确认"));
			System.out.println(getsHtmlText());
			OutWrite();
			// 此处一定要out.println写出来，写出来的是支付宝的支付页面代码，所以不要讲数据返回到前端页面，将无法处理。
			// 直接在java代码中print出就可以实现跳转。
			out.println(getsHtmlText());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 此处接收支付后的结果回调，我认为这几个内容是需要的，其他内容不太需要所以就没有加。
	 * 之所以在这个类里面可以接收回调是我将回调的方法写成了这个Action，用户可以采用别的类，那么就配置Struts到别的类里面就可以了
	 * 
	 */
	private String trade_status;
	private String is_success;
	private String trade_no;
	private String total_fee;
	private String subject;
	private String out_trade_no;

	/**
	 * 支付宝同步回调，一般会走这个方法
	 * 
	 * @return
	 */
	public String aliBackSame() {
		try {
			if (is_success.equals("T")) {
				// TODO 回调成功，支付成功，执行自己的业务代码。更新订单状态，或者上传支付后的订单号
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 支付宝异步回调
	 * 
	 * @return
	 */
	public String aliBackAsny() {

		try {
			if (is_success.equals("T")) {
				// TODO 回调成功，支付成功，执行自己的业务代码。更新订单状态，或者上传支付后的订单号
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getIs_success() {
		return is_success;
	}

	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	@JSON(serialize = false)
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@JSON(serialize = false)
	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	@JSON(serialize = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JSON(serialize = false)
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getsHtmlText() {
		return sHtmlText;
	}

	public void setsHtmlText(String sHtmlText) {
		this.sHtmlText = sHtmlText;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
}
