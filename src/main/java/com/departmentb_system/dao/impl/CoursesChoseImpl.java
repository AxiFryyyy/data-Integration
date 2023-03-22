package com.departmentb_system.dao.impl;


import com.departmentb_system.PO.CourseChoseList;
import com.departmentb_system.PO.CourseInfo;
import com.departmentb_system.PO.CoursesChose;
import com.departmentb_system.dao.CoursesChoseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CoursesChoseImpl implements CoursesChoseDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<CoursesChose> getCoursesChose(String student_id) {
        String sql = "SELECT c.*, cc.result FROM COURSES c INNER JOIN COURSES_CHOSE cc ON c.course_id = cc.course_id WHERE cc.student_id = ?";
        RowMapper<CoursesChose> rowMapper = new BeanPropertyRowMapper<>(CoursesChose.class);
        List<CoursesChose> coursesChoseList = new ArrayList<CoursesChose>();
        try{
            coursesChoseList = jdbcTemplate.query(sql,rowMapper,student_id);
            System.out.println(coursesChoseList);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
        return coursesChoseList;
    }

    @Override
    public List<CourseInfo> getAllCoursesInfo() {
        String sql = "SELECT * FROM COURSES";
        RowMapper<CourseInfo> rowMapper = new BeanPropertyRowMapper<>(CourseInfo.class);
        List<CourseInfo> courseInfoList = new ArrayList<>();
        try{
            courseInfoList = jdbcTemplate.query(sql,rowMapper);

        }catch (EmptyResultDataAccessException e){
            return null;
        }
        return courseInfoList;
    }

    @Override
    public Boolean chooseCourse(String courseId, String studentID) {
        String sql = "INSERT INTO courses_chose (course_id,student_id, result) VALUES (?, ?, null)";
        System.out.println(studentID);
        try{
            jdbcTemplate.update(sql,courseId,studentID);
        }catch (Error e){
            return false;
        }
        return true;
    }
}
