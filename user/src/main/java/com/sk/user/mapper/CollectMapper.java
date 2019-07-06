package com.sk.user.mapper;

import com.sk.user.po.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectMapper {
    /**
     * 根据用户ID查询该用户收藏的所有的商品
     * @param userId
     * @return 收藏集合
     */
    List<Collect> queryAllCollects(String userId);

    /**
     * 根据收藏ID删除收藏记录
     * @param colId
     */
    void deleteCollect(String colId);
}
