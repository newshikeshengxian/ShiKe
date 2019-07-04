package com.sk.store.po;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

  private String product_Id;
  private String product_Name;
  private String pth_Id;
  private double product_Price;
  private String product_Desc;
  private String product_Area;
  private String product_Pc1;
  private String product_Pc2;
  private String product_Pc3;
  private String product_Pc4;
  private String product_Pc5;
  private String product_Pc6;
  private String product_Pc7;
  private String mode_Pack;
  private long product_Grade;
  private double market_Price;
  private long state;
  private String brand_Id;
  private java.sql.Timestamp product_Time;


  public String getProduct_Id() {
    return product_Id;
  }

  public void setProduct_Id(String product_Id) {
    this.product_Id = product_Id;
  }


  public String getProduct_Name() {
    return product_Name;
  }

  public void setProduct_Name(String product_Name) {
    this.product_Name = product_Name;
  }


  public String getPth_Id() {
    return pth_Id;
  }

  public void setPth_Id(String pth_Id) {
    this.pth_Id = pth_Id;
  }


  public double getProduct_Price() {
    return product_Price;
  }

  public void setProduct_Price(double product_Price) {
    this.product_Price = product_Price;
  }


  public String getProduct_Desc() {
    return product_Desc;
  }

  public void setProduct_Desc(String product_Desc) {
    this.product_Desc = product_Desc;
  }


  public String getProduct_Area() {
    return product_Area;
  }

  public void setProduct_Area(String product_Area) {
    this.product_Area = product_Area;
  }


  public String getProduct_Pc1() {
    return product_Pc1;
  }

  public void setProduct_Pc1(String product_Pc1) {
    this.product_Pc1 = product_Pc1;
  }


  public String getProduct_Pc2() {
    return product_Pc2;
  }

  public void setProduct_Pc2(String product_Pc2) {
    this.product_Pc2 = product_Pc2;
  }


  public String getProduct_Pc3() {
    return product_Pc3;
  }

  public void setProduct_Pc3(String product_Pc3) {
    this.product_Pc3 = product_Pc3;
  }


  public String getProduct_Pc4() {
    return product_Pc4;
  }

  public void setProduct_Pc4(String product_Pc4) {
    this.product_Pc4 = product_Pc4;
  }


  public String getProduct_Pc5() {
    return product_Pc5;
  }

  public void setProduct_Pc5(String product_Pc5) {
    this.product_Pc5 = product_Pc5;
  }


  public String getProduct_Pc6() {
    return product_Pc6;
  }

  public void setProduct_Pc6(String product_Pc6) {
    this.product_Pc6 = product_Pc6;
  }


  public String getProduct_Pc7() {
    return product_Pc7;
  }

  public void setProduct_Pc7(String product_Pc7) {
    this.product_Pc7 = product_Pc7;
  }


  public String getMode_Pack() {
    return mode_Pack;
  }

  public void setMode_Pack(String mode_Pack) {
    this.mode_Pack = mode_Pack;
  }


  public long getProduct_Grade() {
    return product_Grade;
  }

  public void setProduct_Grade(long product_Grade) {
    this.product_Grade = product_Grade;
  }


  public double getMarket_Price() {
    return market_Price;
  }

  public void setMarket_Price(double market_Price) {
    this.market_Price = market_Price;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public String getBrand_Id() {
    return brand_Id;
  }

  public void setBrand_Id(String brand_Id) {
    this.brand_Id = brand_Id;
  }


  public java.sql.Timestamp getProduct_Time() {
    return product_Time;
  }

  public void setProduct_Time(java.sql.Timestamp product_Time) {
    this.product_Time = product_Time;
  }

}
