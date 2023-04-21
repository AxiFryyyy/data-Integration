package com.departmentb_system.controller;

import com.departmentb_system.PO.CourseInfo;
import com.departmentb_system.dao.CoursesManageDao;
import com.departmentb_system.server.XmlServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;

import java.util.List;

@Controller
@RequestMapping("/account-manage")
public class AccountManageController {
    @Autowired
    private CoursesManageDao coursesManageDao;

    @Autowired
    private XmlServer xmlServer;

    @GetMapping()
    public String showManagePage(Model model){
        List<CourseInfo> courseInfoListList = coursesManageDao.getAllCoursesInfo();
        model.addAttribute("coursesInfo", courseInfoListList);
        return "UserManagePage";
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
    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestParam("name") String name,
                                            @RequestParam("hours") int hours,
                                            @RequestParam("credits") int credits,
                                            @RequestParam("teacher") String teacher,
                                            @RequestParam("location") String location,
                                            @RequestParam(value = "shared", defaultValue = "false") boolean shared){
        if(coursesManageDao.addCourse(name,hours,credits,teacher,location,shared)){
            return ResponseEntity.ok("添加成功");
        }
        return ResponseEntity.ok("添加失败");
    }
    @PostMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable String courseId){
        return ResponseEntity.ok("刪除成功");
    }
}
