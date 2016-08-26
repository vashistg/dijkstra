package com.dijkstra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DijkstraController {
	
	@ResponseBody
	@RequestMapping(value="/")
	public String welcome(){
		return "<h1><b>I am up and running &#9786</b></h1>";
	}
	
	@RequestMapping(value="/test")
	private void executeQuery(){
		
	}
}
