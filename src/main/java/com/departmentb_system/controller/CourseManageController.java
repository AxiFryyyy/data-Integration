package com.departmentb_system.controller;

import com.departmentb_system.PO.CourseInfo;
import com.departmentb_system.dao.CoursesManageDao;
import com.departmentb_system.server.XmlServer;
import com.departmentb_system.socket.ClientB_Send_Socket;
import com.departmentb_system.socket.ClientB_Socket;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/course-manage")
public class CourseManageController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CoursesManageDao coursesManageDao;

    @Autowired
    private XmlServer xmlServer;

    @GetMapping()
    public String showManagePage(Model model){
        List<CourseInfo> courseInfoListList = coursesManageDao.getAllCoursesInfo();
        model.addAttribute("coursesInfo", courseInfoListList);
        return "CourseManagePage";
    }

    @PostMapping("/getShare")
    public ResponseEntity<String> getSharedCourse() {
        ClientB_Send_Socket clientB_send_socket = new ClientB_Send_Socket();
        clientB_send_socket.sendMessage("share B");

        return ResponseEntity.ok("獲取成功");
    }

    @PostMapping("/transferShare")
    public ResponseEntity<Document> transferMyShareCourse(){
        Document doc = xmlServer.getTheShareCourse();
        String filePath = "src/main/resources/Transfer/BShare.xml";

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            File file = new File(filePath);
            // 如果文件存在，先删除再创建
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            System.out.println("XML saved successfully to " + filePath);

        } catch (TransformerException | IOException e) {
            System.out.println("Error saving XML to " + filePath + ": " + e.getMessage());
        }
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
