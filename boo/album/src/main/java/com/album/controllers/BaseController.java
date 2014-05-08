package com.album.controllers;

import com.album.model.api.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by anastasia on 4/30/14.
 */
@Controller
public class BaseController {

    protected final AlbumService albumService;

    @Autowired
    protected BaseController(AlbumService albumService) {
        this.albumService = albumService;
    }

    protected static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
}
