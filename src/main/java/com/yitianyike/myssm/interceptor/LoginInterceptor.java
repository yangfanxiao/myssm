package com.yitianyike.myssm.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		//判断当前访问路径是否为登录的路径,如果是则放行
			if(request.getRequestURI().indexOf("/login") > 0){
				return true;
			}
			
			//判断session中是否有登录信息,如果没有则跳转到登录页面,如果有则放行
			HttpSession session = request.getSession();
			if(session.getAttribute("username") != null){
				return true;
			}
//			response.sendError(555);
			request.getRequestDispatcher("login").forward(request, response);   //重定向跳转代码
			return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
