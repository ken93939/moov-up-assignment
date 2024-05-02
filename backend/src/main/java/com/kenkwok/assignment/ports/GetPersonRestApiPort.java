package com.kenkwok.assignment.ports;

import com.kenkwok.assignment.domain.people.model.Person;
import java.util.List;

public interface GetPersonRestApiPort {
	List<Person> getPersonList();
}
