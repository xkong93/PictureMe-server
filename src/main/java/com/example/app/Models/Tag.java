package com.example.app.Models;

import java.util.Date;

/**
 * @author Xuan Kong, Yuting Chen
 * @Date 2019-11-26.
 */
public class Tag {

  private Integer tid;
  private String createDate;
  private String name;

  public Tag() {

  }
  public Tag(String name) {
    this.name = name;
  }
  public Integer getTid() {
    return tid;
  }

  public void setTid(Integer tid) {
    this.tid = tid;
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
}
