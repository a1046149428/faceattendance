package com.cherbini.faceattendance.service;

import com.cherbini.faceattendance.model.Attendance;

import java.util.Date;
import java.util.List;

public interface AttendanceService {
    boolean updateAttendance(Attendance attendance);
    List<Attendance> findAttendance(String date,Integer id);
    void updateComment(Attendance attendance);
    List<Attendance> findAttendanceByDay(Date date);
    Boolean updateTtime(Attendance attendance);

}
