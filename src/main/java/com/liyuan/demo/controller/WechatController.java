package com.liyuan.demo.controller;

import com.liyuan.demo.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wechat.util.SignUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/wechat")
public class WechatController {

	@Autowired
	private CoreService coreService;

	@RequestMapping("/doProcess")
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String method = request.getMethod();

		if (method.equals("GET")) {
			// 对微信后台的验证
			verifyWechat(request, response);
		} else {
			
			// 调用核心业务类接收消息、处理消息
			String respXml = coreService.processRequest(request);

			// 响应消息
			PrintWriter out = response.getWriter();
			out.print(respXml);
			out.close();
		}

	}

	// 对微信后台的验证
	private void verifyWechat(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		signature = new String(signature.getBytes("ISO8859-1"),"UTF-8");
		
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		timestamp = new String(timestamp.getBytes("ISO8859-1"),"UTF-8");
		
		// 随机数
		String nonce = request.getParameter("nonce");
		nonce = new String(nonce.getBytes("ISO8859-1"),"UTF-8");
		
		// 随机字符串
		String echostr = request.getParameter("echostr");
		echostr = new String(echostr.getBytes("ISO8859-1"),"UTF-8");

		PrintWriter out = response.getWriter();
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
}
