package com.sk.auth.po;


public class AuthCenter {

  private long acId;
  private String roleId;
  private String acPwd;
  private String userId;
  private String acPhone;
  private String userName;


  public long getAcId() {
    return acId;
  }

  public void setAcId(long acId) {
    this.acId = acId;
  }


  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }


  public String getAcPwd() {
    return acPwd;
  }

  public void setAcPwd(String acPwd) {
    this.acPwd = acPwd;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getAcPhone() {
    return acPhone;
  }

  public void setAcPhone(String acPhone) {
    this.acPhone = acPhone;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}
