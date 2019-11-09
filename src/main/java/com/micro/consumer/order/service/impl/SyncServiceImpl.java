package com.micro.consumer.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.consumer.order.service.SyncService;
import com.micro.consumer.order.thread.ThreadTasks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SyncServiceImpl implements SyncService {

	@Autowired
    private ThreadTasks threadTasks;

	@Override
	public void importOrder() {
		log.info("start");
        for (int i = 0; i < 10; i++) {
        	threadTasks.startThreadTasks();
        }
        
		log.info("end");
	}

}
