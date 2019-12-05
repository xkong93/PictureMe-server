package com.example.app.Views;

import com.example.app.Daos.TagDao;
import com.example.app.Models.Photo;
import com.example.app.Models.Tag;

import java.util.List;
import java.util.Scanner;

/**
 * @author Xuan Kong
 * @Date 2019-12-03.
 */
public class TagView {
  TagDao tagDao;
  PhotoView photoView;

  private String tagPageOptions = "Please choose from the following options:\n" +
          "1. Display all tags\n" + //done
          "2. Display photos from a tag\n" + // done
          "3. Create a tag\n" + //done
          "4. Delete a tag\n" +
          "5. Update a tag's name\n" +
          "6. Add a photo to a tag\n" +
          "7. Go back to main page";


  public TagView() {
    tagDao = new TagDao();
    photoView = new PhotoView();
  }


  public void displayTagInfo(Tag tag) {
    System.out.println(tag.getName() + ", " + tag.getCreateDate());
  }


  public void displayAllTags() {
    List<Tag> tagList = tagDao.findAllTags();
    for (Tag c : tagList) {
      displayTagInfo(c);
    }
  }


  public void displayAllPhotosFromTag() {
    Scanner in = new Scanner(System.in);
    System.out.println("In Tag Mode!\nEnter the name of the tag. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    List<Photo> photoList = tagDao.findAllPhotosFromTagByName(cname);
    photoView.displayAllPhoto(photoList);
    while (true) {
      System.out.println("In Tag Mode!\nEnter the name of the tag. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      photoList = tagDao.findAllPhotosFromTagByName(cname);
      photoView.displayAllPhoto(photoList);
    }
  }


  public void createTagView() {
    Scanner in = new Scanner(System.in);
    System.out.println("In Tag Mode!\nEnter the name of the tag you want to create. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    boolean isSuccess = false;
    Tag tag = new Tag(cname);
    isSuccess = tagDao.createTag(tag);
    if (isSuccess) {

      System.out.println("New tag:" + cname + "created!");
    }
    while (!isSuccess) {
      System.out.println("In Tag Mode!\nEnter the name of the tag you want to create. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      tag = new Tag(cname);
      isSuccess = tagDao.createTag(tag);
      if (isSuccess) {
        System.out.println("New tag:" + cname + " created!");
      }
    }
  }

  public void deleteTagView() {
    Scanner in = new Scanner(System.in);
    System.out.println("In Tag Mode!\nEnter the name of the tag you want to delete. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    boolean isSuccess = false;
    isSuccess = tagDao.deleteTag(cname);
    if (isSuccess) {
      System.out.println("Tag: " + cname + " deleted!");
    }
    while (!isSuccess) {
      System.out.println("In Tag Mode!\nEnter the name of the tag you want to delete. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      isSuccess = tagDao.deleteTag(cname);
      if (isSuccess) {
        System.out.println("Tag: " + cname + " deleted!");
      }
    }
  }

  public void updateTagView() {
    Scanner in = new Scanner(System.in);
    System.out.println("In Tag Mode!\nEnter the name of the tag you want to update. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    System.out.println("In Tag Mode!\nEnter the new name of the tag. (Enter q to go back)");
    String newName = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }

    boolean isSuccess = false;
    isSuccess = tagDao.updateTag(newName, cname);
    if (isSuccess) {
      System.out.println("Tag's name: " + cname + " updated to " + newName);
    }
    while (!isSuccess) {
      System.out.println("In Tag Mode!\nEnter the name of the tag you want to update. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }

      System.out.println("In Tag Mode!\nEnter the new name of the tag. (Enter q to go back)");
      newName = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      isSuccess = tagDao.updateTag(newName, cname);
      if (isSuccess) {
        System.out.println("Tag's name: " + cname + " updated to " + newName);
      }
    }
  }

  public void addPhotoToTag() {
    Scanner in = new Scanner(System.in);
    System.out.println("In Tag Mode!\nSelect a tag. (Enter q to go back)");
    String cname = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }
    System.out.println("In Tag Mode!\nSelect a photo. (Enter q to go back)");
    String pName = in.next();
    if (cname.toLowerCase().equals("q")) {
      return;
    }

    boolean isSuccess = false;
    isSuccess = tagDao.addPhotoToTag(pName, cname);
    if (isSuccess) {
      System.out.println("Image: " + pName + " added to tag: " + cname);
    }
    while (!isSuccess) {
      System.out.println("In Tag Mode!\nSelect a tag. (Enter q to go back)");
      cname = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }

      System.out.println("In Tag Mode!\nSelect a photo. (Enter q to go back)");
      pName = in.next();
      if (cname.toLowerCase().equals("q")) {
        return;
      }
      isSuccess = tagDao.addPhotoToTag(pName, cname);
      if (isSuccess) {
        System.out.println("Image: " + pName + " added to tag: " + cname);
      }
    }
  }

  public void goToTagMode() {
    System.out.println("In Tag Mode!");
    System.out.println(tagPageOptions);
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String input = in.next();
      switch (input) {
        case "1":
          displayAllTags();
          System.out.println(tagPageOptions);
          break;
        case "2":
          displayAllPhotosFromTag();
          System.out.println(tagPageOptions);
          break;
        case "3":
          createTagView();
          System.out.println(tagPageOptions);
          break;
        case "4":
          deleteTagView();
          System.out.println(tagPageOptions);
          break;
        case "5":
          updateTagView();
          System.out.println(tagPageOptions);
          break;
        case "6":
          addPhotoToTag();
          System.out.println(tagPageOptions);
          break;
        case "7":
          System.out.println("Left tag mode.");
          return;
        default:
          System.out.println("invalid input, please try it again!");
      }

    }
  }
}
