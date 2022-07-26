package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository {
    //댓글
    List<comment> findComment(Long pro_id);

    List<comment> findCommentId(int commentid);

    //댓글 저장
    void save(comment comment);

    //댓글 수정
    void update(comment comment);
    //댓글 삭제
    void delete(int commentid);
}
