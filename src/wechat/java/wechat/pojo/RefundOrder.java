package wechat.pojo;
/**
 * 统一下单实体
 *
 * @ClassName: UnifiedOrder
 *
 */
public class RefundOrder {
	/**
	 * 公众账号ID
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mch_id;
	/**
	 * 随机串:随机字符串，长度要求在32位以内,实例：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	 */
	private String nonce_str;

	/**
	 * 签名：通过签名算法计算得出的签名值，详见签名生成算法
	 */
	private String sign;

	/**
	 * 微信交易单号
	 */
	private String transaction_id;

	/**
	 * 商户订单号：商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
	 */
	private String out_trade_no;

	/**
	 * 商户内部退款单号
	 */
	private String out_refund_no;


	/**
	 * 总金额：单位为：分
	 */
	private Integer total_fee;

	/**
	 *退款金额：单位为：分
	 */
	private Integer refund_fee;

	/**
	 *操作人：默认为商户
	 */
	private String op_user_id;

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}




}
