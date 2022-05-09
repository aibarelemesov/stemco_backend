package com.stemco.java_stemco_backend.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.stemco.java_stemco_backend.user.UserProgressRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TestService {

	public void addPassedTest(UserProgressRequest userProgress) {
		Firestore fireStore = FirestoreClient.getFirestore();

		userProgress.setDocId(fireStore.collection("user_progress").document().getId());
		fireStore.collection("user_progress").document(userProgress.getDocId()).set(userProgress);
	}

	public List<UserProgressRequest> getProgress(String userId) throws ExecutionException, InterruptedException {
		List<UserProgressRequest> list = new ArrayList<>();

		Firestore firestore = FirestoreClient.getFirestore();
		CollectionReference readingSamples = firestore.collection("user_progress");
		Query query = readingSamples.whereEqualTo("userId", userId);
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();

		for (DocumentSnapshot document : querySnapshotApiFuture.get().getDocuments()) {
			UserProgressRequest userProgressResponse = document.toObject(UserProgressRequest.class);

			if (userProgressResponse != null) {
				userProgressResponse.setDocId(document.getId());
			}

			list.add(userProgressResponse);
		}

		return list;
	}
}
