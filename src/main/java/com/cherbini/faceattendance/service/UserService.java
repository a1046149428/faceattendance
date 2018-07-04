package com.cherbini.faceattendance.service;

        import com.cherbini.faceattendance.model.User;

        import java.util.List;


public interface UserService {
    //添加一个用户
    boolean addUser(User user);
    //更新用户pic地址
    boolean updatePic(String picAddress,Integer id);
    //查找用户pic地址
    String searchPic(Integer id);
    //匹对用户信息
    User findByPass(User user);
    //查找所有用户信息
    List<User> findAll();
    //通过id删除用户
    boolean deleteUser(String id);
    //根据id 更新姓名
    boolean updateName(String name,String id);
}
