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
          "1. Display all categories\n" + //done
          "2. Display photos from a category\n" + // done
          "3. Create a category\n" + //done
          "4. Delete a category\n" +
          "5. Update a category's name\n" +
          "6. Add a photo to a category\n" +
          "7. Go back to main page";


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
    System.out.println("In Category Mode!\nEnter the name of the category. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    List<Photo> photoList = categoryDao.findAllPhotosFromCategoryByName(cname);
    photoView.displayAllPhoto(photoList);
    while (true) {
      System.out.println("In Category Mode!\nEnter the name of the category. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      photoList = categoryDao.findAllPhotosFromCategoryByName(cname);
      photoView.displayAllPhoto(photoList);
    }
  }


  public void createCategoryView() {
    Scanner in = new Scanner(System.in);
    System.out.println("In Category Mode!\nEnter the name of the category you want to create. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    boolean isSuccess = false;
    Category category = new Category(cname);
    isSuccess = categoryDao.createCategory(category);
    if (isSuccess) {

      System.out.println("New category:" + cname + "created!");
    }
    while (!isSuccess) {
      System.out.println("In Category Mode!\nEnter the name of the category you want to create. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      category = new Category(cname);
      isSuccess = categoryDao.createCategory(category);
      if (isSuccess) {
        System.out.println("New category:" + cname + " created!");
      }
    }
  }

  public void deleteCategoryView() {
    Scanner in = new Scanner(System.in);
    System.out.println("In Category Mode!\nEnter the name of the category you want to delete. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    boolean isSuccess = false;
    isSuccess = categoryDao.deleteCategory(cname);
    if (isSuccess) {
      System.out.println("Category: " + cname + " deleted!");
    }
    while (!isSuccess) {
      System.out.println("In Category Mode!\nEnter the name of the category you want to delete. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      isSuccess = categoryDao.deleteCategory(cname);
      if (isSuccess) {
        System.out.println("Category: " + cname + " deleted!");
      }
    }
  }

  public void updateCategoryView() {
    Scanner in = new Scanner(System.in);
    System.out.println("In Category Mode!\nEnter the name of the category you want to update. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    System.out.println("In Category Mode!\nEnter the new name of the category. (Enter q to go back)");
    String newName = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }

    boolean isSuccess = false;
    isSuccess = categoryDao.updateCategory(newName, cname);
    if (isSuccess) {
      System.out.println("Category's name: " + cname + " updated to " + newName);
    }
    while (!isSuccess) {
      System.out.println("In Category Mode!\nEnter the name of the category you want to update. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }

      System.out.println("In Category Mode!\nEnter the new name of the category. (Enter q to go back)");
      newName = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      isSuccess = categoryDao.updateCategory(newName, cname);
      if (isSuccess) {
        System.out.println("Category's name: " + cname + " updated to " + newName);
      }
    }
  }

  public void addPhotoToCategory() {
    Scanner in = new Scanner(System.in);
    System.out.println("In Category Mode!\nSelect a category. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    System.out.println("In Category Mode!\nSelect a photo. (Enter q to go back)");
    String pName = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }

    boolean isSuccess = false;
    isSuccess = categoryDao.addPhotoToCategory(pName, cname);
    if (isSuccess) {
      System.out.println("Image: " + pName + " added to category: " + cname);
    }
    while (!isSuccess) {
      System.out.println("In Category Mode!\nSelect a category. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }

      System.out.println("In Category Mode!\nSelect a photo. (Enter q to go back)");
      pName = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      isSuccess = categoryDao.addPhotoToCategory(pName, cname);
      if (isSuccess) {
        System.out.println("Image: " + pName + " added to category: " + cname);
      }
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
          createCategoryView();
          System.out.println(categoryPageOptions);
          break;
        case "4":
          deleteCategoryView();
          System.out.println(categoryPageOptions);
          break;
        case "5":
          updateCategoryView();
          System.out.println(categoryPageOptions);
          break;
        case "6":
          addPhotoToCategory();
          System.out.println(categoryPageOptions);
          break;
        case "7":
          System.out.println("Left category mode.");
          return;
        default:
          System.out.println("invalid input, please try it again!");
      }

    }
  }


}
