package com.sk.user.vo;

import com.sk.user.po.Credits;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ListCreditsVO {
   private List<Credits>  creditsList;
   private Long allcreNum;
}
