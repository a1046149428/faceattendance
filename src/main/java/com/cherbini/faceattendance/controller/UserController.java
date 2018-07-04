package com.cherbini.faceattendance.controller;


import com.cherbini.faceattendance.commonUtils.AddressPIc;
import com.cherbini.faceattendance.facematchutils.FaceMatch;
import com.cherbini.faceattendance.model.Attendance;

import com.cherbini.faceattendance.model.User;
import com.cherbini.faceattendance.service.AttendanceService;


import com.cherbini.faceattendance.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AttendanceService atttenService;


    @PostMapping("/register")
    public int faceRegister(HttpServletRequest request) throws IOException {
        String pic = request.getParameter("imageData");
        Integer id = Integer.valueOf(request.getParameter("id"));
        String picAddress = AddressPIc.addPic(pic, id);
        boolean temp = userService.updatePic(picAddress, id);
        if (temp) {
            return 1;
        }
        return 0;
    }

    @PostMapping("/facePost")
    public int faceAttendance(HttpServletRequest request) throws IOException {
        String pic = request.getParameter("imageData");
        Integer id = Integer.valueOf(request.getParameter("id"));

        String pic1 = AddressPIc.addPic(pic, id);
        String pic2 = userService.searchPic(id);
        String result = FaceMatch.match(pic1, pic2);
        JSONObject object = new JSONObject(result);
        //通过属性名，获得内部的对象
        JSONArray arr = object.getJSONArray("result");
        //获取Json
        Double score = arr.getJSONObject(0).getDouble("score");
        if (score > 80) {
            Date date = new Date();
            Attendance attendance = new Attendance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


            try {
                attendance.setDay(sdf.parse(sdf.format(date)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            attendance.setEtime(date);
            attendance.setLtime(date);

            User user = new User();
            user.setId(id);
            attendance.setUser(user);
            if (atttenService.updateAttendance(attendance)) return 0;
        }
        return 1;
    }

    @GetMapping("/getAllUsers")
    public String findAll() {

        List<User> userList = userService.findAll();
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("userlist", userList);
        return jsonObject.toString();
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(HttpServletRequest req) {
        String id = req.getParameter("id");

        if (userService.deleteUser(id)) {
            return "1";
        }
        return "0";
    }

    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        if (userService.updateName(name, id)) {
            return "1";
        }
        return "0";
    }


}