package com.departmentb_system.controller;

import com.departmentb_system.server.XmlServer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;

@Controller
@RequestMapping("/course-manage")
public class CourseManageController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private XmlServer xmlServer;

    @GetMapping()
    public String showManagePage(){
        return "CourseManagePage";
    }

    @GetMapping("/getShare")
    public ResponseEntity<String> getSharedCourse(){
        return ResponseEntity.ok("獲取成功");
    }

    @PostMapping("/transferShare")
    public ResponseEntity<Document> transferMyShareCourse(){
        Document doc = xmlServer.getTheShareCourse();
        return ResponseEntity.ok(doc);
    }
}
