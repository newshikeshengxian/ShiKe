package com.sk.user.service.impl;

import com.sk.user.mapper.CollectMapper;
import com.sk.user.po.Collect;
import com.sk.user.service.ICollectService;
import com.sk.user.vo.CollectVO;
import com.sk.user.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CollectServiceImpl implements ICollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 根据用户ID查询所有的收藏商品集合
     * @param userId
     * @return 收藏商品集合
     * @throws Exception
     */
    @Override
    public List<CollectVO> queryAllCollect(String userId) throws Exception {
        List<CollectVO> list = new ArrayList<>();
        List<Collect> collects = collectMapper.queryAllCollects(userId);
        for (Collect collect :collects) {
            JsonResult forObject = restTemplate.getForObject("http://store/product/products?id=" + collect.getProductId(), JsonResult.class);
            Map map = (Map) forObject.getData();
            CollectVO collectVO = new CollectVO();
            collectVO.setColId(collect.getColId());
            collectVO.setCreateTime((String)map.get("productTime"));
            collectVO.setPrice(Double.parseDouble((String) map.get("productPrice")));
            collectVO.setProductId((String)map.get("productId"));
            collectVO.setProductName((String)map.get("productName"));
            collectVO.setProductPic((String)map.get("productPc1"));
            list.add(collectVO);
        }
        return list;
    }

    @Transactional
    @Override
    public List<CollectVO> deleteCollect(String userId, String colId) throws Exception {
        collectMapper.deleteCollect(colId);
        List<CollectVO> list = queryAllCollect(userId);
        return list;
    }
}
