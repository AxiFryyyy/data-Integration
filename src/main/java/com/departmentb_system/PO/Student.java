package com.departmentb_system.PO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String student_id;
    private String student_name;
    private String gender;
    private String major;
    private String password;
}
