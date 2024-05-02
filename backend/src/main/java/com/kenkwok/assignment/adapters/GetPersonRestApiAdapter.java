package com.kenkwok.assignment.adapters;

import com.kenkwok.assignment.adapters.dto.PersonResponse;
import com.kenkwok.assignment.domain.people.model.Person;
import com.kenkwok.assignment.ports.GetPersonRestApiPort;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

public class GetPersonRestApiAdapter implements GetPersonRestApiPort {

	WebClient client;

	String basePath;

	String apiKey;

	public GetPersonRestApiAdapter(WebClient client,
		String basePath, String apiKey) {
		this.client = client;
		this.basePath = basePath;
		this.apiKey = apiKey;
	}

	@Override
	public List<Person> getPersonList() {
		List<PersonResponse> response = client.get()
			.uri(String.format("%s/data", basePath))
			.header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", apiKey))
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<List<PersonResponse>>() {
			})
			.block();
		return response.stream()
			.map(res -> toPerson(res))
			.collect(Collectors.toList());
	}

	private Person toPerson(PersonResponse response) {
		return new Person(
			Optional.of(response.getName()).map(name -> name.getLast()).orElse(null),
			Optional.of(response.getName()).map(name -> name.getFirst()).orElse(null),
			response.getPicture(),
			Optional.of(response.getLocation()).map(loc -> loc.getLatitude()).orElse(0.0),
			Optional.of(response.getLocation()).map(loc -> loc.getLongitude()).orElse(0.0)
		);
	}
}
