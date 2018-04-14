package wechat.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import wechat.constants.ConstantsUtil;
import wechat.util.TokenThread;




/**
 * 中控服务器：实现定时获取凭证access_token,在启动的时候实例化并调用其init()方法
 */
public class InitServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    public void init() throws ServletException
    {
        // 未配置APPID、APPSECRET时不启动线程
        if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret))
        {
            System.out.println("请先配置appID和appSecret...");
        }
        else
        {
            // 启动定时获取access_token的线程
            new Thread(new TokenThread()).start();
        }

        //加载证书
        ConstantsUtil.certPath = this.getClass().getClassLoader().getResource("apiclient_cert.p12").getPath();
        System.out.println("加载证书路径："+ConstantsUtil.certPath);
    }

}

