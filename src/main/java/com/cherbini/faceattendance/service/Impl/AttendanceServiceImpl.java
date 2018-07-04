package com.cherbini.faceattendance.service.Impl;

import com.cherbini.faceattendance.mapper.AttendanceMapper;
import com.cherbini.faceattendance.model.Attendance;
import com.cherbini.faceattendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service("AttendanceServiceImpl")
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceMapper mapper;


    @Override
    public boolean updateAttendance(Attendance attendance) {
        Integer temp;
        //首先查询数据库是否已经存在当日的考勤信息
       Attendance a1=mapper.findEtimeById(attendance);
        if (a1== null) {
            temp = mapper.insertDay(attendance);
        } else {
           Date etime= a1.getEtime();
           Date ltime=attendance.getLtime();
           long enter =etime.getTime();
           long leave=ltime.getTime();
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            attendance.setTtime(sdf.format(leave-enter));
            temp = mapper.updateLtime(attendance);

        }

        if (temp > 0) return true;
        return false;
    }

    @Override
    public List<Attendance> findAttendance(String  date,Integer id) {
        List<Attendance> list = mapper.findByMonth(date,id);
        return list;
    }

    @Override
    public void updateComment(Attendance attendance) {
        mapper.updateComment(attendance);
    }

    @Override
    public List<Attendance> findAttendanceByDay(Date date) {
        return mapper.findAllAttendanceBydate(date);
    }

    @Override
    public Boolean updateTtime(Attendance attendance) {
        if ( mapper.updateTtime(attendance)!=0) return true;
        return false ;
    }
}
