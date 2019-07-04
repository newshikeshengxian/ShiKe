package com.sk.store.po;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promotion {

  private String prom_Id;
  private String product_Id;
  private double prom_Price;
  private java.sql.Timestamp prom_Time;
  private long prom_Num;
  private Product product;


  public String getProm_Id() {
    return prom_Id;
  }

  public void setProm_Id(String prom_Id) {
    this.prom_Id = prom_Id;
  }


  public String getProduct_Id() {
    return product_Id;
  }

  public void setProduct_Id(String product_Id) {
    this.product_Id = product_Id;
  }


  public double getProm_Price() {
    return prom_Price;
  }

  public void setProm_Price(double prom_Price) {
    this.prom_Price = prom_Price;
  }


  public java.sql.Timestamp getProm_Time() {
    return prom_Time;
  }

  public void setProm_Time(java.sql.Timestamp prom_Time) {
    this.prom_Time = prom_Time;
  }


  public long getProm_Num() {
    return prom_Num;
  }

  public void setProm_Num(long prom_Num) {
    this.prom_Num = prom_Num;
  }

}
