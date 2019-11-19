package com.example.app.Models;

import java.util.Date;
import java.util.List;

/**
 * @author Xuan Kong
 * @Date 2019-11-17.
 */
public class Category {
  private Date createDate;
  private String name;
  private List<Photo> photoList;

  public Category() {

  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Photo> getPhotoList() {
    return photoList;
  }

  public void setPhotoList(List<Photo> photoList) {
    this.photoList = photoList;
  }


}
