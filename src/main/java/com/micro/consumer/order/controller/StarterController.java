package com.micro.consumer.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.starter.service.FileService;

@RestController
@RequestMapping("starter")
public class StarterController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/getFileService")
	public void getFileService() {
		fileService.print();
	}
}
