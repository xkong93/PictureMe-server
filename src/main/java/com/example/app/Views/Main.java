package com.example.app.Views;

import java.util.Scanner;

/**
 * @author Xuan Kong
 * @Date 2019-12-03.
 */
public class Main {

  public static String mainPageOptions = "Please choose from the following options:\n" +
    "1. Display all photos\n" +
    "2. Go to category mode\n" +
    "3. Go to tag mode\n" +
    "4. Delete existing photos\n" +
    "5. Create a photo\n" +
    "6. Update a photo info\n" +
    "7. Go back to the main page\n";

  public static void main(String[] args) {


    CategoryView categoryView = new CategoryView();
    PhotoView photoView = new PhotoView();
    TagView tagView = new TagView();

    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to the PictureMe photo management system!");
    System.out.println(mainPageOptions);
    while (in.hasNext()) {
      String input = in.next();
      switch (input) {
        case "1":
          photoView.displayAllPhoto();
          System.out.println(mainPageOptions);
          break;
        case "2":
          categoryView.goToCategoryMode();
          break;
        case "3":

          break;
        case "4":

          System.out.println(mainPageOptions);
          break;
        case "5":

          System.out.println(mainPageOptions);
          break;
        case "6":
          System.out.println(mainPageOptions);
          break;
        case "7":
          System.out.println(mainPageOptions);

          return;
        default:
          System.out.println("invalid input, please try it again!");
      }
    }
  }


  public static void mainOptions() {

  }

}
