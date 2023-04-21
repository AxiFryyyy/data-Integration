package com.departmentb_system.socket;

import com.departmentb_system.PO.CourseInfo;
import com.departmentb_system.PO.CoursesChose;
import com.departmentb_system.PO.Student;
import com.departmentb_system.dao.CoursesChoseDao;
import com.departmentb_system.dao.CoursesManageDao;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class ClientB_Receive_Socket implements Runnable{
    /**
     * 选课是否成功的反馈信息
     */
    //todo
    String retMessage = "choose success";


    @Autowired
    CoursesChoseDao coursesChoseDao;

    @Autowired
    CoursesManageDao coursesManageDao;



    public ClientB_Receive_Socket(){

    }
    public String getRetMessage() {
        return retMessage;
    }

    public  void  run() {
        String studenttable = "src/main/resources/Transfer/B_student.xml";
        String coursetable = "src/main/resources/Transfer/B_courses.xml";
        String selecttable = "src/main/resources/Transfer/B_chose_course.xml";
        String sharetable = "src/main/resources/Transfer/BShare.xsl";
        String choosetable = "src/main/resources/Transfer/B_chose_course.xml";
        Socket socket = null;
        try {
            socket = new Socket("172.17.194.248",9999);
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());
            // 获取客户端输入流
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // 获取客户端输出流
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
            writer.println("B");
            writer.flush();
            //已接收的文件数量
            AtomicInteger doc_count = new AtomicInteger();
            while (true) {
                System.out.println("testing");
                String message = reader.readLine();
                System.out.println("Received message from server: " + message);
                if (message == null) {
                    continue;
                }
                //接收文件
                else if (message.contains("准备发送选课文件")) {

                    String length = message.substring(15);
                    int l = Integer.parseInt(length);
                    FileOutputStream fos = new FileOutputStream("src/main/resources/output/b"+doc_count+".xml");
                    for(int i = 0;i < l;i++){
                        String m = reader.readLine();
                        byte[] bytes = (m + "\n").getBytes();
                        fos.write(bytes);
                    }
                    fos.close();
                    System.out.println("第"+doc_count+"个文件接收完成。");
                    System.out.println(validateXmlWithXsd("src/main/resources/output/b"+doc_count+".xml",
                            "src/main/resources/Validate/formatChoice.xsd"));
                    if(validateXmlWithXsd("src/main/resources/output/b"+doc_count+".xml",
                            "src/main/resources/Validate/formatChoice.xsd")) {
                        int last = Integer.parseInt(String.valueOf(doc_count)) - 1;
                        insertStudent("src/main/resources/output/b"+last+".xml");
                        handleSelect("src/main/resources/output/b"+doc_count+".xml");
                    }
                    doc_count.getAndIncrement();
                //接收共享課程文件
                }else if(message.equals("准备发送共享文件")){
                    String length = message.substring(15);
                    int l = Integer.parseInt(length);
                    FileOutputStream fos = new FileOutputStream("src/main/resources/output/b"+doc_count+".xml");
                    for (int i=0; i<l; i++) {
                        String m = reader.readLine();
                        byte[] bytes = (m + "\n").getBytes();
                        fos.write(bytes);
                    }
                    fos.close();
                    System.out.println("第"+doc_count+"个文件接收完成");
                    System.out.println(validateXmlWithXsd("src/main/resources/output/b"+doc_count+".xml",
                            "src/main/resources/Validate/formatClass.xsd"));
                    if (validateXmlWithXsd("src/main/resources/output/b"+doc_count+".xml",
                            "src/main/resources/Validate/formatClass.xsd")) {
                        handleShare("src/main/resources/output/b"+doc_count+".xml");
                    }

                }
                //处理服务端的collect请求，发送三个文件
                else if(message.equals("collect *")){
                    writer.println("B will send *");
                    writer.flush();
                    writer.println("学生表准备发送，行数为：" + countLinesInXML(studenttable));
                    writer.flush();
                    sendmessage(socket,studenttable);
                    writer.println("课程表准备发送，行数为：" + countLinesInXML(coursetable));
                    writer.flush();
                    sendmessage(socket,coursetable);
                    writer.println("选课表准备发送，行数为：" + countLinesInXML(selecttable));
                    writer.flush();
                    sendmessage(socket,selecttable);
                }
                //处理服务端转发的别的客户端的share请求，把共享表发送给服务端
                else if(message.contains("share")){
                    ClientB_Send_Socket clientB_send_socket = new ClientB_Send_Socket();
                    clientB_send_socket.sendShareCourse();
                }
                //接收服务端转发的其他客户端的delete请求，需要完成在客户端上继续完成删除功能
                else if(message.contains("delete")){
                    String courseInfo = message.substring(9);
                    Boolean deleted = handleRemove(courseInfo.split(" ")[0],courseInfo.split(" ")[1]);
                    if(!deleted){
                        writer.println("delete failed");
                    }
                    writer.println("delete success");
                    writer.flush();
                }
                else if(message.contains("choose")) {
                    System.out.println("testingggggg");
                    retMessage = message;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }


    public static boolean validateXmlWithXsd(String xmlFilePath, String xsdFilePath) {
        try {
            // 创建SchemaFactory对象
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // 创建Schema对象
            Schema schema = factory.newSchema(new File(xsdFilePath));
            // 创建Validator对象
            javax.xml.validation.Validator validator = schema.newValidator();
            // 验证XML文件
            validator.validate(new StreamSource(new File(xmlFilePath)));
            return true;
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertStudent(String xmlPath){
        File file = new File(xmlPath);

        SAXReader reader = new SAXReader();

        String studentId = null;
        String studentName = null;
        String gender = null;
        String major = null;

        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(file);
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while(it.hasNext()){

                Element book = (Element)it.next();
                List<Attribute> bookAttrs = book.attributes();
                for(Attribute attr:bookAttrs){
                    System.out.println("属性："+attr.getName()+"---值："+attr.getValue());
                }

                Iterator itt = book.elementIterator();
                while(itt.hasNext()){
                    Element node = (Element)itt.next();
                    System.out.println("节点：" + node.getName() + "---值：" + node.getStringValue());
                    if(node.getName().equals("学号")){
                        studentId = node.getStringValue();
                    }
                    if(node.getName().equals("名称")){
                        studentName = node.getStringValue();
                    }
                    if(node.getName().equals("性别")){
                        gender = node.getStringValue();
                    }
                    if(node.getName().equals("专业")){
                        major = node.getStringValue();
                    }
                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        if(coursesChoseDao.findThisStudent(studentId) != null){
            return;
        }
        coursesChoseDao.insertStudent(studentId,studentName,gender,major);
    }
    public void handleSelect(String xmlPath){
        File file = new File(xmlPath);

        SAXReader reader = new SAXReader();

        String cno = null;
        String sno = null;
        String grd = null;

        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(file);
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while(it.hasNext()){

                Element book = (Element)it.next();
                List<Attribute> bookAttrs = book.attributes();
                for(Attribute attr:bookAttrs){
                    System.out.println("属性："+attr.getName()+"---值："+attr.getValue());
                }

                Iterator itt = book.elementIterator();
                while(itt.hasNext()){
                    Element node = (Element)itt.next();
                    System.out.println("节点：" + node.getName() + "---值：" + node.getStringValue());
                    if(node.getName().equals("学生编号")){
                        sno = node.getStringValue();
                    }
                    if(node.getName().equals("课程编号")){
                        cno = node.getStringValue();
                    }
                    if(node.getName().equals("得分")){
                        grd = node.getStringValue();
                    }
                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Student student = coursesChoseDao.findThisStudent(sno);
        /**
         * 判断所选课程是否在本院系
         */


        CourseInfo course = coursesChoseDao.findCourseInMine(cno);
        boolean flag = false;
        if(course != null){
            flag = true;
        }
        if(!flag){
            //todo

        } else {
            coursesChoseDao.chooseCourse(cno,sno);
            if (student.getMajor().equals("軟件工程")){
                coursesChoseDao.chooseCourse(cno,sno);
            }
        }



    }
    public Boolean handleRemove(String courseId,String studentId){
        CourseInfo course = coursesChoseDao.findCourseInMine(courseId);
        Student student = coursesChoseDao.findThisStudent(studentId);
        if(student == null || course == null){
            return false;
        }
        return coursesChoseDao.deleteCourseChose(courseId,studentId);
    }


    public void handleShare(String xmlPath) {
        File file = new File(xmlPath);

        SAXReader reader = new SAXReader();

        CourseInfo course = new CourseInfo();

        try {
            Document document = reader.read(file);
            Element bookStore = document.getRootElement();
            Iterator it = bookStore.elementIterator();
            while (it.hasNext()) {
                Element book = (Element) it.next();
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性"+attr.getName()+"---值："+attr.getValue());
                }
                Iterator itt = book.elementIterator();
                while (itt.hasNext()) {
                    Element node = (Element) itt.next();
                    System.out.println("节点"+node.getName()+"---值："+node.getStringValue());
                    if (node.getName().equals("编号")) {
                        course.setCourse_id(node.getStringValue());
                    } else if (node.getName().equals("名称")) {
                        course.setCourse_name(node.getStringValue());
                    } else if (node.getName().equals("学分")) {
                        course.setCredit(node.getStringValue());
                    } else if (node.getName().equals("老师")) {
                        course.setTeacher(node.getStringValue());
                    } else if (node.getName().equals("地点")) {
                        course.setLocation(node.getStringValue());
                    } else if (node.getName().equals("共享")) {
                        course.setShare_with(node.getStringValue());
                    }
                }
                if(!coursesManageDao.findCourseExist(course.getCourse_id())){
                    coursesManageDao.addCourseById(course.getCourse_id(),course.getCourse_name(),course.getCredit(),course.getTeacher(),course.getLocation(),course.getShare_with());
                }


            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void sendmessage(Socket socket,String path) throws IOException {
        // 获取客户端输入流
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        // 获取客户端输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        int lines = countLinesInXML(path);
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        for(int i = 0;i < lines;i++){
            writer.println(bufferedReader.readLine());
            writer.flush();
        }
    }
    //数文件行数方法
    public static int countLinesInXML(String path) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int lines = 0;
        while (bufferedReader.readLine() != null) {
            lines++;
        }
        bufferedReader.close();
        fileReader.close();
        return lines;
    }

}
