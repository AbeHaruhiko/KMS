package jp.caliconography.kms.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.caliconography.kms.model.Member;
import jp.caliconography.kms.service.MemberManagerService;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AuthFilter implements Filter {

	private String[] excludes;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		String thisURL = ((HttpServletRequest) request).getRequestURI();
		Principal principal = ((HttpServletRequest) request).getUserPrincipal();
		HttpSession session = ((HttpServletRequest) request).getSession();

		if (principal == null) {
			if (!isExcludePath(thisURL)) {

				 // ログイン用のGoogle AccountsのURLを取得します
				 session.removeAttribute("logoutUrlPath");
				// ユーザアカウント関連情報
				session.removeAttribute("loginUserInfo");
			}
		} else {
			MemberManagerService service = new MemberManagerService();
			Member memberInfo = service.getMember(userService.getCurrentUser().getEmail());
//			if (!userService.isUserAdmin() && memberInfo == null) {
			if (memberInfo == null && !Arrays.asList("/", "/index.jsp").contains(((HttpServletRequest) request).getRequestURI())) {
				((HttpServletResponse) response).sendRedirect(userService.createLogoutURL("/"));
				return;
			}

			// ログアウト用のGoogle AccountsのURLを取得します
			session.setAttribute("logoutUrlPath", userService.createLogoutURL("/"));
			// ユーザアカウント関連情報
			session.setAttribute("loginUserInfo", userService.getCurrentUser());
			// 里メンバー情報
			session.setAttribute("loginMemberInfo", memberInfo);
		}
		chain.doFilter(request, response);
	}

	/**
	 * リクエストされたURLが除外対象か判断する。 除外対象の場合trueを返す
	 * 
	 * @param thisURL
	 * @return
	 */
	private boolean isExcludePath(String thisURL) {
		if (this.excludes == null)
			return false;
		String[] excludes = this.excludes;
		for (String path : excludes) {
			// 除外対象パスの最後が「*」の場合、indexOfで含まれるか確認
			if (path.indexOf("*") == path.length() - 1) {
				if (thisURL.indexOf(path.substring(0, path.length() - 2)) >= 0) {
					return true;
				}
			} else {
				// 上記以外は、完全一致
				if (thisURL.equals(path)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String exclude = config.getInitParameter("exclude");
		if (exclude == null || "".equals(exclude))
			return;
		this.excludes = exclude.split(",");
	}
}