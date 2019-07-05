package com.sk.user.po;


public class Recharge {

  private String rechId;
  private String userId;
  private double rechPrice;
  private long rechState;
  private java.sql.Timestamp rechTime;
  private String rechNum;
  private String rechPwd;
  private  String rechType;
  private String time;

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getRechType() {
    return rechType;
  }

  public void setRechType(String rechType) {
    this.rechType = rechType;
  }

  public String getRechNum() {
    return rechNum;
  }

  public void setRechNum(String rechNum) {
    this.rechNum = rechNum;
  }

  public String getRechPwd() {
    return rechPwd;
  }

  public void setRechPwd(String rechPwd) {
    this.rechPwd = rechPwd;
  }

  public String getRechId() {
    return rechId;
  }

  public void setRechId(String rechId) {
    this.rechId = rechId;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public double getRechPrice() {
    return rechPrice;
  }

  public void setRechPrice(double rechPrice) {
    this.rechPrice = rechPrice;
  }


  public long getRechState() {
    return rechState;
  }

  public void setRechState(long rechState) {
    this.rechState = rechState;
  }


  public java.sql.Timestamp getRechTime() {
    return rechTime;
  }

  public void setRechTime(java.sql.Timestamp rechTime) {
    this.rechTime = rechTime;
  }

}
