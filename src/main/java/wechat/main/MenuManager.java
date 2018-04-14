package wechat.main;

import com.liyuan.demo.constants.ConstantUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wechat.constants.ConstantsUtil;
import wechat.menu.*;
import wechat.pojo.Token;
import wechat.util.CommonUtil;
import wechat.util.MenuUtil;

/**
 * 菜单管理器类：用于创建菜单
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 *
	 * @return
	 */
	private static Menu getMenu() {

		//第一个按钮：进入商城
		ViewButton btn1 = new ViewButton();
		btn1.setName("进入商城");
		btn1.setType("view");
		btn1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ConstantsUtil.appid+"&redirect_uri="+ ConstantUtils.serverPath+"jh_proj/OAuthServlet&response_type=code&scope=snsapi_userinfo&connect_redirect=1#wechat_redirect");

		//第二组复合按钮
//		ViewButton btn21 = new ViewButton();
//		btn21.setName("定制服务");
//		btn21.setType("view");
//		btn21.setUrl(ConstantUtils.serverPath+"jianghong/message/coming.html");

		ClickButton btn22 = new ClickButton();
		btn22.setName("我的订单");
		btn22.setType("click");
		btn22.setKey("orders");

//		ViewButton btn23 = new ViewButton();
//		btn23.setName("退款退货");
//		btn23.setType("view");
//		btn23.setUrl(ConstantUtils.serverPath+"jianghong/message/coming.html");

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("订单服务");
		mainBtn2.setSub_button(new Button[] {btn22});

		//第三组复合按钮
		ClickButton btn31 = new ClickButton();
		btn31.setName("联系商家");
		btn31.setType("click");
		btn31.setKey("contactus");

		ViewButton btn32 = new ViewButton();
		btn32.setName("活动发布");
		btn32.setType("view");
		btn32.setUrl(ConstantUtils.serverPath+"jianghong/message/coming.html");



		ViewButton btn34 = new ViewButton();
		btn34.setName("科普知识");
		btn34.setType("view");
		btn34.setUrl(ConstantUtils.serverPath+"jianghong/message/coming.html");

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("平台服务");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn34 });

		//创建菜单，添加按钮组
		Menu menu = new Menu();
		menu.setButton(new Button[] {btn1,  mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = ConstantsUtil.appid;
		// 第三方用户唯一凭证密钥
		String appSecret = ConstantsUtil.appsecret;

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		System.out.println(token.getAccessToken());
		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}

		System.out.println(MenuUtil.getMenu(token.getAccessToken()));
	}
}

