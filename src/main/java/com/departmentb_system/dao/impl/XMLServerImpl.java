package com.departmentb_system.dao.impl;

import com.departmentb_system.PO.mapper.TableToXMLColumnMapper;
import com.departmentb_system.dao.XMLServerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;
import java.util.Map;

@Repository
public class XMLServerImpl implements XMLServerDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Document changeCourseTableToXML() {
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM courses");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try{
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = db.newDocument();
        Element root = doc.createElement("classes");
        doc.appendChild(root);

        for (Map<String, Object> row : resultList) {
            Element rowElement = doc.createElement("class");
            root.appendChild(rowElement);
            TableToXMLColumnMapper mapper = TableToXMLColumnMapper.getTableToXMLColumnMapper();
            for (String columnName : row.keySet()) {
                if(columnName.equals("COURSE_HOURS"))continue;
                Object value = row.get(columnName);
                Element columnElement = doc.createElement(mapper.courseTableColumn(columnName));
                columnElement.appendChild(doc.createTextNode(String.valueOf(value)));
                rowElement.appendChild(columnElement);
            }
        }
        return doc;
    }

    @Override
    public Document changeChooseCourseTableToXML() {
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM courses_chose");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try{
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = db.newDocument();
        Element root = doc.createElement("choices");
        doc.appendChild(root);

        TableToXMLColumnMapper mapper = TableToXMLColumnMapper.getTableToXMLColumnMapper();

        for (Map<String, Object> row : resultList) {
            Element rowElement = doc.createElement("choice");
            root.appendChild(rowElement);
            for (String columnName : row.keySet()) {
                Object value = row.get(columnName);
                Element columnElement = doc.createElement(mapper.courseChoseTableColumn(columnName));
                columnElement.appendChild(doc.createTextNode(String.valueOf(value)));
                rowElement.appendChild(columnElement);
            }
        }
        return doc;
    }

    @Override
    public Document changeStudentTableToXML() {
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM students");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try{
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = db.newDocument();
        Element root = doc.createElement("students");
        doc.appendChild(root);

        TableToXMLColumnMapper mapper = TableToXMLColumnMapper.getTableToXMLColumnMapper();

        for (Map<String, Object> row : resultList) {
            Element rowElement = doc.createElement("student");
            root.appendChild(rowElement);
            for (String columnName : row.keySet()) {
                if(columnName.equals("PASSWORD"))continue;
                Object value = row.get(columnName);
                Element columnElement = doc.createElement(mapper.studentTableColumn(columnName));

                columnElement.appendChild(doc.createTextNode(String.valueOf(value)));
                rowElement.appendChild(columnElement);
            }
        }
        return doc;
    }

    @Override
    public Document getTheShareCourse() {
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM courses WHERE SHARE_WITH = '1'");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try{
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = db.newDocument();
        Element root = doc.createElement("classes");
        doc.appendChild(root);

        TableToXMLColumnMapper mapper = TableToXMLColumnMapper.getTableToXMLColumnMapper();

        for (Map<String, Object> row : resultList) {
            Element rowElement = doc.createElement("class");
            root.appendChild(rowElement);
            for (String columnName : row.keySet()) {
                if(columnName.equals("COURSE_HOURS"))continue;
                Object value = row.get(columnName);
                Element columnElement = doc.createElement(mapper.courseTableColumn(columnName));
                columnElement.appendChild(doc.createTextNode(String.valueOf(value)));
                rowElement.appendChild(columnElement);
            }
        }
        return doc;
    }
}
