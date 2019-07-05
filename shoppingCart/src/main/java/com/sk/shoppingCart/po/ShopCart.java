package com.sk.shoppingCart.po;


public class ShopCart {

  private long spId;
  private String productId;
  private String userId;
  private String productPic;
  private double productPrice;
  private String productName;
  private long indentNum;


  public long getSpId() {
    return spId;
  }

  public void setSpId(long spId) {
    this.spId = spId;
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


  public String getProductPic() {
    return productPic;
  }

  public void setProductPic(String productPic) {
    this.productPic = productPic;
  }


  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public long getIndentNum() {
    return indentNum;
  }

  public void setIndentNum(long indentNum) {
    this.indentNum = indentNum;
  }

}
