package com.lms.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.notification.model.Notification;
import com.lms.notification.service.NotificationService;






@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	@PostMapping
	public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
		Notification createNotification = notificationService.createNotification(notification);
		return new ResponseEntity<>(createNotification, HttpStatus.CREATED);
	}
		
//	@GetMapping("user/{userId}")
//	public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable Integer userId) {
//	    List<Notification> notifications = notificationService.getNotificationsBYUser(userId);
//	    return new ResponseEntity<>(notifications, HttpStatus.OK);
//	}

	
    
}
