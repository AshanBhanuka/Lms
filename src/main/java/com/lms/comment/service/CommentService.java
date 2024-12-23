package com.lms.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.comment.Repositoey.CommentRepository;
import com.lms.comment.model.Comment;

import com.lms.topic.model.Topic;
import com.lms.topic.service.TopicService;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TopicService topicService;

    public Comment createComment(UUID topicId, Comment comment) {
        Topic topic = topicService.getTopicById(topicId);
        if (topic != null) {
            comment.setTopic(topic);
            return commentRepository.save(comment);
        }
        return null;  // Or throw an exception
    }

    public List<Comment> getCommentsByTopicId(UUID topicId) {
        return commentRepository.findByTopicId(topicId);
    }
}
