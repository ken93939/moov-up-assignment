package com.kenkwok.assignment.config;

import com.kenkwok.assignment.adapters.GetPersonRestApiAdapter;
import com.kenkwok.assignment.ports.GetPersonRestApiPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PersonConfig {
	private WebClient webClient;

	@Value("${api.person.basePath}")
	private String basePath;

	@Value("${api.person.apiKey}")
	private String apiKey;

	public PersonConfig(WebClient webClient) {
		this.webClient = webClient;
	}
	@Bean
	public GetPersonRestApiPort getPersonRestApiPort() {
		return new GetPersonRestApiAdapter(webClient, basePath, apiKey);
	}
}
