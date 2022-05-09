package com.stemco.java_stemco_backend.controllers;

import com.stemco.java_stemco_backend.services.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/{section}")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SectionController {

	private final SectionService sectionService;

	@GetMapping
	public List<String> getReadingList(@PathVariable("section") String section){
		return sectionService.getSectionList(section + "_samples");
	}

	@GetMapping("/test")
	public Map<String, Object> getReadingTest(@PathVariable("section") String section, @RequestParam String id) throws ExecutionException, InterruptedException {
		return sectionService.getSectionTest(section + "_samples", id);
	}
}
