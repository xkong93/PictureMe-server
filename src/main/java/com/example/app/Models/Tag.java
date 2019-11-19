package com.example.app.Models;

import java.util.Date;

/**
 * @author Xuan Kong
 * @Date 2019-11-17.
 */
public class Tag {
  private Date createDate;
  private String name;

  public Tag() {

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
}
