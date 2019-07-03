package com.sk.user.service.impl;

import com.netflix.discovery.converters.Auto;
import com.sk.user.mapper.CreditsMapper;
import com.sk.user.po.Credits;
import com.sk.user.service.ICreditsService;
import com.sk.user.vo.ListCreditsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class CreditsServiceImpl implements ICreditsService {
    @Autowired
    private CreditsMapper creditsMapper;

    /**
     * 查询所有的积分
     * @param userId
     * @return listCreditsVO
     * @throws Exception
     */
    @Override
    public ListCreditsVO queryAllCre(String userId) throws Exception {
        List<Credits> creditsList = creditsMapper.queryAllCre(userId);
        long i = 0;
        for (Credits credits : creditsList){
            i = i + (credits.getCreNum()-credits.getCreUse());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            long time = credits.getCreTime().getTime();
            Date date = new Date(time);
            String format1 = format.format(date);
            credits.setTime(format1);
        }
        ListCreditsVO vo = new ListCreditsVO();
        vo.setAllcreNum(i);
        vo.setCreditsList(creditsList);
        return vo;
    }
}
