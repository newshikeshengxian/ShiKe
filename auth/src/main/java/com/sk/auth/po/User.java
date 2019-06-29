package com.sk.auth.po;


public class User {

  private String userId;
  private String userName;
  private String phone;
  private String userSex;
  private String couponId;
  private double balance;
  private double credits;
  private String email;
  private String pic;
  private long vipGrade;
  private String brith;
  private String addr;
  private String payPwd;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getUserSex() {
    return userSex;
  }

  public void setUserSex(String userSex) {
    this.userSex = userSex;
  }


  public String getCouponId() {
    return couponId;
  }

  public void setCouponId(String couponId) {
    this.couponId = couponId;
  }


  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }


  public double getCredits() {
    return credits;
  }

  public void setCredits(double credits) {
    this.credits = credits;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }


  public long getVipGrade() {
    return vipGrade;
  }

  public void setVipGrade(long vipGrade) {
    this.vipGrade = vipGrade;
  }


  public String getBrith() {
    return brith;
  }

  public void setBrith(String brith) {
    this.brith = brith;
  }


  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }


  public String getPayPwd() {
    return payPwd;
  }

  public void setPayPwd(String payPwd) {
    this.payPwd = payPwd;
  }

}
