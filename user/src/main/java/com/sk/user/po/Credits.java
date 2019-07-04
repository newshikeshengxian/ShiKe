package com.sk.user.po;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Credits {
  private String creId;
  private String userId;
  private long creNum;
  private java.sql.Timestamp creTime;
  private long creUse;
  private String creDesc;
  private String time;

}
