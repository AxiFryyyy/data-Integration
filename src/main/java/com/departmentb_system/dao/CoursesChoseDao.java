package com.departmentb_system.dao;

import com.departmentb_system.PO.CourseInfo;
import com.departmentb_system.PO.CoursesChose;

import java.util.List;

public interface CoursesChoseDao {
    public List<CoursesChose> getCoursesChose(String student_id);

    public List<CourseInfo> getAllCoursesInfo();

    Boolean chooseCourse(String courseId, String studentID);
}
