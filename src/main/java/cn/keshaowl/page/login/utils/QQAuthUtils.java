package cn.keshaowl.page.login.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * QQ登陆的处理返回信息处理工具类
 * 让代码更简洁
 * @author shuaike
 *
 */
public class QQAuthUtils {
	
	/**
	 * 需要配置你的应用信息
	 * https://connect.qq.com/manage.html#/ 查看地址
	 * 参数说明:
	 * APPID: 应用的id
	 * APPKEY： 应用的密钥
	 * REDIRECT_URI： 用户登陆成功的回调地址
	 */
	private final static String APPID = "";
	private final static String APPKEY = "";
	private final static String GET_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
	private final static String REDIRECT_URI = "";
	private final static String GET_USER_INFO = "https://graph.qq.com/user/get_user_info";
	
	/**
	 * 获取AccessToken
	 * 
	 * @throws JSONException
	 */
	public static  String getAccessToken(String code) throws JSONException {
		StringBuilder sb = new StringBuilder();
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=" + APPID);
		sb.append("&client_secret=" + APPKEY);
		sb.append("&redirect_uri=" + REDIRECT_URI);
		sb.append("&code=" + code);
		String result = HttpsUtil.post(GET_TOKEN_URL, sb.toString());
		/**
		 * 返回数据 { "access_token": "ACCESS_TOKEN", "expires_in": 1234,
		 * "remind_in":"798114", "uid":"12341234" }
		 */
		return result;
	}
	

	/**
	 * 获取用户信息
	 * 
	 * @param access_token
	 * @param uid
	 *            查询的用户ID
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getUserInfo(String access_token, String uid) throws JSONException {
		StringBuilder sb = new StringBuilder();
		sb.append("?" + access_token);
		sb.append("&openid=" + uid);
		sb.append("&oauth_consumer_key="+APPID);
		String result = HttpsUtil.get(GET_USER_INFO + sb.toString());
		// 返回参数：查看 http://wiki.connect.qq.com/get_user_info
		JSONObject json = new JSONObject(result);
		return json;
	}
}
