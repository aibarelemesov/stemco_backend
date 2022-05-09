package com.stemco.java_stemco_backend.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class SectionService {

	public List<String> getSectionList(String collection) {
		List<String> sectionList = new ArrayList<>();
		Firestore firestore = FirestoreClient.getFirestore();

		CollectionReference sectionSamples = firestore.collection(collection);
		Iterable<DocumentReference> query = sectionSamples.listDocuments();

		for (DocumentReference documentReference : query) {
			sectionList.add(documentReference.getId());
		}

		return sectionList;
	}

	public Map<String, Object> getSectionTest(String collection, String id) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();

		DocumentReference documentReference = firestore.collection(collection).document(id);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();


		return document.getData();
	}
}
