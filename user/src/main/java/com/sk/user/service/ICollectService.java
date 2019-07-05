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
}
