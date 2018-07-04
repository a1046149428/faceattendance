package com.cherbini.faceattendance.commonUtils;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddressPIc {
    public static String addPic(String pic, Integer id) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(pic);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }
        //使用id+时间进行命名
        String time = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());

        //File file =new File("C:\\Users\\秋叶\\Desktop\\zgr0.jpeg");
        //判断目录是否存在

        File file = new File("C:/Users/10461/Desktop/Register/" + id);
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }
        String imgFilePath = "C:/Users/10461/Desktop/Register/" + id + "/" + time + ".jpg";

        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.write(b);
        out.flush();
        out.close();
        return imgFilePath;
    }
}

