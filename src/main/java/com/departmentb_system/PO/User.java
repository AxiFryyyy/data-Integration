package com.departmentb_system.PO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String passWord;
    private String account_grade;
    private String handler_id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAccount_grade() {
        return account_grade;
    }

    public void setAccount_grade(String account_grade) {
        this.account_grade = account_grade;
    }

    public String getHandler_id() {
        return handler_id;
    }

    public void setHandler_id(String handler_id) {
        this.handler_id = handler_id;
    }
}
