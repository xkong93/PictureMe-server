package com.example.app.Controllers;

import com.example.app.Daos.PhotoDao;
import com.example.app.Models.Photo;

/**
 * @author Xuan Kong
 * @Date 2019-11-18.
 */
public class PhotoController {

  PhotoDao photoDao = new PhotoDao();
  public void createPhoto(Photo photo) {
    photoDao.createPhoto(photo);
  }
}
