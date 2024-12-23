package com.lms.notification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.notification.model.Notification;
import com.lms.notification.repository.NotificationRepository;



@Service
public class NotificationService {
	
	@Autowired
    private NotificationRepository notificationRepository;
	
	public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
}
	
//	  public List<Notification> getNotificationsBYUser(Integer userId) {
//	        return notificationRepository.findByUserId(userId);
//	    }
}
	 
