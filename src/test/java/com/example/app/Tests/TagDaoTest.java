package com.example.app.Tests;

import com.example.app.Daos.TagDao;
import com.example.app.Models.Photo;
import com.example.app.Models.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xuan Kong, Yuting Chen
 * @Date 2019-12-04.
 */
class TagDaoTest {

  TagDao tagDao = new TagDao();

  @Test
  void createTag() {
    //test add a new tag
    Tag tag = new Tag("red");

    tagDao.createTag(tag);
    Tag res = tagDao.findTagByName("red");
    //test if name is equal
    assertEquals("red", res.getName());

    //delete test the sample tag for reusable test
    tagDao.deleteTag("red");
  }

  @Test
  void findAllTags() {
    //compare with sample data that have been imported at beginning
    List<Tag> tagList;
    List<String> tnames = new ArrayList<>();
    tnames.add("A");
    tnames.add("B");
    tnames.add("C");
    tnames.add("D");
    tagList = tagDao.findAllTags();
    int index = 0;
    for (Tag t : tagList) {
      assertEquals(tnames.get(index), t.getName());
      index++;
    }
  }

  @Test
  void findAllPhotosFromTagByName() {
    List<Photo> photoList = tagDao.findAllPhotosFromTagByName("study");
    List<String> expectedPhotoList = new ArrayList<>();
    expectedPhotoList.add("IMG_001.jpg");
    expectedPhotoList.add("IMG_002.jpg");
    expectedPhotoList.add("IMG_003.jpg");
    expectedPhotoList.add("IMG_004.jpg");
    for (int i = 0; i < photoList.size(); i++) {
      assertEquals(expectedPhotoList.get(i), photoList.get(i).getName());
    }

  }

  @Test
  void findTagByName() {
    Tag tag = tagDao.findTagByName("A");
    Tag expectedTag = new Tag("A");
    expectedTag.setCreateDate(new java.sql.Date(new Date().getTime()).toString());
    assertEquals(expectedTag.getName(), tag.getName());
    assertEquals(expectedTag.getCreateDate(), tag.getCreateDate());
  }

  @Test
  void updateTag() {
    tagDao.updateTag("Z", "A");
    Tag t = tagDao.findTagByName("Z");
    assertEquals("Z", t.getName());
    assertEquals(new java.sql.Date(new Date().getTime()).toString(), t.getCreateDate());

    //rollback the data
    tagDao.updateTag("A", "Z");
  }

  @Test
  void deleteTag() {
    //create a new category called newCate
    tagDao.createTag(new Tag("brown"));

    //then delete newCate
    tagDao.deleteTag("brown");

    //find "brown" exists in the list of all tags
    boolean isExist = false;

    List<Tag> categoryList = tagDao.findAllTags();
    for (Tag t : categoryList) {
      if (t.getName().equals("brown")) {
        isExist = true;
      }
    }
    assertEquals(false, isExist);
  }

  @Test
  void addPhotoToTag() {
    tagDao.addPhotoToTag("IMG_007.jpg", "A");
    List<Photo> photoList = tagDao.findAllPhotosFromTagByName("A");

    boolean isExist = false;

    for (Photo p : photoList) {
      if (p.getName().equals("IMG_007.jpg")) {
        isExist = true;
      }
    }
    assertEquals(true, isExist);
  }

}