package com.yitianyike.myssm.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yitianyike.myssm.entity.UserPojo;
import com.yitianyike.myssm.service.UserService;
import com.yitianyike.myssm.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userServiceImpl;
	//直接返回String类型的 @RequestMapping里加上produces="text/html;charset=UTF-8"
	 @SuppressWarnings("null")
	@RequestMapping(value = "/create", method ={RequestMethod.POST}, produces="text/html;charset=UTF-8")
	 @ResponseBody
	  public String insert(UserPojo user,HttpServletResponse response){
		 List<String> password = userServiceImpl.getPassWord(user.getName());
		 if (password == null && password.get(0) == null) {
			 userServiceImpl.insertUser(user);
			 return "注册成功";
		}else{
			return "该用户名已存在";
		}
	  }
	 @ResponseBody
	 @RequestMapping(value = "/login", method ={RequestMethod.GET}, produces="text/html;charset=UTF-8")
	 public String login(HttpServletRequest request,UserPojo user){
		 List<String> password = userServiceImpl.getPassWord(user.getName());
		 System.out.println("thread[" + Thread.currentThread().getName()+"]sn[" + "login" + "]");
		 if (password.isEmpty()) {
			 return "用户不存在";
		}
		 if (password.get(0).equals(user.getPassword())) {
			 HttpSession session = request.getSession();
			Boolean isNew = session.isNew();
				//判断用户名密码的正确性,如果正确则将登录信息放入session中
			session.setAttribute("username", user.getName());
			 return "登陆成功";
		}else{
			 return "密码错误";
		}
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "/logout", method ={RequestMethod.GET},produces="text/html;charset=UTF-8")
	 public String logout(HttpServletRequest request,UserPojo user){
			 HttpSession session = request.getSession();
			 session.invalidate();  //让session失效， 下次请求会重新创建session
			 //session.setMaxInactiveInterval(interval)  //方法体内的参数interval为秒   优先级高于web.xml配置的
			//给username赋值为空 拦截器会认为没有登陆  但下次请求用的session是同一个
//			session.setAttribute("username", null);   //两个只能用一个  
			 System.out.println("thread[" + Thread.currentThread().getName()+"]logout[" + "aaaaaa" + "]");
			 return "登出成功";
	 }
}
