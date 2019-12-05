package com.example.app.Tests;

import com.example.app.Daos.PhotoDao;
import com.example.app.Daos.TagDao;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author Xuan Kong
 * @Date 2019-12-04.
 */
public class Main {
  @Test
  public static void main(String[] args) {
    CategoryDaoTest categoryDaoTest = new CategoryDaoTest();
    PhotoDaoTest photoDaoTest = new PhotoDaoTest();
    TagDaoTest tagDaoTest = new TagDaoTest();

  }
}
