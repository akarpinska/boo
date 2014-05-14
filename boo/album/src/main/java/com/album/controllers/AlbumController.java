package com.album.controllers;

import com.album.model.api.Album;
import com.album.model.api.AlbumService;
import com.album.model.api.Photo;
import com.album.model.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by akarpinska on 5/14/14.
 */
@Controller
public class AlbumController extends BaseController {

    @Autowired
    public AlbumController(AlbumService albumService) {
        super(albumService);
    }

    @RequestMapping(value={"/albums/{albumName}"}, method = GET)
    public String processGet(Model model, @PathVariable String albumName) {
        Album album = getAlbum(albumName);
        if (album == null)
            return "redirect:/main";

        session().setAttribute("album", album);
        return "album";
    }

    @RequestMapping(value={"/albums/{albumName}"}, method = POST)
    public String processPost(Model model, @PathVariable String albumName,
                              @RequestParam("files") MultipartFile[] files) {
        Album album = getAlbum(albumName);
        if (album == null)
            return "redirect:/main";

        session().setAttribute("album", album);
        for (int i = 0; i < files.length; ++i) {
            MultipartFile file = files[i];
            String fileName = file.getOriginalFilename();
            try {
                albumService.savePhoto(album, fileName, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "album";
    }

    @RequestMapping(value={"/albums/img/{imageName}.{ext}"}, method = GET)
    @ResponseBody
    public byte[] showImage(Model model, @PathVariable String imageName, @PathVariable String ext) {
        Album album = (Album) session().getAttribute("album");
        Photo photo = album.getPhoto(imageName + "." + ext);
        return photo.getData();
    }

    private Album getAlbum(String albumName) {
        User user = (User) session().getAttribute("user");
        return user.getAlbum(albumName);
    }
}
