package com.cherbini.faceattendance.service.Impl;

import com.cherbini.faceattendance.mapper.UserMapper;
import com.cherbini.faceattendance.model.User;
import com.cherbini.faceattendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        int num = userMapper.addUser(user);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePic(String picAddress,Integer id) {
        int temp = userMapper.updateUser(picAddress,id);
        if (temp > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String searchPic(Integer id) {
        return userMapper.findPicById(id);
    }

    @Override
    public User findByPass(User user) {
        return userMapper.findbypass(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public boolean deleteUser(String id) {
        boolean flag=false;
        if (userMapper.deleteUser(id)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean updateName(String name, String id) {
        boolean flag=false;
        if (userMapper.updateName(name,id)>0){
            flag=true;
        }
        return flag;
    }


}
