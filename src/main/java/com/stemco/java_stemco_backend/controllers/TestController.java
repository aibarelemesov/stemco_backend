package com.stemco.java_stemco_backend.controllers;

import com.stemco.java_stemco_backend.services.TestService;
import com.stemco.java_stemco_backend.user.UserProgressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class TestController {
	private final TestService testService;

	@PostMapping("/passTest")
	public void passTest(@RequestBody UserProgressRequest userProgress){
		testService.addPassedTest(userProgress);
	}

	@GetMapping("/getProgress")
	public List<UserProgressRequest> getUser(@RequestParam String userId) throws ExecutionException, InterruptedException {
		return testService.getProgress(userId);
	}
}
