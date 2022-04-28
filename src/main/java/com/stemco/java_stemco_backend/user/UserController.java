package com.stemco.java_stemco_backend.user;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {
	private final UserService userService;

	@GetMapping("/testFilter")
	public Map<String, Object> testFilter() throws ExecutionException, InterruptedException {
		return userService.testQueryMethod();
	}

	@GetMapping("/update")
	public void update(@RequestParam(value = "uid", required = true) String uid,
								  @RequestParam(value = "name", required = false) String displayName,
								  @RequestParam(value = "imgUrl", required = false) String photoUrl)
			throws ExecutionException, InterruptedException, FirebaseAuthException, IOException {
		log.info("method was called");
		if (displayName != null){
			userService.updateUserDisplayName(uid, displayName);
		}
		if (photoUrl != null){
			userService.updateUserPhotoUrl(uid, photoUrl);
		}
	}

	@PostMapping("/create")
	public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
		return userService.createUser(user);
	}

	@GetMapping("/get")
	public User getUser(@RequestParam String email) throws ExecutionException, InterruptedException {
		return userService.getUser(email);
	}

	@PutMapping("/updateUser")
	public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
		return userService.updateUser(user);
	}

	@DeleteMapping("/delete")
	public String deleteUser(@RequestParam String email) throws ExecutionException, InterruptedException {
		return userService.deleteUser(email);
	}
}
