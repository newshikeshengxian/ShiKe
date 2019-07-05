package com.sk.user.po;


public class Collect {

  private String colId;
  private String productId;
  private String userId;
  private java.sql.Timestamp createTime;


  public String getColId() {
    return colId;
  }

  public void setColId(String colId) {
    this.colId = colId;
  }


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
