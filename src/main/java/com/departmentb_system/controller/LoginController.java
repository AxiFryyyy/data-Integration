package com.departmentb_system.controller;

import com.departmentb_system.PO.User;
import com.departmentb_system.dao.UserDao;
import com.departmentb_system.dao.impl.UserImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/login" ,method = RequestMethod.POST )
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        User user = userDao.getUserByUN(username);
        if(user !=null){
            if(password.equals(user.getPassWord())){

            }else{
                model.addAttribute("error", "密碼不正確");
                return "Login";
            }
        }else{
            model.addAttribute("error", "找不到用戶");
            return "Login";
        }
        request.getSession().setAttribute("username", user.getHandler_id());
        return "redirect:/Home";

    }

    @GetMapping("/")
    public String showLoginPage(){
        return "Login";
    }
}
