package com.sk.shoppingCart.po;


public class IndPro {

  private long ipId;
  private long spId;
  private String indentId;
  private String productId;
  private long indentNum;
  private double productPrice;
  private String productName;
  private String productPic;

  public long getSpId() {
    return spId;
  }

  public void setSpId(long spId) {
    this.spId = spId;
  }

  public long getIpId() {
    return ipId;
  }

  public void setIpId(long ipId) {
    this.ipId = ipId;
  }


  public String getIndentId() {
    return indentId;
  }

  public void setIndentId(String indentId) {
    this.indentId = indentId;
  }


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }


  public long getIndentNum() {
    return indentNum;
  }

  public void setIndentNum(long indentNum) {
    this.indentNum = indentNum;
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


  public String getProductPic() {
    return productPic;
  }

  public void setProductPic(String productPic) {
    this.productPic = productPic;
  }

}
