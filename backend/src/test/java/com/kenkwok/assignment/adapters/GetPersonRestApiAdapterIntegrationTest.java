package com.kenkwok.assignment.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenkwok.assignment.adapters.dto.Location;
import com.kenkwok.assignment.adapters.dto.Name;
import com.kenkwok.assignment.adapters.dto.PersonResponse;
import com.kenkwok.assignment.domain.people.model.Person;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(SpringExtension.class)
class GetPersonRestApiAdapterIntegrationTest {

	public static MockWebServer mockBackEnd;

	@BeforeAll
	static void setup() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start(8123);
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@Test
	void testApiReturnCorrectResult() throws JsonProcessingException {
		HttpUrl url = mockBackEnd.url("/");
		WebClient testClient = WebClient.create(url.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		PersonResponse response = new PersonResponse("1", new Name("Chan", "Ming"),
			"chan@gmail.com", "http://www.google.com", new Location(38.1, 0.0));
		mockBackEnd.enqueue(
			new MockResponse().setBody(objectMapper.writeValueAsString(List.of(response)))
				.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
		GetPersonRestApiAdapter adapter = new GetPersonRestApiAdapter(testClient, url.toString(),
			"123");
		List<Person> result = adapter.getPersonList();
		assertEquals(1, result.size());
		Person actual = result.get(0);
		assertEquals(response.getName().getFirst(), actual.getFirstName());
		assertEquals(response.getLocation().getLatitude(), actual.getLatitude());
		assertEquals(response.getLocation().getLongitude(), actual.getLongitude());
	}
}