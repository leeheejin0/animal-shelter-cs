package com.leeheejin.pms.domain;

import java.sql.Date;

public class Dog {
  private int ids;
  private String photos;
  private String breeds;
  private String genders;
  private int ages;
  private Date dates;
  private String places;
  private String status;
  private int writer;

  @Override
  public String toString() {
    return "Dog [ids=" + ids + ", photos=" + photos + ", breeds=" + breeds + ", genders=" + genders
        + ", ages=" + ages + ", dates=" + dates + ", places=" + places + ", status=" + status
        + ", writer=" + writer + "]";
  }

  public int getWriter() {
    return writer;
  }


  public void setWriter(int writer) {
    this.writer = writer;
  }


  public int getIds() {
    return ids;
  }
  public void setIds(int ids) {
    this.ids = ids;
  }
  public String getPhotos() {
    return photos;
  }
  public void setPhotos(String photos) {
    this.photos = photos;
  }
  public String getBreeds() {
    return breeds;
  }
  public void setBreeds(String breeds) {
    this.breeds = breeds;
  }
  public String getGenders() {
    return genders;
  }
  public void setGenders(String genders) {
    this.genders = genders;
  }
  public int getAges() {
    return ages;
  }
  public void setAges(int ages) {
    this.ages = ages;
  }
  public Date getDates() {
    return dates;
  }
  public void setDates(Date dates) {
    this.dates = dates;
  }
  public String getPlaces() {
    return places;
  }
  public void setPlaces(String places) {
    this.places = places;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
}
