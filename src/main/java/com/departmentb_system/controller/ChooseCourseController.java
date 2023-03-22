package com.departmentb_system.controller;

import com.departmentb_system.PO.CourseInfo;
import com.departmentb_system.PO.CourseInfoList;
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
@RequestMapping("/chooseCourse")
public class ChooseCourseController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    CoursesChoseDao coursesChoseDao;
    @GetMapping
    public String showAllCourse(Model model){
        model.addAttribute("username", request.getSession().getAttribute("username"));
        List<CourseInfo> courseInfoListList = coursesChoseDao.getAllCoursesInfo();
        model.addAttribute("coursesInfo", courseInfoListList);
        return "CourseChosePage";
    }
    @PostMapping("/choose/{courseId}")
    public ResponseEntity<String> chooseCourse(@PathVariable String courseId, @RequestBody String studentID){

        Boolean succeed = coursesChoseDao.chooseCourse(courseId,studentID);
        if(succeed){
            return ResponseEntity.ok("选課成功!");
        }
        return ResponseEntity.badRequest().body("选課失败");
    }

}
