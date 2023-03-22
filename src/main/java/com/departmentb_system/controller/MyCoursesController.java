package com.departmentb_system.controller;

import com.departmentb_system.PO.CoursesChose;
import com.departmentb_system.dao.CoursesChoseDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/myCourses")
public class MyCoursesController {

    @Autowired
    CoursesChoseDao coursesChoseDao;
    @Autowired
    HttpServletRequest request;


    @GetMapping
    public String getCourseChoseList(Model model){
        List<CoursesChose> coursesChoseList = coursesChoseDao.getCoursesChose((String) request.getSession().getAttribute("username"));
        if(coursesChoseList.size() == 0){
            return "myCourses";
        }
        model.addAttribute("coursechose",coursesChoseList);

        return "myCourses";
    }

    @PostMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourseChose(@PathVariable String courseId){
        return ResponseEntity.ok("刪除成功!");
    }
}
