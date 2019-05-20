package cn.keshaowl.page.login.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="SendQLoginServlet",urlPatterns="/sendQLogin.action")
public class SendQLoginServlet extends HttpServlet {

	
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
	private final static String REDIRECT_URI = "";
	private final static String STATE = "register";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义发起的url
		String url="https://graph.qq.com/oauth2.0/show?which=Login&display=pc&client_id="+APPID
				+"&redirect_uri="+REDIRECT_URI+
				"&response_type=code&state="+STATE;
		//重定向
		response.sendRedirect(url);
	}

}
