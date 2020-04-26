package com.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @RequestMapping("/index.do")
    public String index(HttpServletRequest request){
        request.setAttribute("ceshi","ceshi");
        return "index";
    }

    @RequestMapping("/ceshi.do")
    @ResponseBody
    public String cehi(){
        return "ceshi";
    }
}
