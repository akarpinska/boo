package com.album;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by anastasia on 4/30/14.
 */
public class HomeController implements Controller {

    private String greeting;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("var", greeting);
        return mv;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
