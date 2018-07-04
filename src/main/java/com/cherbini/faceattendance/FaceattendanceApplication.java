package com.cherbini.faceattendance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.cherbini.faceattendance.mapper")
public class FaceattendanceApplication {

	public static void main(String[] args) {
		System.err.println("////////////////////////////////////////////////////////////////////\n" +
				"//                          _ooOoo_                               //\n" +
				"//                         o8888888o                              //\n" +
				"//                         88\" . \"88                              //\n" +
				"//                         (| ^_^ |)                              //\n" +
				"//                         O\\  =  /O                              //\n" +
				"//                      ____/`---'\\____                           //\n" +
				"//                    .'  \\\\|     |//  `.                         //\n" +
				"//                   /  \\\\|||  :  |||//  \\                        //\n" +
				"//                  /  _||||| -:- |||||-  \\                       //\n" +
				"//                  |   | \\\\\\  -  /// |   |                       //\n" +
				"//                  | \\_|  ''\\---/''  |   |                       //\n" +
				"//                  \\  .-\\__  `-`  ___/-. /                       //\n" +
				"//                ___`. .'  /--.--\\  `. . ___                     //\n" +
				"//              .\"\" '<  `.___\\_<|>_/___.'  >'\"\".                  //\n" +
				"//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //\n" +
				"//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //\n" +
				"//      ========`-.____`-.___\\_____/___.-`____.-'========         //\n" +
				"//                           `=---='                              //\n" +
				"//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //\n" +
				"//         佛祖保佑       永无BUG     永不修改                  //\n" +
				"////////////////////////////////////////////////////////////////////");
		SpringApplication.run(FaceattendanceApplication.class, args);
	}
}
