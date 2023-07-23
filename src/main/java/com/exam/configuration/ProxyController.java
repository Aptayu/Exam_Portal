package com.exam.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

	@GetMapping("{url}")
	public ResponseEntity<?> proxyRequest(@PathVariable String url, @RequestParam("amount") int amount,
			@RequestParam("category") int category, @RequestParam("difficulty") String difficulty,
			@RequestParam("type") String type) {
		System.out.println("Inside proxyRequestMethod if ProxyController.java");
		RestTemplate restTemplate = new RestTemplate();
		String apiUrl = "https://opentdb.com/" + url; // Modify the URL as per your requirement
		
		
		// Create a query parameter string
        String queryParamString = "?amount=" + amount
                + "&category=" + category
                + "&difficulty=" + difficulty
                + "&type=" + type;
//        It's important to note that each concatenation operation creates a new String object, 
        apiUrl += queryParamString;
        System.out.println("apiUrl " + apiUrl);
		// Make the request to the external API
		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

		// Forward the response from the external API to the client
		return ResponseEntity.ok(response.getBody());
	}
}
