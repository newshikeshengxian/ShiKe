package com.sk.user.vo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CollectVO {
    private String colId;
    private String productId;
    private String productName;
    private double price;
    private String productPic;
    private String createTime;
}
