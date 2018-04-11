package wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import wechat.constants.ConstantsUtil;
import wechat.util.Sha1Util;
import wechat.util.SignUtil;
import wechat.util.TokenThread;

/**
 * 请求获取Jsapi_ticket，以求在页面上调用微信js
 */
public class JSApiServlet extends HttpServlet {
    private static final long serialVersionUID = 4440739483644821986L;

    /**
     * 请求校验（确认请求来自微信服务器）
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        getConfig(request, response);
    }

    public void getConfig(HttpServletRequest request,HttpServletResponse response){
        //获取appId
        String appid = ConstantsUtil.appid;

        //获取页面路径(前端获取时采用location.href.split('#')[0]获取url)
        String url = request.getParameter("url");
        //获取access_token
        //String access_token = CommonUtil.getToken(appid, appsecret).getAccessToken();

        //获取ticket
        //String jsapi_ticket = CommonUtil.getJSApiTicket(access_token);
        String jsapi_ticket = TokenThread.jsapiTicket;

        //获取Unix时间戳(java时间戳为13位,所以要截取最后3位,保留前10位)
        String timeStamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        //创建有序的Map用于创建签名串
        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("jsapi_ticket", jsapi_ticket);
        params.put("timestamp", timeStamp);

        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        params.put("noncestr", uuid);
        params.put("url", url);

        //签名
        String signature = "";
        try {
            signature = Sha1Util.createSHA1Sign(params);
        }catch (Exception e) {
            e.printStackTrace();
        }
        //得到签名再组装到Map里
        params.put("signature", signature);
        //传入对应的appId
        params.put("appId", appid);
        //组装完毕，回传
        try {
            JSONArray jsonArray = JSONArray.fromObject(params);
            // System.out.println(jsonArray.toString());
            PrintWriter out = response.getWriter();
            out.print(jsonArray);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
