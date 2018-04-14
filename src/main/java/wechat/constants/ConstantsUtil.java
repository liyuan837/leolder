package wechat.constants;

/**
 * 常量类
 */
public class ConstantsUtil {
    //微信公众号的测试号和密码:肇基的，不用了
//	public static String appid = "wx0a28fa5d48548a77";
//	public static String appsecret = "2627fd9c615c62b67982fa60cdf6f99d";

    //微信公众号的测试号和密码:公司的
//	public static String appid = "wx6b34d63329482365";
//	public static String appsecret = "befe70c4de4a6092b58aa9b034ba7a86";

    //微信公众号的服务号：公司的
    public static String appid = "wxc36868c0d27e9a62";
    public static String appsecret = "44f15beed293a5fd64863fe2e52d506e";

    //挂载项目的腾讯云服务器的ip
    //public static String serverIp = "211.159.216.52";


    /**
     * 商户号码
     */
    public static String mchId = "1467696002";
    /**
     * 支付码
     */
    public static String payKey = "B4s7modIRkmaqhshvSdgdIukorVlv0Yx";

    /**
     * 证书路径
     */
    public static String certPath = "";

    /**
     * 统一下单URL
     */
    public static String unifiedOrderUrl= "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 退款url
     */
    public static String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 支付成功回调地址
     */
    public static String callback = "http://www.dyjkglass.com/jh_proj/order/wechat_notify.action";
}
