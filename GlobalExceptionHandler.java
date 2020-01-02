package com.sino.llb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sino.llb.vo.Response;
import com.sino.llb.vo.ResponseResult;
 
/**
 * 全局异常捕获处理
 */

@ControllerAdvice
public class GlobalExceptionHandler {
	private String packagePath = "com.sino";
	
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> allExceptionHandler(Exception e){
    	StackTraceElement[] els = e.getStackTrace();
    	String expStack = null;
    	for (int i = 0; i < els.length; i++) {
    		expStack = els[i].toString();
			if(expStack.toString().contains(packagePath)) {
				log.info(Thread.currentThread().getName()+" 全局异常："+expStack);
				break;
			}
		}
    	
    	
    	HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);  
        return new ResponseEntity<Object>("消息体", headers, HttpStatus.OK); 
    }
 
}
