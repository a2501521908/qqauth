package cn.keshaowl.page.login.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.keshaowl.page.login.utils.HttpsUtil;
import cn.keshaowl.page.login.utils.QQAuthUtils;
@WebServlet(name = "DoQLoginServlet", urlPatterns = "/qauth.action")
public class DoQLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获得code
				String code = request.getParameter("code");
				String access_token = "";
				// 获取token
				access_token = QQAuthUtils.getAccessToken(code);
				// 获取带有 openid的 json串
				String openidjson = HttpsUtil.get("https://graph.qq.com/oauth2.0/me?" + access_token);

				// 拿到openid
				int i = openidjson.indexOf("{");
				
				/**
				 * 为什么需要截取?
				 * 需要过滤掉json里的{
				 * 则get报错
				 */
				
				openidjson = openidjson.substring(i);
				JSONObject json = new JSONObject(openidjson.trim());
				String openid = json.getString("openid");

				/**
				 * 获取用户信息
				 */
				JSONObject userInfo = QQAuthUtils.getUserInfo(access_token, openid);
				System.out.println("userInfo=======" + userInfo);
				System.out.println("用户性别" + userInfo.getString("gender"));
				System.out.println("城市" + userInfo.getString("city"));
				System.out.println("昵称" + userInfo.getString("nickname"));
				System.out.println("头像" + userInfo.getString("figureurl_2"));
				System.out.println("出生年:"+userInfo.getString("year"));
				
				response.sendRedirect("success.jsp");
	}
	

}
