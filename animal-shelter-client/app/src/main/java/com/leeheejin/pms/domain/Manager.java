package com.leeheejin.pms.domain;

import java.sql.Date;

public class Manager {
  private int no;
  private String name;
  private String id;
  private String password;
  private String email;
  private String tel;
  private String photo;
  private Date registeredDate;

  @Override
  public String toString() {
    return "Manager [no=" + no + ", id=" + id + ", name=" + name + ", email=" + email + ", tel="
        + tel + ", password=" + password + ", photo=" + photo + ", registeredDate=" + registeredDate
        + "]";
  }

  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
}
