package com.snkit.springprofiledemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

	@Value("${application.key}")
	public String profileKey;

	@GetMapping("/profileKey")
	public String getPofileKey() {

		return profileKey;

	}
	
	@GetMapping("/healthCheck")
	public String healthCheck() {
		return "It's Ok";
	}

}
