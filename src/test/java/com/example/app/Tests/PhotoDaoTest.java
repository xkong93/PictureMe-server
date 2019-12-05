package com.example.app.Tests;

import com.example.app.Daos.PhotoDao;
import com.example.app.Models.Photo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuan Kong
 * @Date 2019-12-04.
 */
class PhotoDaoTest {
  PhotoDao photoDao = new PhotoDao();
  @Test
  void createPhoto() {

    //test normal photo params
    Photo photo = new Photo("test.jpg", "2017-01-30", 400, 600, 20, "f/1.8", 400);
    photoDao.createPhoto(photo);
    Photo resP = photoDao.findPhotoByName("test.jpg");
    assertEquals("test.jpg",resP.getName());

    //rollback
    photoDao.deletePhoto("test.jpg");

    boolean res = false;
    //test when height is negative
    Photo photo2 = new Photo("test.jpg", "2017-01-30", -1, 600, 20, "f/1.8", 400);
    res = photoDao.createPhoto(photo2);
    assertEquals(false,res);

    //test when width is negative
    Photo photo3 = new Photo("test.jpg", "2017-01-30", 400, -600, 20, "f/1.8", 400);
    res = photoDao.createPhoto(photo3);
    assertEquals(false,res);


        //test when width is negative
    Photo photo4 = new Photo("test.jpg", "2017-01-30", 400, -600, 20, "f/1.8", 400);
    res = photoDao.createPhoto(photo3);
    assertEquals(false,res);

        //test when width is negative
    Photo photo5 = new Photo("test.jpg", "2017-01-30", 400, -600, 20, "f/1.8", 400);
    res = photoDao.createPhoto(photo3);
    assertEquals(false,res);

        //test when width is negative
    Photo photo6 = new Photo("test.jpg", "2017-01-30", 400, -600, 20, "f/1.8", 400);
    res = photoDao.createPhoto(photo3);
    assertEquals(false,res);
  }

  @Test
  void findPhotoByName() {
  }

  @Test
  void findAllPhotos() {
  }

  @Test
  void deletePhoto() {
  }

  @Test
  void updatePhotoName() {
  }
}