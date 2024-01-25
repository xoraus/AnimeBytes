package io.xoraus.CyberScribeHub.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.xoraus.CyberScribeHub.payloads.ApiResponse;
import io.xoraus.CyberScribeHub.payloads.CommentDto;
import io.xoraus.CyberScribeHub.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@Tag(name = "Comment Controller", description = "Handles operations related to comments on posts, providing functionality for creating, retrieving, and managing comments.")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId) {

        CommentDto createComment = this.commentService.createComment(comment, postId);
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {

        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully !!", true), HttpStatus.OK);
    }

}

