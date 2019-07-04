package com.sk.user.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JsonResult implements Serializable {
        private Integer code ;
        private String msg;
        private  Object data;

}
