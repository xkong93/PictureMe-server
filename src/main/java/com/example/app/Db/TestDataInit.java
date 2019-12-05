package com.example.app.Db;

import com.example.app.Daos.CategoryDao;
import com.example.app.Daos.PhotoDao;
import com.example.app.Daos.TagDao;
import com.example.app.Models.Category;
import com.example.app.Models.Photo;
import com.example.app.Models.Tag;

/**
 * @author Xuan Kong, Yuting Chen
 * @Date 2019-11-26.
 */
public class TestDataInit {


  PhotoDao photoDao = new PhotoDao();
  CategoryDao categoryDao = new CategoryDao();
  TagDao tagDao = new TagDao();

  public void insertPhotoTestData() {
    photoDao.createPhoto(new Photo("IMG_001.jpg", "2008-01-30", 400, 600, 20, "f/1.8", 400));
    photoDao.createPhoto(new Photo("IMG_002.jpg", "2013-01-31", 200, 900, 100, "f/3.5", 1500));
    photoDao.createPhoto(new Photo("IMG_003.jpg", "2014-02-23", 150, 500, 55, "f/4.5", 522));
    photoDao.createPhoto(new Photo("IMG_004.jpg", "2019-02-25", 300, 900, 54, "f/5.5", 1600));
    photoDao.createPhoto(new Photo("IMG_005.jpg", "2013-01-31", 600, 900, 88, "f/7.5", 200));
    photoDao.createPhoto(new Photo("IMG_006.jpg", "2014-06-26", 234, 900, 44, "f/8.6", 800));
    photoDao.createPhoto(new Photo("IMG_007.jpg", "2014-02-18", 345, 900, 32, "f/2.5", 900));
    photoDao.createPhoto(new Photo("IMG_008.jpg", "2019-05-03", 323, 235, 20, "f/6.2", 700));
    photoDao.createPhoto(new Photo("IMG_009.jpg", "2019-05-04", 123, 323, 45, "f/2.8", 550));
    photoDao.createPhoto(new Photo("IMG_010.jpg", "2018-05-08", 222, 200, 78, "f/3.1", 5000));
    photoDao.createPhoto(new Photo("IMG_011.jpg", "2012-05-03", 988, 400, 32, "f/3.0", 102400));
    System.out.println("sample photos data imported!");
  }

  public void insertCategoryTestData() {
    categoryDao.createCategory(new Category("study"));
    categoryDao.createCategory(new Category("life"));
    categoryDao.createCategory(new Category("sport"));
    categoryDao.createCategory(new Category("family"));
    categoryDao.createCategory(new Category("travel"));
    System.out.println("sample categories data imported!");

  }

  public void insertPhotoCategoryTestData() {
    categoryDao.addPhotoToCategory("IMG_001.jpg", "study");
    categoryDao.addPhotoToCategory("IMG_002.jpg", "study");
    categoryDao.addPhotoToCategory("IMG_003.jpg", "study");
    categoryDao.addPhotoToCategory("IMG_004.jpg", "study");
    categoryDao.addPhotoToCategory("IMG_005.jpg", "life");
    categoryDao.addPhotoToCategory("IMG_006.jpg", "life");
        System.out.println("sample photos_categories data imported!");

  }

  public void insertTagTestData() {
    tagDao.createTag(new Tag("A"));
    tagDao.createTag(new Tag("B"));
    tagDao.createTag(new Tag("C"));
    tagDao.createTag(new Tag("D"));
  }

  public void insertPhotoTagTestData() {
    tagDao.addPhotoToTag("IMG_001.jpg", "B");
    tagDao.addPhotoToTag("IMG_002.jpg", "B");
    tagDao.addPhotoToTag("IMG_003.jpg", "C");
    tagDao.addPhotoToTag("IMG_004.jpg", "D");
    tagDao.addPhotoToTag("IMG_005.jpg", "A");
    tagDao.addPhotoToTag("IMG_006.jpg", "A");
  }


}
