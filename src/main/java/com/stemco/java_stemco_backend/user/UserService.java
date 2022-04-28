package com.stemco.java_stemco_backend.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class UserService {

	public Map<String, Object> testQueryMethod() throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = firestore.collection("reading_samples").document("tAlhuWdpeti2DcFNUw5k");
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		return document.getData();
	}

	public void updateUserPhotoUrl(String uid, String photoUrl) throws FirebaseAuthException {
		String substringToEncode = StringUtils.substringBetween(photoUrl, "/users_profile_pic", "?alt=media&token=");
		String encodedSubstring = URLEncoder.encode(substringToEncode, StandardCharsets.UTF_8);
		String encodedUrl = StringUtils.replace(photoUrl, substringToEncode, encodedSubstring);


		Firestore firestore = FirestoreClient.getFirestore();
		UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
				.setPhotoUrl(encodedUrl);
		UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
		UserRecord user = FirebaseAuth.getInstance().getUser(uid);


		log.info("the user is " + user.getEmail() + " " + user.getPhotoUrl());
	}


	public void updateUserDisplayName(String uid, String displayName) throws FirebaseAuthException {
		Firestore firestore = FirestoreClient.getFirestore();
		UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
				.setDisplayName(displayName);
		UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
		UserRecord user = FirebaseAuth.getInstance().getUser(uid);


		log.info("the user is " + user.getEmail() + " " + user.getDisplayName());
	}

	public String createUser(User user) throws ExecutionException, InterruptedException {
		Firestore fireStore = FirestoreClient.getFirestore();
		if (getUser(user.getEmail()) == null) {
			ApiFuture<WriteResult> collectionsApiFuture = fireStore.collection("users").document(user.getEmail()).set(user);
			return "User have been created";
		} else {
			return "User with email " + user.getEmail() + " already exists";
		}
	}

	public User getUser(String email) throws ExecutionException, InterruptedException {
		Firestore fireStore = FirestoreClient.getFirestore();
		DocumentReference documentReference = fireStore.collection("users").document(email);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		User user;
		if (document.exists()){
			user = document.toObject(User.class);
			return user;
		}
		return null;
	}

	public String updateUser(User user) throws ExecutionException, InterruptedException {
		Firestore fireStore = FirestoreClient.getFirestore();
		if (getUser(user.getEmail()) != null){
			ApiFuture<WriteResult> collectionsApiFuture = fireStore.collection("users").document(user.getEmail()).set(user);
			//return collectionsApiFuture.get().getUpdateTime().toString();
			return "User have been updated";
		} else {
			return "User with email " + user.getEmail() + " doesnt exists";
		}
	}

	public String deleteUser(String email){
		Firestore fireStore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResultApiFuture = fireStore.collection("users").document(email).delete();
		return "Successfully deleted user with email " + email;
	}
}
