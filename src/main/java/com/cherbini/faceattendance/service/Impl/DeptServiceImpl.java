package com.cherbini.faceattendance.service.Impl;

import com.cherbini.faceattendance.mapper.DeptMapper;
import com.cherbini.faceattendance.model.Dept;
import com.cherbini.faceattendance.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("DeptServiceImpl")
public class DeptServiceImpl  implements DeptService{
    @Autowired
    DeptMapper deptMapper;
    @Override
    public List<Dept> findDepts() {
        return deptMapper.findDepts();
    }
}
