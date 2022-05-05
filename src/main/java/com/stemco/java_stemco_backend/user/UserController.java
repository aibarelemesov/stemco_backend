package com.stemco.java_stemco_backend.user;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {


	private final UserService userService;


	@GetMapping("/testFilter")
	public Map<String, Object> testFilter() throws ExecutionException, InterruptedException {
		return userService.testQueryMethod();
	}

	@PostMapping("/passTest")
	public void passTest(@RequestBody UserProgressRequest userProgress){
		userService.addPassedTest(userProgress);
	}

	@GetMapping("/getProgress")
	public List<UserProgressResponse> getUser(@RequestParam String userId) throws ExecutionException, InterruptedException {
		return userService.getProgress(userId);
	}
}
