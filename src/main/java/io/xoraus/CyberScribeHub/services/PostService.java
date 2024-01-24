package io.xoraus.CyberScribeHub.services;

import io.xoraus.CyberScribeHub.payloads.PostDto;
import io.xoraus.CyberScribeHub.payloads.PostResponse;

import java.util.List;

public interface PostService {
    // create post

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update post

    PostDto updatePost(PostDto postDto, Integer postId);

    // delete post

    void deletePost(Integer postId);

    // get all posts

     PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // get a single post

    PostDto getPostById(Integer postId);

    // get all posts by category

    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    // search posts
    List<PostDto> searchPosts(String keyword);
}
