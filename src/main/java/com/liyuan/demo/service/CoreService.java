package com.liyuan.demo.service;

import wechat.message.resp.TextMessage;
import wechat.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 核心服务类
 */
public class CoreService {

	/**
	 * 处理微信发来的请求
	 *
	 * @param request
	 * @return xml
	 */
	public String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respXml = dealSubscribe(fromUserName, toUserName);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					respXml = dealUnSubscribe(fromUserName, toUserName);
				}// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应

				}
			}
			// 当用户发消息时
			else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	// 处理用户订阅消息：返回一条欢迎订阅消息+将该用户加入到数据库中
	private String dealSubscribe(String fromUserName, String toUserName)
			throws Exception {
		// [1]将用户保存至数据库

		// [2]返回一条欢迎消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent("欢迎进入丹阳江科光学眼镜科技有限公司");

		String respXml = MessageUtil.messageToXml(textMessage);
		return respXml;
	}

	// 处理用户取消订阅消息：返回一条取消订阅消息+将该用户从数据库中删除
	private String dealUnSubscribe(String fromUserName, String toUserName)
			throws Exception {
		// [1]取消关注

		// [2]返回一条再见消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent("再见");

		String respXml = MessageUtil.messageToXml(textMessage);
		return respXml;
	}


}
