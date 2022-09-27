package com.mary.BlogRest.service;

import com.mary.BlogRest.model.Comment;
import com.mary.BlogRest.model.CommentLikes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentService {
    List<Comment> getCommentsByPostId(Long postId);
    List<Comment> getAllComments();
    Comment createComment(Long postId, Comment comment);
    Comment editComment(Long commentId, Comment commentRequest);
    ResponseEntity<?> deleteComment(Long commentId);
    List<CommentLikes> getCommentLikes(Long commentId);
    CommentLikes likeComment(Long commentId, CommentLikes commentLike);
    ResponseEntity<?> dislikeComment(Long commentLikeId);
}
