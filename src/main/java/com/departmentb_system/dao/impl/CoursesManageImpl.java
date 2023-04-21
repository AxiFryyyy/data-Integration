package com.departmentb_system.dao.impl;

import com.departmentb_system.PO.CourseInfo;
import com.departmentb_system.dao.CoursesManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CoursesManageImpl implements CoursesManageDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
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
    public Boolean deleteCourse(String courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try{
            jdbcTemplate.update(sql,courseId);
        }catch(Error e){
            return false;
        }
        return true;
    }




    @Override
    public boolean addCourse(String name, int hours, int credits, String teacher, String location, boolean shared) {
        String sql = "INSERT INTO courses (course_name,course_hours,credit,teacher,location, share_with) VALUES (?, ?,?,?,?,?)";
        try{
            jdbcTemplate.update(sql,name,hours,credits,teacher,location,shared);
        }catch (Error e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addCourseById(String id, String name, String credits, String teacher, String location, String shared) {
        String sql = "INSERT INTO courses (course_id,course_name,course_hours,credit,teacher,location, share_with) VALUES (?,?, null,?,?,?,?)";
        try{
            jdbcTemplate.update(sql,id,name,credits,teacher,location,shared);
        }catch (Error e){
            return false;
        }
        return true;
    }

    @Override
    public boolean findCourseExist(String courseId){
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        RowMapper<CourseInfo> rowMapper = new BeanPropertyRowMapper<>(CourseInfo.class);
        List<CourseInfo> courseInfoList = new ArrayList<>();
        try{
            courseInfoList = jdbcTemplate.query(sql,rowMapper);

        }catch (EmptyResultDataAccessException e){
            return false;
        }
        if(courseInfoList.size() == 0){
            return false;
        }
        return true;
    }
}
