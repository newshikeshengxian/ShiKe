package com.sk.user.service.impl;

import com.google.gson.Gson;
import com.sk.user.mapper.CommentMapper;
import com.sk.user.po.Comment;
import com.sk.user.po.IndPro;
import com.sk.user.po.Indent;
import com.sk.user.service.ICommentService;
import com.sk.user.vo.CommentVO;
import com.sk.user.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CommentVO> queryAllComments(String userId) throws Exception {
        List<CommentVO> list =new ArrayList<>();
        Map map = new HashMap();
        map.put("state",null);
        map.put("deliverTime",null);
        map.put("receiveTime",null);
        map.put("leaveWord",null);
        map.put("months",null);
        map.put("indentType",null);
        map.put("userId",userId);
        JsonResult jsonResult = restTemplate.postForObject("http://order/order/list", map,JsonResult.class);
        Indent[] orders= new Gson().fromJson((String) jsonResult.getData(), Indent[].class);
        List<Comment> comments = commentMapper.queryAllComment(userId);
        for( Indent indent : orders){

            for (Comment comment:comments){
                if(comment.getIndentId().equals(indent.getIndentId())) {

                    String raId = indent.getRaId();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date = new Date(indent.getCreateTime().getTime());
                    String format1 = format.format(date);
                    for (IndPro indPro : indent.getProducts()){
                        CommentVO commentVO = new CommentVO();
                        commentVO.setCreateTime(format1);
                        commentVO.setLeaveWord(indent.getLeaveWord());
                        commentVO.setProductName(indPro.getProductName());
                        commentVO.setProductPic(indPro.getProductPc1());
                        list.add(commentVO);
                    }

                }
            }
        }
        return list;
    }

    /**
     * 添加评论
     * @param comment
     * @throws Exception
     */
    @Override
    public void addComment(Comment comment) throws Exception {
        comment.setComId(UUID.randomUUID().toString());
        commentMapper.addComment(comment);
    }
}
