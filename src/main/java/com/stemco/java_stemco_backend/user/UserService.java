package com.stemco.java_stemco_backend.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class UserService {

	public Map<String, Object> testQueryMethod() throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
//
//		CollectionReference readingSamples = firestore.collection("reading_samples");
//		Iterable<DocumentReference> query = readingSamples.listDocuments();
//		Iterator<DocumentReference> iterator = query.iterator();
//
//		while (iterator.hasNext()) {
//			log.info(iterator.next().getId());
//		}

		DocumentReference documentReference = firestore.collection("reading_samples").document("tAlhuWdpeti2DcFNUw5k");
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		return document.getData();
	}

//	public User getUser(String email) throws ExecutionException, InterruptedException {
//		Firestore fireStore = FirestoreClient.getFirestore();
//		DocumentReference documentReference = fireStore.collection("users").document(email);
//		ApiFuture<DocumentSnapshot> future = documentReference.get();
//		DocumentSnapshot document = future.get();
//		User user;
//		if (document.exists()){
//			user = document.toObject(User.class);
//			return user;
//		}
//		return null;
//	}

	public void addPassedTest(UserProgressRequest userProgress) {
		Firestore fireStore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = fireStore.collection("user_progress").document(UUID.randomUUID().toString()).set(userProgress);
	}

	public List<UserProgressResponse> getProgress(String userId) throws ExecutionException, InterruptedException {
		List<UserProgressResponse> list = new ArrayList<>();

		Firestore firestore = FirestoreClient.getFirestore();
		CollectionReference readingSamples = firestore.collection("user_progress");
		Query query = readingSamples.whereEqualTo("userId", userId);
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();

		for (DocumentSnapshot document : querySnapshotApiFuture.get().getDocuments()) {
			list.add(document.toObject(UserProgressResponse.class));
			log.info(String.valueOf(document.getData()));
		}

		return list;
	}
}
