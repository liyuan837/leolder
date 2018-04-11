package wechat.util;

import wechat.constants.ConstantsUtil;
import wechat.pojo.Token;




/**
 * 线程，获取accessToken
 * 用于保存获取到的access_token和jsapiTicket
 */
public class TokenThread implements Runnable
{
    public static String appid = ConstantsUtil.appid;
    public static String appsecret = ConstantsUtil.appsecret;
    public static Token accessToken = null;
    public static String jsapiTicket = null;

    //两小时运行一次
    public void run()
    {
        while (true)
        {
            try
            {
                //先获取accessToken
                accessToken =CommonUtil.getToken(appid, appsecret);
                System.out.println("获取到accessToken:"+accessToken);
                if (null != accessToken)
                {
                    //通过凭证accessToken调用微信API接口，来获取jsapi的ticket
                    jsapiTicket = CommonUtil.getJSApiTicket(accessToken.getAccessToken());
                    if (null != jsapiTicket)
                    {
                        // 休眠7000秒：如果获取成功，则7000s内不需要在获取
                        System.out.println("获取到jsapiTicket:"+jsapiTicket);
                        Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
                    }
                    else
                    {
                        //如果获取失败，则60s后再次尝试获取
                        Thread.sleep(60 * 1000);
                    }
                }
                else
                {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            }
            catch (InterruptedException e)
            {
                try
                {
                    Thread.sleep(60 * 1000);
                }
                catch (InterruptedException e1)
                {

                }
            }
        }
    }

}
