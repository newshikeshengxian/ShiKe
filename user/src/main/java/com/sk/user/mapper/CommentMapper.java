package com.sk.user.mapper;

import com.sk.user.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    /**
     * 添加评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 根据用户ID查询该用户所有的评论
     * @param userId
     * @return 该用户所有的评论
     */
    List<Comment> queryAllComment(String userId);
}
