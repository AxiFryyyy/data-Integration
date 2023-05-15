package com.server.integratedserver.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmlService {
    private static final String classXMLPath = "/total/totalClassTable.xml";
    private static final String studentXMLPath = "/total/totalStudentTable.xml";
    private static final String chooseCourseXMLPath = "/total/totalClassChoiceTable.xml";
    public List<Map<String, String>> fetchStudentsData() {
        List<Map<String, String>> dataList = new ArrayList<>();
        try {
            File file = new ClassPathResource(studentXMLPath).getFile();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("student");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String sex = element.getElementsByTagName("sex").item(0).getTextContent();
                    String major = element.getElementsByTagName("major").item(0).getTextContent();
                    Map<String, String> dataMap = new HashMap<>();
                    dataMap.put("id", id);
                    dataMap.put("name", name);
                    dataMap.put("sex", sex);
                    dataMap.put("major", major);
                    dataList.add(dataMap);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }

    public List<Map<String, String>> fetchClassesData() {
        List<Map<String, String>> dataList = new ArrayList<>();
        try {
            File file = new ClassPathResource(classXMLPath).getFile();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("class");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String score = element.getElementsByTagName("score").item(0).getTextContent();
                    String teacher = element.getElementsByTagName("teacher").item(0).getTextContent();
                    String location = element.getElementsByTagName("location").item(0).getTextContent();
                    Map<String, String> dataMap = new HashMap<>();
                    dataMap.put("id", id);
                    dataMap.put("name", name);
                    dataMap.put("score", score);
                    dataMap.put("teacher", teacher);
                    dataMap.put("location", location);
                    dataList.add(dataMap);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }

    public List<Map<String, String>> fetchChoicesData() {
        List<Map<String, String>> dataList = new ArrayList<>();
        try {
            File file = new ClassPathResource(chooseCourseXMLPath).getFile();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("choice");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String sid = element.getElementsByTagName("sid").item(0).getTextContent();
                    String cid = element.getElementsByTagName("cid").item(0).getTextContent();
                    String score = element.getElementsByTagName("score").item(0).getTextContent();
                    Map<String, String> dataMap = new HashMap<>();
                    dataMap.put("sid", sid);
                    dataMap.put("cid", cid);
                    dataMap.put("score", score);
                    dataList.add(dataMap);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }
}
