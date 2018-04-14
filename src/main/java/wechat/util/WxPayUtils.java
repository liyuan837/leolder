package wechat.util;

import wechat.constants.ConstantsUtil;
import wechat.pojo.JsAPIConfig;
import wechat.pojo.UnifiedOrder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class WxPayUtils {
	/**
	 * 统一下单
	 *
	 * @Title: unifiedOrder
	 * @Description: TODO
	 * @param: @param openId 微信用户openId
	 * @param: @param order 系统订单信息
	 * @param: @param callbackUrl 回调路径
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String  prepay_id
	 */
//	public static JsAPIConfig unifiedOrder(HttpServletRequest request, String openId, Order order,
//										   String callbackUrl) throws Exception {
//		//[1]创建统一订单对象
//		UnifiedOrder unifiedOrder = new UnifiedOrder();
//		unifiedOrder.setAppid(ConstantsUtil.appid);
//
//		unifiedOrder.setBody("desc");
//		unifiedOrder.setMch_id(ConstantsUtil.mchId);
//
//		String nonce = UUID.randomUUID().toString().replace("-", "").substring(0,32);
//		unifiedOrder.setNonce_str(nonce);
//		unifiedOrder.setNotify_url(callbackUrl);
//
//		unifiedOrder.setOpenid(openId);
//		unifiedOrder.setOut_trade_no(order.getOrdercode());
//
//		//服务器端ip
//		unifiedOrder.setSpbill_create_ip(getRemoteIpAddr(request));
//
//		//测试付款：1分
//		unifiedOrder.setTotal_fee((int)(order.getTotalprice()*100));
//
//		//生成统一订单签名
//		String sign = createSign(unifiedOrder);
//		unifiedOrder.setSign(sign);
//
//		//[2]转化成xml
//		String xml = MessageUtil.messageToXml(unifiedOrder).replace("__", "_");
//
//		System.out.println("统一下单XML："+xml);
//
//		//[3]统一下单，并返回获取prepay_id:
//		String resultXml = httpsRequest(ConstantsUtil.unifiedOrderUrl,xml);
//
//		System.out.println("统一下单返回结果"+resultXml);
//
//		//[4]解析返回数据
//		Map<String,String> resultMap = MessageUtil.parseXml(resultXml);
//
//		if(resultMap.get("return_code").equals("SUCCESS")){
//			//通信成功
//			//System.out.println("通信成功，提示信息："+resultMap.get("return_msg"));
//
//			if(resultMap.get("result_code").equals("SUCCESS")){
//				//统一下单成功,生成JSAPIconfig对象
//				System.out.println("统一下单成功，提示信息："+resultMap.get("return_msg"));
//				return createPayConfig(resultMap,unifiedOrder);
//
//			}else{
//				//统一下单失败
//				//System.out.println("统一下单失败，错误信息："+resultMap.get("return_msg"));
//				JsAPIConfig config = new JsAPIConfig();
//				config.setResult_msg("unifiedOrder_fail");
//				return config;
//			}
//
//		}else
//		{
//			//通信失败
//			//System.out.println("通信失败，错误信息："+resultMap.get("return_msg"));
//			JsAPIConfig config = new JsAPIConfig();
//			config.setResult_msg("unifiedOrder_fail");
//			return config;
//
//		}
//
//
//
//	}

	/**
	 * 生成支付配置
	 * @Title: createPayConfig
	 * @Description: TODO
	 * @param @param preayId 统一下单prepay_id
	 * @param @return
	 * @param @throws Exception
	 * @return JsAPIConfig
	 * @throws
	 */
	public static JsAPIConfig createPayConfig(Map<String,String> resultMap,UnifiedOrder unifiedOrder) throws Exception {
		JsAPIConfig config = new JsAPIConfig();
		config.setResult_msg("SUCCESS");

		String nonce = UUID.randomUUID().toString().replace("-", "").substring(0,32);
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String packageName = "prepay_id="+resultMap.get("prepay_id");

		config.setAppId(ConstantsUtil.appid);
		config.setNonce(nonce);
		config.setTimestamp(timestamp);
		config.setPackageName(packageName);

		String signature = createSignature(config);

		config.setSignature(signature);

		return config;
	}



	//获取客户端真实的IP
	public static String getRemoteIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	//生成统一下单sign
	private static String createSign(UnifiedOrder unifiedOrder){
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		/**
		 * 组装请求参数
		 * 按照ASCII排序
		 */
		parameters.put("appid",unifiedOrder.getAppid());
		parameters.put("body", unifiedOrder.getBody());
		parameters.put("mch_id", unifiedOrder.getMch_id());
		parameters.put("nonce_str", unifiedOrder.getNonce_str());
		parameters.put("out_trade_no", unifiedOrder.getOut_trade_no());
		parameters.put("notify_url", unifiedOrder.getNotify_url());
		parameters.put("spbill_create_ip", unifiedOrder.getSpbill_create_ip());
		parameters.put("total_fee",unifiedOrder.getTotal_fee());
		parameters.put("trade_type",unifiedOrder.getTrade_type());
		parameters.put("openid", unifiedOrder.getOpenid());

		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + ConstantsUtil.payKey);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;

	}

	//生成调起支付的signature
	private static String createSignature(JsAPIConfig config){
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		/**
		 * 组装请求参数
		 * 按照ASCII排序
		 */
		parameters.put("appId",ConstantsUtil.appid);
		parameters.put("nonceStr",config.getNonce());
		parameters.put("package", config.getPackageName());
		parameters.put("signType", config.getSignType());
		parameters.put("timeStamp", config.getTimestamp());

		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + ConstantsUtil.payKey);
		String signature = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return signature;

	}


	public static String httpsRequest(String requestUrl, String xmlStr) {
		try{
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");


			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(xmlStr.getBytes("UTF-8"));
			outputStream.close();

			// 从输入流读取返回内容
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			connection.disconnect();
			return buffer.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return "";
	}


}
