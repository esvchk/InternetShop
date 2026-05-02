package com.academy.course.utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class ParameterConverter {
    public static Integer getIntegerParameter(HttpServletRequest request,String paramName){
        return Integer.parseInt(request.getParameter(paramName));
    }

    public static Double getDoubleParameter(HttpServletRequest request,String paramName){
        return Double.parseDouble(request.getParameter(paramName));
    }

    public static LocalDate getDateParameter(HttpServletRequest request,String paramName){
        return LocalDate.parse(request.getParameter(paramName));
    }

    public static String getStringParameter(HttpServletRequest request,String paramName){
        return request.getParameter(paramName);
    }

}
