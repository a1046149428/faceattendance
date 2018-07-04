package com.cherbini.faceattendance.commonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateUtils {
    public  static  long toString(String s) throws ParseException {
        SimpleDateFormat sdf =new SimpleDateFormat("hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return sdf.parse(s).getTime();
    }
}
