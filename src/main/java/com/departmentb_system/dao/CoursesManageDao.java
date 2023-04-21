package com.departmentb_system.dao;

import com.departmentb_system.PO.CourseInfo;

import java.util.List;

public interface CoursesManageDao {
    public List<CourseInfo> getAllCoursesInfo();


    Boolean deleteCourse(String courseId);


    boolean addCourse(String name, int hours, int credits, String teacher, String location, boolean shared);


    boolean addCourseById(String id, String name,String credits, String teacher, String location, String shared);

    boolean findCourseExist(String courseId);
}
