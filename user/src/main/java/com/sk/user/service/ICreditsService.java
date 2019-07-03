package com.sk.user.service;

import com.sk.user.po.Credits;
import com.sk.user.vo.ListCreditsVO;

import java.util.List;

public interface ICreditsService {
    /**
     * 查询所有的积分
     * @param userId
     * @return ListCreditsVO
     * @throws Exception
     */
    ListCreditsVO queryAllCre(String userId) throws  Exception;


}
