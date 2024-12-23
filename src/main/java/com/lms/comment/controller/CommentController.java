package com.lms.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lms.comment.model.Comment;
import com.lms.comment.service.CommentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{topicId}")
    public ResponseEntity<Comment> createComment(@PathVariable UUID topicId, @RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(topicId, comment);
        if (createdComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<Comment>> getCommentsByTopicId(@PathVariable UUID topicId) {
        List<Comment> comments = commentService.getCommentsByTopicId(topicId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}

