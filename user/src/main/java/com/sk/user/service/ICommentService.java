package com.sk.user.service;

import com.sk.user.po.Comment;
import com.sk.user.vo.CommentVO;

import java.util.List;

public interface ICommentService {
    /**
     * 添加评论
     * @param comment
     */
    void addComment(Comment comment)throws Exception;

    List<CommentVO> queryAllComments(String userId) throws Exception;
}
