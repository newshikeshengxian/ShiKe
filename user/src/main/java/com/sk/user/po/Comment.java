package com.sk.user.po;


public class Comment {

  private String comId;
  private String indentId;
  private long comRank;
  private String comDesc;
  private String comTheme;
  private String userId;


  public String getComId() {
    return comId;
  }

  public void setComId(String comId) {
    this.comId = comId;
  }


  public String getIndentId() {
    return indentId;
  }

  public void setIndentId(String indentId) {
    this.indentId = indentId;
  }


  public long getComRank() {
    return comRank;
  }

  public void setComRank(long comRank) {
    this.comRank = comRank;
  }


  public String getComDesc() {
    return comDesc;
  }

  public void setComDesc(String comDesc) {
    this.comDesc = comDesc;
  }


  public String getComTheme() {
    return comTheme;
  }

  public void setComTheme(String comTheme) {
    this.comTheme = comTheme;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
