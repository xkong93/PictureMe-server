package com.example.app.Models;

import java.util.Date;
import java.util.List;

/**
 * @author Xuan Kong
 * @Date 2019-11-17.
 */
public class Photo {

  private Integer pid;
  private String url;
  private String name;
  private Date createDate;
  private Integer height;
  private Integer width;
  private Integer focalLength;
  private String fNumber;
  private Integer iso;
  private String exposureTime;
  private double latitude;
  private double longitude;
  private String location;
  private String manufacturer;
  private String model;
  private List<Category> categoryList;

  public Photo() {
      this.createDate = new Date();
  }

  public Integer getPid() {
    return pid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getFocalLength() {
    return focalLength;
  }

  public void setFocalLength(Integer focalLength) {
    this.focalLength = focalLength;
  }

  public String getfNumber() {
    return fNumber;
  }

  public void setfNumber(String fNumber) {
    this.fNumber = fNumber;
  }

  public Integer getIso() {
    return iso;
  }

  public void setIso(Integer iso) {
    this.iso = iso;
  }

  public String getExposureTime() {
    return exposureTime;
  }

  public void setExposureTime(String exposureTime) {
    this.exposureTime = exposureTime;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public List<Category> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<Category> categoryList) {
    this.categoryList = categoryList;
  }
}
