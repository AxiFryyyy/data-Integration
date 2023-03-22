package com.departmentb_system.PO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesChose {
    private String course_id;
    private String course_name;
    private String course_hours;
    private String credit;
    private String teacher;
    private String location;
    private String result;
}
