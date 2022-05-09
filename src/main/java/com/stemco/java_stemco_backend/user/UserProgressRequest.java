package com.stemco.java_stemco_backend.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProgressRequest {
	private String docId;
	private String userId;
	private String userResult;
	private String section;
	private List<String> userAnswerList;
}
