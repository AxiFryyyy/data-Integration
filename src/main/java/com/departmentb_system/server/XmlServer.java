package com.departmentb_system.server;


import org.w3c.dom.Document;

public interface XmlServer {
    public Document transferToXML(String table);

    Document getTheShareCourse();
}
