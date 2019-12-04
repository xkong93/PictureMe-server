package com.example.app.Views;

import com.example.app.Daos.CategoryDao;
import com.example.app.Daos.PhotoDao;

import java.util.Scanner;

/**
 * @author Xuan Kong
 * @Date 2019-12-03.
 */
public class Main {


  public static void main(String[] args) {


    CategoryView categoryView = new CategoryView();
    PhotoView photoView = new PhotoView();
    TagView tagView = new TagView();
    PhotoDao photoDao = new PhotoDao();
    CategoryDao categoryDao = new CategoryDao();


    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to the PictureMe photo management system!");
    System.out.println("Please choose from the following options:\n" +
      "1. Display all photos\n" + //done
      "2. Display all categories\n" + //done
      "3. Display all tags\n" + //done
      "4. Delete existing photos\n" + //done
      "5. Delete existing categories\n" +
      "6. Delete existing tags\n" +
      "7. quit");

    while (in.hasNext()) {
      String input = in.next();
      switch (input) {
        case "1":
          photoDao.findAllPhotos();
          break;
        case "2":
          break;
        case "3":
          break;
        case "4":
          break;
        case "5":
          break;
        case "6":
          return;
        default:
          System.out.println("invalid input, please try it again!");
      }
    }
  }

}
