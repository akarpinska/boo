package com.album.controllers;

import com.album.model.api.Album;
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
 * Created by anastasia on 4/30/14.
 */
@Controller
public class MainController extends BaseController {

    @Autowired
    public MainController(AlbumService albumService) {
        super(albumService);
    }

    @RequestMapping(value={"/", "/main"}, method = GET)
    public String processGet(Model model) {
        model.addAttribute("warning", "");
        return "main";
    }

    @RequestMapping(value={"/", "/main"}, method = POST)
    public String processPost(Model model, @RequestParam("album_name") String albumName) {
        User user = (User) session().getAttribute("user");
        Album album = albumService.createNewAlbum(user, albumName);
        if (album == null)
            model.addAttribute("warning", "Album with name " + albumName + " already exists.");
        return "main";
    }
}
