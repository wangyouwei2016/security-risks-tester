package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.risk.HardcodePassword;
import com.example.demo.risk.LeftoverDebug;
@RestController
public class GreetingsController {
	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "hello world!";
	}
	
	
	@GetMapping("/hardcode-password")
	public String hardCodePassword() {
		return HardcodePassword.output();
	}
	
	@GetMapping("/leftover-debug")
	public String leftoverDebug() {
		return LeftoverDebug.output();
	}
	
}
