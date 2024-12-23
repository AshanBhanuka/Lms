package com.lms.topic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.topic.model.Topic;
import com.lms.topic.repository.TopicRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(UUID topicId) {
        return topicRepository.findById(topicId).orElse(null);
    }

	
	
}

