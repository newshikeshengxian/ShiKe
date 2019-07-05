package com.sk.user.controller;

import com.sk.user.po.Comment;
import com.sk.user.service.impl.CommentServiceImpl;
import com.sk.user.vo.CommentVO;
import com.sk.user.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userComment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @RequestMapping("/add")
    public JsonResult addComment(Comment comment,String token){
        //        Map map = new HashMap();
//        map.put("token","666");
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult jsonResult = new JsonResult();
//        if(forObject.getCode() == 0) {
        try {
            commentService.addComment(comment);
            jsonResult.setCode(0);
            return jsonResult;
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setCode(1);
            return jsonResult;
        }

        //        }
//        jsonResult.setCode(1);
//        return jsonResult;
    }
    @RequestMapping("/list")
    public JsonResult queryAllComments(String userId,String token){
        //        Map map = new HashMap();
//        map.put("token",token);
//        JsonResult forObject =null;
//        forObject = restTemplate.postForObject("http://auth-server/ifLogin",map,JsonResult.class);
//        String userId =(String) forObject.getData();
        JsonResult jsonResult = new JsonResult();
//        if(forObject.getCode() == 0) {
        try {
            List<CommentVO> commentVOS = commentService.queryAllComments(userId);
            jsonResult.setCode(0);
            jsonResult.setData(commentVOS);
            return jsonResult;
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setCode(1);
            return jsonResult;
        }
        //        }
//        jsonResult.setCode(1);
//        return jsonResult;
    }
}
