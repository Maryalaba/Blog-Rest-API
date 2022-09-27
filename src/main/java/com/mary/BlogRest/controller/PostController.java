package com.mary.BlogRest.controller;
import com.mary.BlogRest.model.Favorites;
import com.mary.BlogRest.model.Post;
import com.mary.BlogRest.model.PostLikes;
import com.mary.BlogRest.response.ApiResponse;
import com.mary.BlogRest.service.serviceImpl.PostServiceImpl;
import com.mary.BlogRest.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RestController
public class PostController {
    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/posts")
    public Page<Post> getPosts(Pageable pageable) {
        return postService.getAllPosts(pageable);
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> addPost(@PathVariable Long userId,
                        @Valid @RequestBody Post postRequest) {
        Post createdPost = postService.createPost(userId, postRequest);
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long userId,
                           @PathVariable Long postId,
                           @Valid @RequestBody Post postRequest) {
        userService.checkUserExists(userId);
        Post updatedPost = postService.editPost(postId, postRequest);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long userId,
                                        @PathVariable Long postId,
                                        HttpSession session) {
        userService.checkUserExists(userId);
        if (userService.getUser(userId).get() == session.getAttribute("currentUser")) {
            return postService.deletePost(userId, postId);
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "You cannot delete this post."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{userId}/posts/{postId}/postLikes")
    public ResponseEntity<List<PostLikes>> getPostLikesByPostId(@PathVariable Long postId,
                                                @PathVariable Long userId) {
        List<PostLikes> likesList = postService.getAllPostLikes(userId, postId);
        return new ResponseEntity<>(likesList, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/posts/{postId}/postLikes")
    public ResponseEntity<PostLikes> likePost (@PathVariable Long userId,
                               @PathVariable Long postId,
                               @Valid @RequestBody PostLikes postLike){
        PostLikes postLikes = postService.likePost(userId, postId, postLike);
        return new ResponseEntity<>(postLikes, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/posts/{postId}/postLikes/{postLikeId}")
    public ResponseEntity<?> dislikePost(@PathVariable Long postId,
                                        @PathVariable Long postLikeId,
                                        @PathVariable Long userId){
        userService.checkUserExists(userId);
        postService.checkPostExists(postId);
        return postService.disLikePost(postLikeId);
    }

    @GetMapping("/favorites")
    public Page<Favorites> getFavorites(Pageable pageable) {
        return postService.getAllFavorites(pageable);
    }

    @GetMapping("/users/{userId}/favorites")
    public ResponseEntity<List<Favorites>> getFavoritesByUserId(@PathVariable Long userId) {
        List<Favorites> favoritesList = postService.getUserFavorites(userId);
        return new ResponseEntity<>(favoritesList, HttpStatus.OK);
    }

    @PostMapping("/favorites/{postId}")
    public Favorites addFavorite(@PathVariable Long postId,
                                 @Valid @RequestBody Favorites favorite) {
        return postService.addToFavorites( postId, favorite);
    }

    @DeleteMapping("/favorites/{userId}/{favoriteId}")
    public ResponseEntity<?> deleteFavorites(@PathVariable Long userId,
                                             @PathVariable Long favoriteId) {
        userService.checkUserExists(userId);
        return postService.removeFromFavorites(favoriteId);
    }

}
