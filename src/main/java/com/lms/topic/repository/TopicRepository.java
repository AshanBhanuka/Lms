package com.lms.topic.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.topic.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

	Optional<Topic> findById(UUID topicId);
}
