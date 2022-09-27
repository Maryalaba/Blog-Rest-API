package com.mary.BlogRest.service;

import com.mary.BlogRest.model.Favorites;
import com.mary.BlogRest.model.Post;
import com.mary.BlogRest.model.PostLikes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostService {
    Page<Post> getAllPosts(Pageable pageable);
    Post createPost(Long userId, Post post);
    Post editPost(Long postId, Post postRequest);
    ResponseEntity<?>  deletePost(Long userId, Long postId);
    List<PostLikes> getAllPostLikes(Long userId, long postId);
    PostLikes likePost(Long userId, Long postId, PostLikes postLike);
    ResponseEntity<?> disLikePost(Long postLikeId);
    Page<Favorites> getAllFavorites(Pageable pageable);
    List<Favorites> getUserFavorites(Long userId);
    Favorites addToFavorites(Long postId, Favorites favoriteRequest);
    ResponseEntity<?>  removeFromFavorites(Long favoriteID);
}
