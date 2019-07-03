package com.sk.shoppingCart.vo;

import java.io.Serializable;

public class JsonResult implements Serializable {
    private Integer code ;
    private String msg;
    private  Object data;

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
