package com.example.app.Views;

import com.example.app.Daos.PhotoDao;
import com.example.app.Models.Photo;

import java.util.List;

/**
 * @author Xuan Kong
 * @Date 2019-12-03.
 */
public class PhotoView {

  PhotoDao photoDao = new PhotoDao();


  public static void displayPhotoInfo(Photo photo) {
    System.out.println("url: " + photo.getUrl() + ", name: "
      + photo.getName() + ", crate_date:" + photo.getCreateDate() + ", width: "
      + photo.getWidth() + "px" + ", height: " + photo.getHeight() + "px" + ", focal length: "
      + photo.getFocalLength() + "mm" + ", f number: " + photo.getfNumber() + ", ISO: " + photo.getIso());
  }

  public void displayAllPhoto(List<Photo> photoList) {
    for (Photo p : photoList) {
      displayPhotoInfo(p);
    }
  }


  public static void main(String[] args) {
    PhotoDao photoDao = new PhotoDao();
    Photo photo = new Photo();
    photo.setName("IMG_012.jpg");
    photo.setHeight(800);
    photo.setWidth(600);
    photo.setFocalLength(35);
    photo.setfNumber("f/2.5");
    photo.setIso(6400);

  }
}
