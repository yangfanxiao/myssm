package com.yitianyike.myssm.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

	    @RequestMapping("login")
	    public String toIndex(HttpServletRequest request, Model model) {
//	        String id=request.getParameter("id");
//	        int vid=Integer.parseInt(id);
//	        User user = userModel.getUserById(vid);
	        model.addAttribute("user", "liming");
	        return "showUser";
	    }
}
