package com.example.libmanagement;

public class User {
    String iduser, student_name, class_v, password, username;

    public User(String iduser, String student_name, String class_v, String password,String username) {
        this.iduser = iduser;
        this.student_name = student_name;
        this.class_v = class_v;
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getClass_v() {
        return class_v;
    }

    public void setClass_v(String class_v) {
        this.class_v = class_v;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
