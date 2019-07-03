package com.sk.user.mapper;

import com.sk.user.po.Credits;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CreditsMapper {
    /**
     * 跟用用户ID查询全部所有积分
     * @param userId
     * @return 积分po的集合
     */
    List<Credits> queryAllCre(String userId);
}
