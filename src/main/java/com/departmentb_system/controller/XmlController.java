package com.departmentb_system.controller;

import com.departmentb_system.server.XmlServer;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


@Controller
@RequestMapping("/xmlServer")
public class XmlController {

    @Autowired
    XmlServer xmlServer;

    @GetMapping
    public void changeDataToXML(@RequestParam("table") String table, HttpServletResponse response) throws Exception{
        Document document = xmlServer.transferToXML(table);
        response.setContentType("text/xml");
        response.setHeader("Content-Disposition", "attachment;filename=data.xml");
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(response.getOutputStream());
        transformer.transform(source, result);
    }
    @GetMapping("/getShare")
    public void getSharedCourse(){

    }
}
