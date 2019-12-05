package com.example.app.Tests;

import com.example.app.Daos.CategoryDao;
import com.example.app.Models.Category;
import com.example.app.Models.Photo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Xuan Kong
 * @Date 2019-12-04. Category sample data: '1', '2019-12-04', 'study' '2', '2019-12-04', 'life'
 * '3','2019-12-04', 'sport' '4','2019-12-04', 'family' '5', '2019-12-04', 'travel'
 */
class CategoryDaoTest {


  CategoryDao categoryDao = new CategoryDao();


  @Test
  void createCategory() {

    //test add a new category
    Category category = new Category("abc");
    categoryDao.createCategory(category);
    Category res = categoryDao.findCategoryByName("abc");
    //test if name is equal
    assertEquals("abc", res.getName());

    //delete test the sample category for reusable test
    categoryDao.deleteCategory("abc");

  }

  @Test
  void findAllCategories() {

    //compare with sample data that have been imported at beginning
    List<Category> categoryList = new ArrayList<>();
    List<String> cnames = new ArrayList<>();
    cnames.add("study");
    cnames.add("life");
    cnames.add("sport");
    cnames.add("family");
    cnames.add("travel");
    categoryList = categoryDao.findAllCategories();
    int index = 0;
    for (Category c : categoryList) {
      assertEquals(cnames.get(index), c.getName());
      index++;
    }

  }

  @Test
  void findAllPhotosFromCategoryByName() {
    List<Photo> photoList = categoryDao.findAllPhotosFromCategoryByName("study");
    List<String> expectedPhotoList = new ArrayList<>();
    expectedPhotoList.add("IMG_001.jpg");
    expectedPhotoList.add("IMG_002.jpg");
    expectedPhotoList.add("IMG_003.jpg");
    expectedPhotoList.add("IMG_004.jpg");
    for (int i = 0; i < photoList.size(); i++) {
      assertEquals(expectedPhotoList.get(i),photoList.get(i).getName());
    }
  }

  @Test
  void findCategoryByName() {

  }

  @Test
  void updateCategory() {
  }

  @Test
  void deleteCategory() {
  }

  @Test
  void addPhotoToCategory() {
  }
}