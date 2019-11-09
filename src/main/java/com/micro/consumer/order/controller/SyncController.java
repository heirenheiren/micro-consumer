package com.micro.consumer.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.consumer.order.service.SyncService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("sync")
public class SyncController {
	
	@Autowired
	public SyncService syncServiceImpl;
	
	@GetMapping("/starSync")
    public void useMySyncTask() {
		syncServiceImpl.importOrder();
    }
}
