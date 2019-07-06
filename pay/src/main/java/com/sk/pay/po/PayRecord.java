package com.sk.pay.po;


public class PayRecord {

  private String prId;
  private String indentId;
  private double prMoney;
  private String prMode;
  private long state;


  public String getPrId() {
    return prId;
  }

  public void setPrId(String prId) {
    this.prId = prId;
  }


  public String getIndentId() {
    return indentId;
  }

  public void setIndentId(String indentId) {
    this.indentId = indentId;
  }


  public double getPrMoney() {
    return prMoney;
  }

  public void setPrMoney(double prMoney) {
    this.prMoney = prMoney;
  }


  public String getPrMode() {
    return prMode;
  }

  public void setPrMode(String prMode) {
    this.prMode = prMode;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }

}
