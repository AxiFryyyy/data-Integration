package com.departmentb_system.dao;


import org.w3c.dom.Document;

public interface XMLServerDao {
    public Document changeCourseTableToXML();

    public Document changeChooseCourseTableToXML();

    Document changeStudentTableToXML();

    Document getTheShareCourse();
}
