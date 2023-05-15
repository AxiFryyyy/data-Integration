package com.server.integratedserver.controller;

import com.server.integratedserver.service.XmlService;
import com.server.integratedserver.webSocket.Server_Socket;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Home")
public class HomeController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Server_Socket serverSocket;

    @Autowired
    private XmlService xmlService;
    private static Model model;
    private String output;

    @GetMapping
    public String getHome(Model model){
        HomeController.model = model;
        output += "服务端已启动，等待WebSocket客户端连接...\n";
        request.getSession().setAttribute("output", output);
        model.addAttribute("output", output);
        return "Home";
    }

    @GetMapping("/collect/{where}")
    public String collect(Model model, @PathVariable String where){
        serverSocket.actionPerformed("collect " + where);

        model.addAttribute("output", output);
        return "Home";
    }



    @GetMapping("/table")
    public ModelAndView tablePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ShowTable");
        List<List<Map<String, String>>> xmlDataList = new ArrayList<>();
        xmlDataList.add(xmlService.fetchClassesData());
        xmlDataList.add(xmlService.fetchStudentsData());
        xmlDataList.add(xmlService.fetchChoicesData());
        modelAndView.addObject("xmlDataList", xmlDataList);
        System.out.println(xmlDataList);
        return modelAndView;
    }

    public void addString(String str){
        output += str + "/n";
        getHome(model);
    }
}
