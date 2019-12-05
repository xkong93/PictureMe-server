package com.example.app.Tests;

import com.example.app.Daos.PhotoDao;
import com.example.app.Models.Photo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    assertEquals("test.jpg", resP.getName());

    //rollback
    photoDao.deletePhoto("test.jpg");

    boolean res = false;
    //test when height is negative
    Photo photo2 = new Photo("test.jpg", "2017-01-30", -1, 600, 20, "f/1.8", 400);
    res = photoDao.createPhoto(photo2);
    assertEquals(false, res);

    //test when width is negative
    Photo photo3 = new Photo("test.jpg", "2017-01-30", 400, -600, 20, "f/1.8", 400);
    res = photoDao.createPhoto(photo3);
    assertEquals(false, res);


    //test when focal_length is not valid
    Photo photo4 = new Photo("test.jpg", "2017-01-30", 400, 600, -1, "f/1.8", 400);
    res = photoDao.createPhoto(photo4);
    assertEquals(false, res);

    //test when f_number is not valid
    Photo photo5 = new Photo("test.jpg", "2017-01-30", 400, 600, 20, "f/11.11", 400);
    res = photoDao.createPhoto(photo5);
    assertEquals(false, res);

    //test when iso is not valid
    Photo photo6 = new Photo("test.jpg", "2017-01-30", 400, 600, 20, "f/1.8", 5);
    res = photoDao.createPhoto(photo6);
    assertEquals(false, res);
  }

  @Test
  void findPhotoByName() {

    Photo photo = photoDao.findPhotoByName("IMG_001.jpg");
    assertEquals("IMG_001.jpg", photo.getName());
  }


  @Test
  void findAllPhotos() {
    List<Photo> photoList = photoDao.findAllPhotos();
    List<String> expectedPhotoList = new ArrayList<>();
    expectedPhotoList.add("IMG_001.jpg");
    expectedPhotoList.add("IMG_002.jpg");
    expectedPhotoList.add("IMG_003.jpg");
    expectedPhotoList.add("IMG_004.jpg");
    expectedPhotoList.add("IMG_005.jpg");
    expectedPhotoList.add("IMG_006.jpg");
    expectedPhotoList.add("IMG_007.jpg");
    expectedPhotoList.add("IMG_008.jpg");
    expectedPhotoList.add("IMG_009.jpg");
    expectedPhotoList.add("IMG_010.jpg");
    expectedPhotoList.add("IMG_011.jpg");

    for (int i = 0; i < photoList.size(); i++) {
      assertEquals(expectedPhotoList.get(i), photoList.get(i).getName());
    }


  }

  @Test
  void deletePhoto() {
    Photo photo = new Photo("test.jpg", "2017-01-30", 400, 600, 20, "f/1.8", 400);
    photoDao.createPhoto(photo);
    photoDao.deletePhoto("test.jpg");

    boolean isExist = false;
    List<Photo> photoList = photoDao.findAllPhotos();
    for (Photo p : photoList) {
      if (p.getName().equals("test.jpg")) {
        isExist = true;
      }
    }
    assertEquals(false, isExist);

  }

  @Test
  void updatePhotoName() {
    photoDao.updatePhotoName("test99.jpg", "IMG_001.jpg");
    Photo c = photoDao.findPhotoByName("test99.jpg");
    assertEquals("test99.jpg", c.getName());

    //rollback the data
    photoDao.updatePhotoName("IMG_001.jpg", "test99.jpg");
  }
}