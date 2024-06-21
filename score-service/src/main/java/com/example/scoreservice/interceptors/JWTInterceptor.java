package com.example.scoreservice.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.scoreservice.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component("scoreServiceJWT")
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("*****************************************");
        System.out.println("开始token验证");
        System.out.println("*****************************************");
        Map<String, Object> map = new HashMap<>();
        // 获取请求头中令牌
        String token = request.getHeader("token");

        response.setHeader("Access-Control-Allow-Origin", "*");
        // response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,token");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        System.err.println("------------------>:已完成跨域处理");

        try {
            // 验证令牌
            JWTUtils.verify(token);
            System.out.println("*****************************************");
            System.out.println("token验证成功");
            System.out.println("*****************************************");
            return true;
        } catch (SignatureVerificationException e) {
            map.put("code", 4000);
            e.printStackTrace();
            map.put("data", "无效签名!");
        }catch (TokenExpiredException e){
            map.put("code", 4001);
            e.printStackTrace();
            map.put("data", "token过期!");
        }catch (AlgorithmMismatchException e){
            map.put("code", 4002);
            e.printStackTrace();
            map.put("data", "token算法不一致!");
        }catch (Exception e){
            map.put("code", 4003);
            e.printStackTrace();
            map.put("data", "token无效!");
        }
        System.out.println("*****************************************");
        System.out.println("未通过token验证");
        System.out.println("*****************************************");
        // 将map转为json  jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}