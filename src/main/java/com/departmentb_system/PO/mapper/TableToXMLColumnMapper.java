package com.departmentb_system.PO.mapper;

import java.util.HashMap;

public class TableToXMLColumnMapper {

    private  static TableToXMLColumnMapper tableToXMLColumnMapper;

    private final HashMap<String,String> courseTableMap;
    private final HashMap<String,String> courseCourseTableMap;

    private final HashMap<String,String> studentTableMap;

    private TableToXMLColumnMapper(){
        courseTableMap = new HashMap<>();
        courseTableMap.put("COURSE_ID", "编号");
        courseTableMap.put("COURSE_NAME", "名称");
        courseTableMap.put("COURSE_HOURS", "课时");
        courseTableMap.put("CREDIT", "学分");
        courseTableMap.put("TEACHER", "老师");
        courseTableMap.put("LOCATION", "地点");
        courseTableMap.put("SHARE_WITH", "共享");

        courseCourseTableMap = new HashMap<>();
        courseCourseTableMap.put("COURSE_ID","课程编号");
        courseCourseTableMap.put("STUDENT_ID","学生编号");
        courseCourseTableMap.put("RESULT","得分");

        studentTableMap = new HashMap<>();
        studentTableMap.put("STUDENT_ID", "学号");
        studentTableMap.put("STUDENT_NAME", "名称");
        studentTableMap.put("MAJOR","专业");
        studentTableMap.put("GENDER", "性別");
    }

    public static TableToXMLColumnMapper getTableToXMLColumnMapper() {
        tableToXMLColumnMapper = new TableToXMLColumnMapper();
        return tableToXMLColumnMapper;
    }
    public String courseTableColumn(String column){
        return tableToXMLColumnMapper.courseTableMap.get(column);
    }

    public String courseChoseTableColumn(String column){
        return tableToXMLColumnMapper.courseCourseTableMap.get(column);
    }

    public String studentTableColumn(String column){
        return tableToXMLColumnMapper.studentTableMap.get(column);
    }
}
