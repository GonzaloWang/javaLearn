package com.itheima.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ObjectAndJsonUtils {
    @Autowired
    private ObjectMapper objectMapper;

    public void ObjectToJson(HttpServletResponse response, Object object) {
        response.setContentType("Appliction/json;charset=utf-8");
        try {
            String jsonStr = objectMapper.writeValueAsString(object);
            System.out.println("jsonStr:"+jsonStr);
            response.getWriter().print(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public <T> T JsonToObject(HttpServletRequest request, Class<T> tClass) throws IOException {
        request.setCharacterEncoding("utf-8");
        return objectMapper.readValue(request.getInputStream(), tClass);

    }

}
