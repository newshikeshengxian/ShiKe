package com.sk.user.vo;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.GET;

@Getter
@Setter
public class CollectVO {
    private String productId;
    private String productName;
    private double price;
    private String createTime;
}
