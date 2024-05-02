package com.kenkwok.assignment.domain.people;

import com.kenkwok.assignment.adapters.GetPersonRestApiAdapter;
import com.kenkwok.assignment.domain.people.model.Person;
import com.kenkwok.assignment.ports.GetPersonRestApiPort;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	GetPersonRestApiPort apiAdapter;

	public PersonService(GetPersonRestApiPort apiAdapter) {
		this.apiAdapter = apiAdapter;
	}

	public List<Person> getPersonList() {
		return apiAdapter.getPersonList();
	}
}
