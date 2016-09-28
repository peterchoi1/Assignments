package com.tiy.ssa;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @RequestMapping("/")
    public ModelAndView home(HttpServletRequest request, ModelAndView mv) {
        if (request.getParameter("name") != null) {
            mv.addObject("name", request.getParameter("name"));
        }
        mv.setViewName("home");
        return mv;
    }

}