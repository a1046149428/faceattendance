package com.cherbini.faceattendance.controller;


import com.cherbini.faceattendance.commonUtils.JsonDateValueProcessor;
import com.cherbini.faceattendance.model.Attendance;
import com.cherbini.faceattendance.model.User;
import com.cherbini.faceattendance.service.AttendanceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class AttendanceController {
    @Autowired
    AttendanceService service;

    @PostMapping("/setComment")
    public String setComment(HttpServletRequest request) throws ParseException {
        String comment = request.getParameter("comment");
        String day = request.getParameter("day");
        User user = (User) request.getSession().getAttribute("user");
        Attendance attendance = new Attendance();
        attendance.setUser(user);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        attendance.setDay(sdf.parse(day));
        attendance.setComment(comment);
        service.updateComment(attendance);
        return "myAttendance";
    }

    @GetMapping("/getAllAttendance")
    @ResponseBody
    public String getAllattendance(HttpServletRequest request) throws ParseException {
        String date = request.getParameter("date");
        User user = (User) request.getSession().getAttribute("user");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date day = simpleDateFormat.parse(date);
        List<Attendance> list = service.findAttendanceByDay(day);
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = new JsonConfig();
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
        JSONArray array = new JSONArray();
        array = array.fromObject(list, config);
        jsonObject.put("name", user.getName());
        jsonObject.put("id", user.getId());
        jsonObject.put("AllAtt", array);
        return jsonObject.toString();
    }

    @PostMapping("/setTtime")
    public String setTtime(HttpServletRequest request) throws ParseException {
        String id = request.getParameter("id");
        String ttime = request.getParameter("ttime");
        String time = request.getParameter("time");
        Attendance attendance = new Attendance();
        User user2 = new User();

        user2.setId(Integer.valueOf(id));
        attendance.setUser(user2);
        attendance.setTtime(ttime);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        attendance.setDay(sdf.parse(time));
        service.updateTtime(attendance);
        return "admin";
    }
}
