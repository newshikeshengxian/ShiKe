package com.sk.user.po;


import java.util.List;

public class Indent {

  private String indentId;
  private String userId;
  private double price;
  private java.sql.Timestamp payTime;
  private long state;
  private java.sql.Timestamp createTime;
  private String raId;
  private String couponId;
  private java.sql.Timestamp deliverGoodsTime;
  private java.sql.Timestamp receiveTime;
  private String trackingNumber;
  private String leaveWord;
  private long indentType;
  private Integer payMent;
  private long deliveryTime;
  private List<IndPro> products;
  private String raName;
  private String time;
  private String stateDesc;

  public String getStateDesc() {
    return stateDesc;
  }

  public void setStateDesc(String stateDesc) {
    this.stateDesc = stateDesc;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getRaName() {
    return raName;
  }

  public void setRaName(String raName) {
    this.raName = raName;
  }

  public List<IndPro> getProducts() {
    return products;
  }

  public void setProducts(List<IndPro> products) {
    this.products = products;
  }

  public String getIndentId() {
    return indentId;
  }

  public void setIndentId(String indentId) {
    this.indentId = indentId;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public java.sql.Timestamp getPayTime() {
    return payTime;
  }

  public void setPayTime(java.sql.Timestamp payTime) {
    this.payTime = payTime;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getRaId() {
    return raId;
  }

  public void setRaId(String raId) {
    this.raId = raId;
  }


  public String getCouponId() {
    return couponId;
  }

  public void setCouponId(String couponId) {
    this.couponId = couponId;
  }


  public java.sql.Timestamp getDeliverGoodsTime() {
    return deliverGoodsTime;
  }

  public void setDeliverGoodsTime(java.sql.Timestamp deliverGoodsTime) {
    this.deliverGoodsTime = deliverGoodsTime;
  }


  public java.sql.Timestamp getReceiveTime() {
    return receiveTime;
  }

  public void setReceiveTime(java.sql.Timestamp receiveTime) {
    this.receiveTime = receiveTime;
  }


  public String getTrackingNumber() {
    return trackingNumber;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }


  public String getLeaveWord() {
    return leaveWord;
  }

  public void setLeaveWord(String leaveWord) {
    this.leaveWord = leaveWord;
  }


  public long getIndentType() {
    return indentType;
  }

  public void setIndentType(long indentType) {
    this.indentType = indentType;
  }


  public Integer getPayMent() {
    return payMent;
  }

  public void setPayMent(Integer payMent) {
    this.payMent = payMent;
  }

  public long getDeliveryTime() {
    return deliveryTime;
  }

  public void setDeliveryTime(long deliveryTime) {
    this.deliveryTime = deliveryTime;
  }

}
