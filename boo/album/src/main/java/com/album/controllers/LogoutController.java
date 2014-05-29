package com.album.controllers;

import com.album.service.api.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by akarpinska on 5/29/14.
 */
@Controller
public class LogoutController extends BaseController {

    @Autowired
    public LogoutController(AlbumService albumService) {
        super(albumService);
    }

    @RequestMapping(value={"/logout"}, method = GET)
    public String processGet(Model model) {
        session().invalidate();
        return "redirect:login.htm";
    }
}
