package com.sk.user.po;


public class Coupon {

  private String couponId;
  private java.sql.Timestamp endTime;
  private double couponMoney;
  private String condition;
  private String ptId;
  private java.sql.Timestamp beginTime;
  private long state;
  private String time1;
  private String time2;
  private String useState;

  public String getTime1() {
    return time1;
  }

  public void setTime1(String time1) {
    this.time1 = time1;
  }

  public String getTime2() {
    return time2;
  }

  public void setTime2(String time2) {
    this.time2 = time2;
  }

  public String getUseState() {
    return useState;
  }

  public void setUseState(String useState) {
    this.useState = useState;
  }

  public String getCouponId() {
    return couponId;
  }

  public void setCouponId(String couponId) {
    this.couponId = couponId;
  }


  public java.sql.Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Timestamp endTime) {
    this.endTime = endTime;
  }


  public double getCouponMoney() {
    return couponMoney;
  }

  public void setCouponMoney(double couponMoney) {
    this.couponMoney = couponMoney;
  }


  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }


  public String getPtId() {
    return ptId;
  }

  public void setPtId(String ptId) {
    this.ptId = ptId;
  }


  public java.sql.Timestamp getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(java.sql.Timestamp beginTime) {
    this.beginTime = beginTime;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }

}
