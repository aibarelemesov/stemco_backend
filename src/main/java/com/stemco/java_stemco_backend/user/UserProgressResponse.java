package com.stemco.java_stemco_backend.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProgressResponse {
	private String docId;
	private String result;
	private String section;
	private List<String> userAnswerList;
}
