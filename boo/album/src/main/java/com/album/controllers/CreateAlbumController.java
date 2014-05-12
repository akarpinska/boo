package com.album.controllers;

import com.album.model.api.AlbumService;
import com.album.model.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by akarpinska on 5/12/14.
 */
@Controller
public class CreateAlbumController extends BaseController {

    @Autowired
    public CreateAlbumController(AlbumService albumService) {
        super(albumService);
    }

    @RequestMapping(value={"/create_album"}, method = GET)
    public String processGet(Model model) {
        User user = (User) session().getAttribute("user");
        if (user == null)
            return "redirect: login";
        return "create_album";
    }

    @RequestMapping(value={"/create_album"}, method = POST)
    public String processPost(Model model) {
        return "create_album";
    }
}