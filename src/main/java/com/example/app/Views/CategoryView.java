package com.example.app.Views;

import com.example.app.Daos.CategoryDao;
import com.example.app.Models.Category;
import com.example.app.Models.Photo;

import java.util.List;
import java.util.Scanner;

/**
 * @author Xuan Kong
 * @Date 2019-12-03.
 */
public class CategoryView {

  CategoryDao categoryDao;
  PhotoView photoView;

  private String categoryPageOptions = "Please choose from the following options:\n" +
    "1. Display all categories\n" +
    "2. Display photos from a category\n" +
    "3. Create a category\n" +
    "4. Delete a category\n" +
    "5. Update a category's name\n" +
    "6. Add a photo to a category\n" +
    "7. quit\n";


  public CategoryView() {
    categoryDao = new CategoryDao();
    photoView = new PhotoView();
  }


  public void displayCategoryInfo(Category category) {
    System.out.println(category.getName() + ", " + category.getCreateDate());
  }


  public void displayAllCategories() {
    List<Category> categoryList = categoryDao.findAllCategories();
    for (Category c : categoryList) {
      displayCategoryInfo(c);
    }
  }


  public void displayAllPhotosFromCategory() {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the name of the category. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    List<Photo> photoList = categoryDao.findAllPhotosFromCategoryByName(cname);
    photoView.displayAllPhoto(photoList);
    while (true) {
      System.out.println("Enter the name of the category. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      photoList = categoryDao.findAllPhotosFromCategoryByName(cname);
      photoView.displayAllPhoto(photoList);
    }


  }

  public void createACategoryView() {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the name of the category. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    boolean isSuccess = false;
    Category category = new Category(cname);
    isSuccess = categoryDao.createCategory(category);
    while (!isSuccess) {

    }
  }

  public void goToCategoryMode() {
    System.out.println("In Category Mode!");
    System.out.println(categoryPageOptions);
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String input = in.next();
      switch (input) {
        case "1":
          displayAllCategories();
          System.out.println(categoryPageOptions);
          break;
        case "2":
          displayAllPhotosFromCategory();
          System.out.println(categoryPageOptions);
          break;
        case "3":
          break;
        case "4":
          break;
        case "5":
          break;
        case "6":
          break;
        case "7":
          return;
        default:
          System.out.println("invalid input, please try it again!");
      }

    }
  }


}
