package com.album.controllers;

import com.album.exceptions.ResourceNotFoundException;
import com.album.model.api.Album;
import com.album.model.api.User;
import com.album.service.api.AlbumService;
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
    public String processGet(Model model, @PathVariable String albumName) throws ResourceNotFoundException {
        Album album = getAlbum(albumName);
        if (album == null)
            throw new ResourceNotFoundException();

        session().setAttribute("album", album);
        return "album";
    }

    @RequestMapping(value={"/albums/{albumName}"}, method = POST)
    public String processPost(Model model, @PathVariable String albumName,
                              @RequestParam("files") MultipartFile[] files) throws ResourceNotFoundException {
        Album album = getAlbum(albumName);
        if (album == null)
            throw new ResourceNotFoundException();

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

    @RequestMapping(value={"/albums/img/{imageId}"}, method = GET)
    @ResponseBody
    public byte[] showImage(Model model, @PathVariable String imageId) throws ResourceNotFoundException {
        try {
            int photoId = Integer.parseInt(imageId);
            byte[] photo = albumService.loadPhoto(photoId);
            return photo;
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value={"/albums/img/small/{imageId}"}, method = GET)
    @ResponseBody
    public byte[] showPreview(Model model, @PathVariable String imageId) throws ResourceNotFoundException {
        try {
            int photoId = Integer.parseInt(imageId);
            byte[] photo = albumService.loadPreview(photoId);
            return photo;
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException();
        }
    }

    private Album getAlbum(String albumName) {
        User user = (User) session().getAttribute("user");
        return albumService.loadAlbum(user, albumName);
    }
}
