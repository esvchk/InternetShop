package com.academy.course.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class ParameterConverter {
    public static Integer getIntegerParameter(HttpServletRequest request,
                                              String paramName){
        if (request.getParameter(paramName).isEmpty()) {
            return null;
        }
            return Integer.parseInt(request.getParameter(paramName));

    }

    public static Double getDoubleParameter(HttpServletRequest request,
                                            String paramName){
        if (request.getParameter(paramName).isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(request.getParameter(paramName));
    }

    public static LocalDate getDateParameter(HttpServletRequest request,
                                             String paramName){
        return LocalDate.parse(request.getParameter(paramName));
    }

    public static String getStringParameter(HttpServletRequest request,
                                            String paramName){
        if (request.getParameter(paramName).isEmpty()) {
            return StringUtils.EMPTY;
        }
        return request.getParameter(paramName);
    }

    public static Boolean getBooleanParameter(HttpServletRequest request, String paramName){
        return Boolean.parseBoolean(request.getParameter(paramName));
    }

}
