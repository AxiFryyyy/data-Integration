package com.departmentb_system.controller;

import com.departmentb_system.PO.User;
import com.departmentb_system.dao.UserDao;
import com.departmentb_system.socket.ClientB_Socket;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Home")
public class HomeController {
    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("username",username());

        return "Home";
    }
    @ModelAttribute(name="username")
    public String username(){
        String user = (String) request.getSession().getAttribute("username");
        return user;
    }
}
