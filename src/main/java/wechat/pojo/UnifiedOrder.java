package wechat.pojo;
/**
 * 统一下单实体
 *
 * @ClassName: UnifiedOrder
 *
 */
public class UnifiedOrder {
    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 附加数据(说明)
     */
    private String attach;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 随机串:随机字符串，长度要求在32位以内,实例：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
     */
    private String nonce_str;
    /**
     * 通知地址
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    private String notify_url;
    /**
     * 用户标识
     * trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
     */
    private String openid;
    /**
     * 商户订单号：商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    private String out_trade_no;
    /**
     * 终端IP（用户）
     * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
     */
    private String spbill_create_ip;
    /**
     * 总金额：单位为：分
     */
    private Integer total_fee;
    /**
     * 交易类型
     * 取值如下：JSAPI，NATIVE，APP等，说明详见参数规定
     */
    private String trade_type;
    /**
     * 签名：通过签名算法计算得出的签名值，详见签名生成算法
     */
    private String sign;
    /**
     * WEB
     */
    private String device_info;

    public UnifiedOrder(){
        this.trade_type = "JSAPI";
        //   this.device_info = "WEB";
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }
}
