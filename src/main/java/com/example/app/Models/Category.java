package com.example.app.Models;

import org.apache.tomcat.jni.Local;

import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;


/**
 * @author Xuan Kong
 * @Date 2019-11-17.
 */
public class Category {

  private int cid;
  private String createDate;
  private String name;
  private List<Photo> photoList;

  public Category() {
//      LocalDateTime localDateTime = LocalDateTime.now();
//      Date date = new Date();
//      date.set
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public String getCreateDate() {

    return createDate;
  }

  public void setCreateDate(String createDate) {
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
