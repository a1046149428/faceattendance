package com.cherbini.faceattendance.mapper;


import com.cherbini.faceattendance.model.Dept;
import com.cherbini.faceattendance.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("Select * from user where id=#{id}")
        // @Results(value = @Result(property = "dept", column = "deptno", javaType = Dept.class))
    User findById(Integer id);


    @Insert("insert into user(password,name,sex,deptno,power)values(#{password},#{name},#{sex},#{dept.deptno},#{power})")
    @Results(value = @Result(property = "dept", column = "deptno", javaType = Dept.class))
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addUser(User user);

    @Update("update user set pic='${pic}' where id =${id}")
    Integer updateUser(@Param("pic") String picAddress, @Param("id") Integer id);

    @Select("select pic from user where id=${id} ")
    String findPicById(@Param("id") Integer id);

    @Select("select *  from user where id=#{id} and password=#{password}")
    User findbypass(User user);

    @Select("select * from user")
    List<User> findAll();

    @Delete("delete from user where id =${id}")
    Integer deleteUser(@Param("id") String id);

    @Update("update user set name=#{name} where id=#{id}")
    Integer updateName(@Param("name") String name,@Param("id") String id);



}
