package com.sk.store.po;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProducrTwo {

  private String ptId;
  private String ptName;
  private String poId;
  private List<ProductThree> productThree;

  public String getPtId() {
    return ptId;
  }

  public void setPtId(String ptId) {
    this.ptId = ptId;
  }


  public String getPtName() {
    return ptName;
  }

  public void setPtName(String ptName) {
    this.ptName = ptName;
  }


  public String getPoId() {
    return poId;
  }

  public void setPoId(String poId) {
    this.poId = poId;
  }

}
