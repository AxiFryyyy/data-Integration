package com.departmentb_system.PO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseChoseList {
    private List<CoursesChose> coursesChoseList;
}
