package GUI.dao;

import GUI.entity.SSSelect;

import java.util.List;

public interface SSSelectDao {
    public List<SSSelect> findCourse(String cno);
    public List<SSSelect> findStudent(String sno);
}
