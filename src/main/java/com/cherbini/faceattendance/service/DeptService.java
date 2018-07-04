package com.cherbini.faceattendance.service;

import com.cherbini.faceattendance.model.Dept;

import java.util.List;

public interface DeptService {
    /*
    获取部门员工信息
     */
    List<Dept> findDepts();
}
