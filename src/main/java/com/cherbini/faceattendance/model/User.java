package com.cherbini.faceattendance.model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String password;
    private String name;
    private String sex;
    private Dept dept;
    private Integer power;
    private String pic;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", deptno=" + dept +
                ", power=" + power +
                ", pic='" + pic + '\'' +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {

        this.dept = dept;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


}
