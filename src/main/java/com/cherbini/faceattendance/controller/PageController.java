package com.cherbini.faceattendance.controller;

import com.cherbini.faceattendance.commonUtils.JsonDateValueProcessor;

import com.cherbini.faceattendance.model.Attendance;
import com.cherbini.faceattendance.model.Dept;
import com.cherbini.faceattendance.model.User;

import com.cherbini.faceattendance.service.AttendanceService;
import com.cherbini.faceattendance.service.DeptService;
import com.cherbini.faceattendance.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;


@Controller

public class PageController {
    @Autowired
    UserService userService;
    @Autowired
    AttendanceService atttenService;
    @Autowired
    DeptService deptService;


    @RequestMapping("/login")
    public String login(User user, HttpSession session) {
        User user1 = userService.findByPass(user);
        if (user1 == null) {
            return "login";
        }
        session.setAttribute("user", user1);
        return "index";

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "login";
    }

    @RequestMapping(value = "/myAttendance")
    public String myAttendance(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "myAttendance";
        }
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        if (user != null) {
           if ( user.getPower()==0)
            return "admin";
        }
        return "login";
    }

    @RequestMapping("/faceRegistration")
    public String register(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if ( user.getPower()==0)
                return "faceRegistration";
        }
        return "login";
    }
    @RequestMapping("/addUser")
    public String addUser(User user) {
        user.toString();
        boolean ss = userService.addUser(user);
        return "admin";
    }

    @RequestMapping("/faceAttendance")
    public String faceAttendance() {
        return "faceAttendance";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/getPersonalAttendace")
    @ResponseBody
    public String getPersonalAttendace(HttpServletRequest request) throws ParseException {
        User user = (User) request.getSession().getAttribute("user");
        String year = request.getParameter("year");
        String month = request.getParameter("month");

        //格式化参数
        String date = year + "-" + month;

        JSONObject obj = new JSONObject();
        obj.put("username", user.getName());
        List<Attendance> list = atttenService.findAttendance(date, user.getId());
        JsonConfig config = new JsonConfig();
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
        JSONArray array = new JSONArray();
        array = array.fromObject(list, config);
        obj.put("attendace", array);
        obj.put("id", user.getId());
        return obj.toString();
    }
    @GetMapping("/getDepts")
    @ResponseBody
    public String  findDepts() {
        JSONObject object= new JSONObject();
        List<Dept> depts=deptService.findDepts();
        object.put("depts",depts);
        return object.toString();
    }
}
