package com.departmentb_system.dao;

import com.departmentb_system.PO.CourseInfo;
import com.departmentb_system.PO.CoursesChose;
import com.departmentb_system.PO.Student;

import java.util.List;

public interface CoursesChoseDao {
    public List<CoursesChose> getCoursesChose(String student_id);

    public List<CourseInfo> getAllCoursesInfo();

    Boolean chooseCourse(String courseId, String studentID);

    Student findThisStudent(String studentID);

    CourseInfo findCourseInMine(String courseID);

    Boolean deleteCourseChose(String courseId, String studentId);

    Boolean insertStudent(String studentId, String studentName, String gender, String major);
}
