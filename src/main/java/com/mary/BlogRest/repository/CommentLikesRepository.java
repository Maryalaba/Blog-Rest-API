package com.mary.BlogRest.repository;

import com.mary.BlogRest.model.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
    List<CommentLikes> findAllByCommentId(Long commentId);
}
