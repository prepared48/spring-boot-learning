package com.example.demo.log;

import org.apache.logging.log4j.util.Strings;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
    public static String GetIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(!Strings.isBlank(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");

        //StringUtils.isBlank只是一个判断非空字符的方法
        if(!Strings.isBlank(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
