package wechat.util;

public class CertHttpUtil {
//
//	private static int socketTimeout = 10000;// 连接超时时间，默认10秒
//	private static int connectTimeout = 30000;// 传输超时时间，默认30秒
//	private static RequestConfig requestConfig;// 请求器的配置
//	private static CloseableHttpClient httpClient;// HTTP请求器
//
//	public static Map<String,String> refundOrder(Order order) throws Exception{
//		//创建退款订单
//		RefundOrder refundOrder = new RefundOrder();
//		refundOrder.setAppid(ConstantsUtil.appid);
//		refundOrder.setMch_id(ConstantsUtil.mchId);
//		refundOrder.setNonce_str(UUID.randomUUID().toString().replace("-", "").substring(0,32));
//		refundOrder.setOut_refund_no(ConstantsUtil.mchId);
//		refundOrder.setOut_refund_no(order.getBackcode());
//		refundOrder.setOut_trade_no(order.getOrdercode());
//		refundOrder.setTotal_fee((int) (1));
//		refundOrder.setRefund_fee((int) (1));
//		refundOrder.setOp_user_id(ConstantsUtil.mchId);
//		refundOrder.setSign(createSign(refundOrder));
//
//		//将集合转换成xml
//		String requestXML =MessageUtil.messageToXml(refundOrder).replace("__", "_");
//		System.out.println("请求："+requestXML);
//		//带证书的post
////		String resXml = CertHttpUtil.postData(ConstantsUtil.refundUrl, requestXML);
//		String resXml = postData(ConstantsUtil.refundUrl, requestXML);
//		System.out.println("结果："+resXml);
//		//解析xml为集合，请打断点查看resXml详细信息
//		Map<String,String> resultMap = MessageUtil.parseXml(resXml);
//
//		return resultMap;
//	}
//
//	/**
//	 * 通过Https往API post xml数据
//	 *
//	 * @param url
//	 *            API地址
//	 * @param xmlObj
//	 *            要提交的XML数据对象
//	 * @return
//	 * @throws Exception
//	 */
//	public static String postData(String url, String xmlObj) throws Exception {
//		// 加载证书
//		try {
//			initCert();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String result = null;
//		HttpPost httpPost = new HttpPost(url);
//		// 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
//		StringEntity postEntity = new StringEntity(xmlObj, "UTF-8");
//		httpPost.addHeader("Content-Type", "text/xml");
//		httpPost.setEntity(postEntity);
//		// 根据默认超时限制初始化requestConfig
//		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
//				.setConnectTimeout(connectTimeout).build();
//		// 设置请求器的配置
//		httpPost.setConfig(requestConfig);
//		try {
//			HttpResponse response = null;
//			try {
//				response = httpClient.execute(httpPost);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			HttpEntity entity = response.getEntity();
//			try {
//				result = EntityUtils.toString(entity, "UTF-8");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} finally {
//			httpPost.abort();
//		}
//		return result;
//	}
//
//	/**
//	 * 加载证书
//	 *
//	 */
//	private static void initCert() throws Exception {
//		// 证书密码，默认为商户ID
//		String key = ConstantsUtil.mchId;
//		// 证书的路径
//		String path = ConstantsUtil.certPath;
//		// 指定读取证书格式为PKCS12
//		KeyStore keyStore = KeyStore.getInstance("PKCS12");
//		// 读取本机存放的PKCS12证书文件
//		FileInputStream instream = new FileInputStream(new File(path));
//		try {
//			// 指定PKCS12的密码(商户ID)
//			keyStore.load(instream, key.toCharArray());
//		} finally {
//			instream.close();
//		}
//		SSLContext sslcontext = SSLContexts.custom()
//				.loadKeyMaterial(keyStore, key.toCharArray()).build();
//		// 指定TLS版本
//		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//				sslcontext, new String[] { "TLSv1" }, null,
//				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//		// 设置httpclient的SSLSocketFactory
//		httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//	}
//
//	// 生成退款单sign
//	private static String createSign(RefundOrder refundOrder) {
//		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
//		/**
//		 * 组装请求参数 按照ASCII排序
//		 */
//		parameters.put("appid", refundOrder.getAppid());
//		parameters.put("mch_id", refundOrder.getMch_id());
//		parameters.put("nonce_str", refundOrder.getNonce_str());
//		parameters.put("out_trade_no", refundOrder.getOut_trade_no());
//		parameters.put("out_refund_no", refundOrder.getOut_refund_no());
//		parameters.put("total_fee", refundOrder.getTotal_fee());
//		parameters.put("refund_fee",refundOrder.getRefund_fee());
//		parameters.put("op_user_id", refundOrder.getOp_user_id());
//
//		StringBuffer sb = new StringBuffer();
//		Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
//		Iterator it = es.iterator();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String k = (String) entry.getKey();
//			Object v = entry.getValue();
//			if (null != v && !"".equals(v) && !"sign".equals(k)
//					&& !"key".equals(k)) {
//				sb.append(k + "=" + v + "&");
//			}
//		}
//		sb.append("key=" + ConstantsUtil.payKey);
//		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
//		return sign;
//
//	}
}