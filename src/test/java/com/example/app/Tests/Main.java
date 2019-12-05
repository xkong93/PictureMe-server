package com.example.app.Tests;

import com.example.app.Daos.PhotoDao;
import com.example.app.Daos.TagDao;

import org.junit.jupiter.api.Test;

/**
 * @author Xuan Kong
 * @Date 2019-12-04.
 */
public class Main {
  public static void main(String[] args) {
    CategoryDaoTest categoryDaoTest = new CategoryDaoTest();
    PhotoDaoTest photoDaoTest = new PhotoDaoTest();
    TagDaoTest tagDaoTest = new TagDaoTest();
  }
}
