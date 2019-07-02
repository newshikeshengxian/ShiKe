package com.sk.store.po;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductOne {

  private String poId;
  private String poName;
  private List<ProducrTwo> producrTwo;


  public String getPoId() {
    return poId;
  }

  public void setPoId(String poId) {
    this.poId = poId;
  }


  public String getPoName() {
    return poName;
  }

  public void setPoName(String poName) {
    this.poName = poName;
  }

}
