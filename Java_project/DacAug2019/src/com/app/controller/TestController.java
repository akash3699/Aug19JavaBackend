package com.app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
	
	public TestController() {
		System.out.println("inside Test Controller");
	}
	
	@GetMapping
	public String test()
	{
		return "SunBeam";
	}

}
