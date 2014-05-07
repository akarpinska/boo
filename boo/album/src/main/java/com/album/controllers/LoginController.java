package com.album.controllers;

import com.album.model.api.AlbumService;
import com.album.model.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by anastasia on 4/30/14.
 */
@Controller
public class LoginController {

    private final AlbumService albumService;

    @Autowired
    public LoginController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @RequestMapping(value={"/", "/login"}, method = GET)
    public String processGet(Model model) {
        model.addAttribute("warning", "");
        return "login";
    }

    @RequestMapping(value={"/", "/login"}, method = POST)
    public String processPost(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model) {

        User user = albumService.findUser(username, password);
        if (user != null) {
            model.addAttribute("username", user.getFullName());
            return "main";
        } else {
            model.addAttribute("warning", "Invalid username or password.");
            return "login";
        }
    }
}
