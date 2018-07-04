package com.cherbini.faceattendance.mapper;

import com.cherbini.faceattendance.model.Attendance;
import org.apache.ibatis.annotations.*;


import java.util.Date;
import java.util.List;


public interface AttendanceMapper {
    //查询考勤信息
    @Select("select * from attendance where id =#{user.id} and day=#{day}" )
    //@Results(value = @Result(property = "user", column = "id", javaType = User.class))
    Attendance findEtimeById(Attendance attendance);
    //更新进入时间
    @Update("update attendance set etime=#{etime} where id = #{user.id}  and day=#{day} ")
   // @Results(value = @Result(property = "user", column = "id", javaType = User.class))
    Integer updateEtime(Attendance attendance);

    //更新离开时间
    @Update("update attendance set ltime=#{ltime},ttime=#{ttime} where id = #{user.id}  and day=#{day} ")
    //@Results(value = @Result(property = "user", column = "id", javaType = User.class))
    Integer updateLtime(Attendance attendance);
    //更新comment
    @Update("update attendance set comment=#{comment} where id = #{user.id} and day=#{day} ")
    //@Results(value = @Result(property = "user", column = "id", javaType = User.class))
    Integer updateComment(Attendance attendance);
    //更新全部时间
    @Update("update attendance set ttime=#{ttime} where id = #{user.id} and day=#{day} ")
    //@Results(value = @Result(property = "user", column = "id", javaType = User.class))
    Integer updateTtime(Attendance attendance);

    //插入考勤数据
    @Insert("insert into attendance(id,day,etime)values(#{user.id},#{day},#{etime})")
    //@Results(value = @Result(property = "user", column = "id", javaType = User.class))
    Integer insertDay(Attendance attendance);

    //查询某月的考勤信息
    @Select("select * from attendance where date_format(day,'%Y-%m')='${date}' and id=${id}")
    List<Attendance> findByMonth(@Param("date")String  date,@Param("id") Integer id);

    //查询某日的所有员工考勤信息
    List<Attendance>findAllAttendanceBydate(Date date);


}
