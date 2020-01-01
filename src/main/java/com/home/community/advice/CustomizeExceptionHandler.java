package com.home.community.advice;


import com.alibaba.fastjson.JSON;
import com.home.community.dto.ResultDTO;
import com.home.community.exception.CustomizeErrorCode;
import com.home.community.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@ControllerAdvice //全局异常处理
@Slf4j
/**
 * 自定义异常处理类
 */
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handler(HttpServletRequest request , HttpServletResponse response, Exception e, Model model){
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO;
            //返回json
            if (e instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            }else {
                log.error("handle error",e);
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter write = response.getWriter();
                write.write(JSON.toJSONString(resultDTO));
            }catch (IOException io){

            }
            return null;
        }else {
            //跳转错误页面
            if (e instanceof  CustomizeException){
                model.addAttribute("message",e.getMessage());
            }else {
                log.error("handle error",e);
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }

}
