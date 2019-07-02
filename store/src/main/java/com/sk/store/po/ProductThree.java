package com.sk.store.po;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductThree {

  private String pthId;
  private String pthName;
  private String ptId;


  public String getPthId() {
    return pthId;
  }

  public void setPthId(String pthId) {
    this.pthId = pthId;
  }


  public String getPthName() {
    return pthName;
  }

  public void setPthName(String pthName) {
    this.pthName = pthName;
  }


  public String getPtId() {
    return ptId;
  }

  public void setPtId(String pt_Id) {
    this.ptId = ptId;
  }

}
