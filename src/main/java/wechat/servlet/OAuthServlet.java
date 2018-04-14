package wechat.servlet;

import com.liyuan.demo.constants.ConstantUtils;
import wechat.constants.ConstantsUtil;
import wechat.pojo.SNSUserInfo;
import wechat.pojo.WeixinOauth2Token;
import wechat.util.AdvancedUtil;
import wechat.util.NicknameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;


/**
 * 授权后的回调请求处理:微信网页授权，获取微信用户信息
 *https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0a28fa5d48548a77&redirect_uri=http://www.liyuanwechat.cn/jh_proj/OAuthServlet&response_type=code&scope=snsapi_userinfo&connect_redirect=1#wechat_redirect
 */
public class OAuthServlet extends HttpServlet {
    private static final long serialVersionUID = -1847238807216447030L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

//        HeroDao heroDao = (HeroDao)SpringUtils.getBean("heroDao");
//        System.out.println(heroDao.queryAll().size());

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        // 用户同意授权
        if (!"authdeny".equals(code)) {
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(ConstantsUtil.appid, ConstantsUtil.appsecret, code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);

            System.out.println("处理前："+snsUserInfo.getNickname());
            //对微信用户名带有特殊表情的用户名进行处理
            String oldnickname = snsUserInfo.getNickname();
            snsUserInfo.setNickname(NicknameUtils.filterEmoji(oldnickname));
            System.out.println("处理后："+snsUserInfo.getNickname());

            //查看该用户是否关注了我们的公众号

            String nickname = snsUserInfo.getNickname();
            nickname = string2Unicode(nickname);
            System.out.println("nickname:"+nickname);
            System.out.println("header:"+snsUserInfo.getHeadImgUrl());
            response.sendRedirect(ConstantUtils.serverPath+"jianghong/index.html?openid="+snsUserInfo.getOpenId()+"&nickname="+URLEncoder.encode(nickname)+"&headimgurl="+snsUserInfo.getHeadImgUrl()+"&timestamp="+(new Date()).getTime());
//            response.sendRedirect(ConstantUtils.serverPath+"jianghong/index.html?openid=111"+"&nickname="+"21"+"&headimgurl="+snsUserInfo.getHeadImgUrl()+"&timestamp="+(new Date()).getTime());

        }

    }

    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    public static String dealFaceImg(String oldStr){
        String newStr = new String();
        for(int i=0;i<oldStr.length();){
            if(newStr.indexOf(i) == '\\' && (newStr.indexOf(i+1) == 'x'||newStr.indexOf(i+1) == 'X')){
                newStr += "*";
                i += 4;
                System.out.println("找到不明字符");
            }else{
                newStr += oldStr.indexOf(i);
                i += 1;
            }

        }

        return newStr;
    }
}
