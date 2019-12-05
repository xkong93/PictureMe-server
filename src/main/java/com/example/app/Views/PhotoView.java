package com.example.app.Views;

import com.example.app.Daos.PhotoDao;
import com.example.app.Models.Photo;

import java.util.List;
import java.util.Scanner;

/**
 * @author Xuan Kong, Yuting Chen
 * @Date 2019-11-26.
 */
public class PhotoView {

  PhotoDao photoDao;

  public PhotoView() {
    photoDao = new PhotoDao();
  }

  public static void displayPhotoInfo(Photo photo) {
    System.out.println("url: " + photo.getUrl() + ", name: "
            + photo.getName() + ", crate_date:" + photo.getCreateDate() + ", width: "
            + photo.getWidth() + "px" + ", height: " + photo.getHeight() + "px" + ", focal length: "
            + photo.getFocalLength() + "mm" + ", f number: " + photo.getfNumber() + ", ISO: " + photo.getIso());
  }

  public void displayAllPhoto() {
    List<Photo> photoList = photoDao.findAllPhotos();
//    if (photoList.size() == 0) {
//      System.out.println("No photos");
//    }
    for (Photo p : photoList) {
      displayPhotoInfo(p);
    }
  }

  public void displayAllPhoto(List<Photo> photoList) {
    if (photoList.size() == 0) {
      System.out.println("No photos");
    }
    for (Photo p : photoList) {
      displayPhotoInfo(p);
    }
  }

  public void createPhotoView() {
    Scanner in = new Scanner(System.in);
    boolean isSuccess = false;
    System.out.println("Enter name, width, height, focal_length(value > 0), f_number(f/0.1 ~ f9.9), iso(25 ~ 1024000) with delimiter \",\" Enter q to go back)");
    System.out.println("Example: IMG001.jpg,600,500,200,f/1.1,100");
    String input = in.next();
    if (input.toLowerCase().equals("q")) {
      return;
    }
    String[] words = input.split(",");
    while (words.length != 6) {
      System.out.println("Photo info incomplete. Please re-enter!");
      System.out.println("Enter name, width, height, focal_length(value > 0), f_number(f/0.1 ~ f9.9), iso(25 ~ 1024000) with delimiter \",\" Enter q to go back)");
      System.out.println("Example: IMG001.jpg,600,500,200,f/1.1,100");
      input = in.next();
      if (input.toLowerCase().equals("q")) {
        return;
      }
      words = input.split(",");
    }

    String pName = words[0];
    Integer width = Integer.parseInt(words[1]);
    Integer height = Integer.parseInt(words[2]);
    Integer focal_length = Integer.parseInt(words[3]);
    String f_number = words[4];
    Integer iso = Integer.parseInt(words[5]);
    Photo photo = new Photo(pName, width, height, focal_length, f_number, iso);
    isSuccess = photoDao.createPhoto(photo);
    if (isSuccess) {
      System.out.println("New photo: " + pName + " created!");
    }
    while (!isSuccess) {
      System.out.println("Enter name, width, height, focal_length(value > 0), f_number(f/0.1 ~ f9.9), iso(25 ~ 1024000) with delimiter \",\" Enter q to go back)");
      System.out.println("Example: IMG001.jpg,600,500,200,f/1.1,100");
      input = in.next();
      if (input.toLowerCase().equals("q")) {
        return;
      }
      words = input.split(",");
      while (words.length != 6) {
        System.out.println("Photo info incomplete. Please re-enter!");
        System.out.println("Enter name, width, height, focal_length(value > 0), f_number(f/0.1 ~ f9.9), iso(25 ~ 1024000) with delimiter \",\" Enter q to go back)");
        System.out.println("Example: IMG001.jpg,600,500,200,f/1.1,100");
        input = in.next();
        if (input.toLowerCase().equals("q")) {
          return;
        }
        words = input.split(",");
      }
      pName = words[0];
      width = Integer.parseInt(words[1]);
      height = Integer.parseInt(words[2]);
      focal_length = Integer.parseInt(words[3]);
      f_number = words[4];
      iso = Integer.parseInt(words[5]);
      photo = new Photo(pName, width, height, focal_length, f_number, iso);

      isSuccess = photoDao.createPhoto(photo);
      if (isSuccess) {
        System.out.println("New photo: " + pName + " created!");
      }
    }


  }

  public void deletePhotoView() {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the name of the photo you want to delete. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    boolean isSuccess = false;
    isSuccess = photoDao.deletePhoto(cname);
    if (isSuccess) {
      System.out.println("Photo: " + cname + " deleted!");
    }
    while (!isSuccess) {
      System.out.println("Enter the name of the photo you want to delete. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      isSuccess = photoDao.deletePhoto(cname);
      if (isSuccess) {
        System.out.println("Photo: " + cname + " deleted!");
      }
    }
  }

  public void updatePhotoView() {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the name of the photo you want to update. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    System.out.println("Enter the new name of the photo. (Enter q to go back)");
    String newName = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }

    boolean isSuccess = false;
    isSuccess = photoDao.updatePhotoName(newName, cname);
    if (isSuccess) {
      System.out.println("Photo's name: " + cname + " updated to " + newName);
    }
    while (!isSuccess) {
      System.out.println("Enter the name of the photo you want to update. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }

      System.out.println("Enter the new name of the photo. (Enter q to go back)");
      newName = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      isSuccess = photoDao.updatePhotoName(newName, cname);
      if (isSuccess) {
        System.out.println("Photo's name: " + cname + " updated to " + newName);
      }
    }
  }


//  public static void main(String[] args) {
////    PhotoDao photoDao = new PhotoDao();
////    Photo photo = new Photo();
////    photo.setName("IMG_012.jpg");
////    photo.setHeight(800);
////    photo.setWidth(600);
////    photo.setFocalLength(35);
////    photo.setfNumber("f/2.5");
////    photo.setIso(6400);
//  }
}
