package com.departmentb_system.controller;

import com.departmentb_system.PO.CourseInfo;
import com.departmentb_system.PO.CourseInfoList;
import com.departmentb_system.PO.CoursesChose;
import com.departmentb_system.PO.Student;
import com.departmentb_system.dao.CoursesChoseDao;
import com.departmentb_system.socket.ClientB_Receive_Socket;
import com.departmentb_system.socket.ClientB_Send_Socket;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chooseCourse")
public class ChooseCourseController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    CoursesChoseDao coursesChoseDao;

    @Autowired
    ClientB_Receive_Socket clientB_receive_socket;
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
            boolean flag = courseId.charAt(0) == '1';

            //todo
            /**
             * 当查询课程不在本院系时
             */
            String retMessage = null;
            if (!flag) {
                //todo确定课程编号对应的院系
                String message = "choose B " + courseId ;
                System.out.println(courseId.charAt(0));
                if(courseId.charAt(0) == '5') {
                    message = message + " " + "A";
                }
                else if(courseId.charAt(0) == '2') {
                    message = message + " " + "C";
                }
                else {
                    message = message + " " + "D";
                }
                ClientB_Send_Socket clientB_send_socket = new ClientB_Send_Socket();
                clientB_send_socket.sendMessage(message);


                ClientB_Receive_Socket clientB_receive_socket = this.clientB_receive_socket;
                /**Thread thread2 = new Thread(clientB_receive_socket);
                thread2.start();

                try {
                    Thread.sleep(10000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try{
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }**/
                retMessage = clientB_receive_socket.getRetMessage();
                if(retMessage.equals("choose success")){

                }
            }
            return ResponseEntity.ok("选課成功!");
        }
        return ResponseEntity.badRequest().body("选課失败");
    }

}
