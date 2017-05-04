package com.dayuanit.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dayuanit.util.VerifyCodeUtils;

@Controller
@RequestMapping("/code")
public class CodeController {
	
	public static final String CODE_FLAG = "code_flog";
	public static final String CODE_TIMES = "code_times_flog";
	
	@RequestMapping("/geneCode")
	public void geneCode(HttpServletResponse response, HttpServletRequest request) {
		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg"); 
        
        HttpSession session2 = request.getSession(false);
        Object obj = session2.getAttribute(CODE_TIMES);
        if (null == obj) {
        	session2.setAttribute(CODE_TIMES, 1);
        } else {
        	int times = Integer.parseInt(obj.toString());
        	if (times > 10) {
        		return;
        	}
        	
        	session2.setAttribute(CODE_TIMES, ++ times);
        }
        
		
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        System.out.println(verifyCode);
        HttpSession session = request.getSession();
        session.setAttribute(CODE_FLAG, verifyCode);
        
        OutputStream out = null;
        try {
        	out = response.getOutputStream();
			VerifyCodeUtils.outputImage(95, 25, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
