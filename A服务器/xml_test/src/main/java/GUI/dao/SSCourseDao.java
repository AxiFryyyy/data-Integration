package GUI.dao;

import GUI.entity.SSCourse;

import java.util.List;

public interface SSCourseDao {
    public SSCourse findOne(String cno);
    public List<SSCourse> findAll();
}
