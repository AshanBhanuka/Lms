package com.lms.onlineclass.controller;

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

import com.lms.onlineclass.model.OnlineClass;
import com.lms.onlineclass.service.OnlineClassService;
import com.lms.student.model.Student;


@RestController
@RequestMapping("/api/v1/onlineclasses")
public class OnlineClassController {

	 @Autowired
	    private OnlineClassService onlineClassService;

	   @PostMapping
	    public ResponseEntity<OnlineClass> createOnlineClass(@RequestBody OnlineClass onlineClass) {
	        OnlineClass createOnlineClass = onlineClassService.createOnlineClass(onlineClass);
	        return new ResponseEntity<>(createOnlineClass, HttpStatus.CREATED);
	}
	   
	   @GetMapping
	    public ResponseEntity<List<OnlineClass>> getAllOnlineClasses() {
	        List<OnlineClass> onlineClasses = onlineClassService.getAllOnlineClasses();
	        return new ResponseEntity<>(onlineClasses, HttpStatus.OK);
}
	   @GetMapping("/{id}")
	   public ResponseEntity<OnlineClass> getOnlineClassById(@PathVariable Integer id) {
	       OnlineClass onlineClass = onlineClassService.getOnlineClassById(id);
	       if (onlineClass == null) {
	           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	       }
	       return new ResponseEntity<>(onlineClass, HttpStatus.OK);
	   }

}

