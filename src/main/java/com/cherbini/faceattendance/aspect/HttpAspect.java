package com.cherbini.faceattendance.aspect;


import com.cherbini.faceattendance.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {
    private final static Logger LOGGER= LoggerFactory.getLogger(HttpAspect.class);
    @Pointcut("execution(public * com.cherbini.faceattendance.controller.UserController.*(..))")
    public void log(){
    }
    @Before("log()")
    public void beforeRequest(JoinPoint joinPoint){

        HttpServletRequest request= getRequest();
        User user= (User) request.getSession().getAttribute("user");

        if(user==null) {

            request.getRequestDispatcher("/login");
        }else {

        }
        //url
        LOGGER.info("url={}",request.getRequestURL());
        //method
        LOGGER.info("method={}",request.getMethod());
        //ip
        LOGGER.info("ip={}",request.getRemoteAddr());
        //类方法
        LOGGER.info("classMethod={}",joinPoint.getSignature().getDeclaringTypeName() +"."+joinPoint.getSignature().getName());
        //参数
        LOGGER.info("para={}",joinPoint.getArgs());

    }
    //获取request
   private HttpServletRequest getRequest(){
       ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       return attributes.getRequest();

   }
}
