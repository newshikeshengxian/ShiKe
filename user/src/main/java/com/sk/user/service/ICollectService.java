package com.sk.user.service;

import com.sk.user.vo.CollectVO;

import java.util.List;

public interface ICollectService {
    /**
     * 根据用户ID查询所有的收藏商品集合
     * @param userId
     * @return 收藏商品集合
     * @throws Exception
     */
    List<CollectVO> queryAllCollect(String userId) throws Exception;

    /**
     * 根据收藏ID删除该收藏并查询最新收藏
     * @param userId
     * @param colId
     * @return 最新收藏商品集合
     * @throws Exception
     */
    List<CollectVO> deleteCollect(String userId,String colId) throws Exception;
}
