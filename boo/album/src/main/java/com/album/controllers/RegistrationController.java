package com.album.controllers;

import com.album.model.api.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by akarpinska on 5/6/14.
 */
@Controller
public class RegistrationController {

    private final AlbumService albumService;

    @Autowired
    public RegistrationController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @RequestMapping(value="/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("warning", "");
        return "registration";
    }

    @RequestMapping(value="/registration", method=POST)
    public String loginUser(@RequestParam("fullname") String fullName,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("confirm_password") String confirmPassword,
                            Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("warning", "Password does not match confirmed password.");
            return "registration";
        }

        if (albumService.registerNewUser(fullName, username, password))
            return "main";
        else {
            model.addAttribute("warning", "User with given name already exists.");
            return "registration";
        }

    }
}
