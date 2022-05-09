package com.stemco.java_stemco_backend.security;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class FirebaseIdTokenValidationFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String idToken = request.getHeader("idToken");
		log.info("enter filter");
		if (request.getHeader("idToken") == null || request.getHeader("idToken").isEmpty()){
			log.info("token is null");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}


		FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
		try {
			firebaseAuth.verifyIdToken(idToken);
		} catch (FirebaseAuthException e) {
			log.info("token is wrong");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		filterChain.doFilter(request,response);
	}
}
