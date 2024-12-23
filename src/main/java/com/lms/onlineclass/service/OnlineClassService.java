package com.lms.onlineclass.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.onlineclass.model.OnlineClass;
import com.lms.onlineclass.repository.OnlineClassRepository;

@Service
public class OnlineClassService {
	
    @Autowired
    private OnlineClassRepository onlineClassRepository;

   
    public OnlineClass createOnlineClass(OnlineClass onlineClass) {
        return onlineClassRepository.save(onlineClass);
    }
    
    
    public List<OnlineClass> getAllOnlineClasses() {
        return onlineClassRepository.findAll();
    }
    
    public OnlineClass getOnlineClassById (Integer id) {
   	 return onlineClassRepository.findById(id).orElseThrow(null);
    }

}
