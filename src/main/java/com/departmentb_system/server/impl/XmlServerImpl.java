package com.departmentb_system.server.impl;

import com.departmentb_system.dao.XMLServerDao;
import com.departmentb_system.server.XmlServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


@Service
public class XmlServerImpl implements XmlServer {
    @Autowired
    XMLServerDao xmlServerDao;

    @Override
    public Document transferToXML(String table) {
        switch (table) {
            case "courses" -> {
                Document doc = xmlServerDao.changeCourseTableToXML();
                String filePath = "src/main/resources/Transfer/B_courses.xml";
                saveXMLToPath(doc, filePath);
                return doc;
            }
            case "choseCourse" -> {
                Document doc = xmlServerDao.changeChooseCourseTableToXML();
                String filePath = "src/main/resources/Transfer/B_chose_course.xml";
                saveXMLToPath(doc, filePath);
                return doc;
            }
            case "students" -> {
                Document doc = xmlServerDao.changeStudentTableToXML();
                String filePath = "src/main/resources/Transfer/B_student.xml";
                saveXMLToPath(doc, filePath);
                return doc;
            }
        }
        return null;
    }

    public void saveXMLToPath(Document doc, String filePath){
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
    }

    @Override
    public Document getTheShareCourse(){
        return xmlServerDao.getTheShareCourse();
    }
}
