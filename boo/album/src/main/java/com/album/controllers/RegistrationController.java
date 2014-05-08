package com.album.controllers;

import com.album.model.api.AlbumService;
import com.album.model.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by akarpinska on 5/6/14.
 */
@Controller
public class RegistrationController extends BaseController {

    @Autowired
    public RegistrationController(AlbumService albumService) {
        super(albumService);
    }

    @RequestMapping(value="/registration", method = GET)
    public String processGet(Model model) {
        model.addAttribute("warning", "");
        return "registration";
    }

    @RequestMapping(value="/registration", method = POST)
    public String processPost(@RequestParam("fullname") String fullName,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model) {
        User user = albumService.registerNewUser(fullName, username, password);
        if (user != null) {
            model.addAttribute("username", fullName);
            session().setAttribute("user", user);
            return "main";
        } else {
            model.addAttribute("warning", "User with given name already exists.");
            return "registration";
        }
    }
}
