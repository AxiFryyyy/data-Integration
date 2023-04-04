package com.departmentb_system.server.impl;

import com.departmentb_system.dao.XMLServerDao;
import com.departmentb_system.server.XmlServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;


@Service
public class XmlServerImpl implements XmlServer {
    @Autowired
    XMLServerDao xmlServerDao;

    @Override
    public Document transferToXML(String table) {
        if(table.equals("courses")){
            return xmlServerDao.changeCourseTableToXML();
        }
        return xmlServerDao.changeChooseCourseTableToXML();
    }
}
