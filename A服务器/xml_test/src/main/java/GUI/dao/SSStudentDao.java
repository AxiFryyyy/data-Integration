package GUI.dao;

import GUI.entity.SSStudent;

import java.util.List;

public interface SSStudentDao {
    public SSStudent findOne(String sno);
    public List<SSStudent> findAll();
}
