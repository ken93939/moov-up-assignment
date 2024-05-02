package com.kenkwok.assignment.adapters;

import com.kenkwok.assignment.adapters.dto.PersonInfo;
import com.kenkwok.assignment.domain.people.PersonService;
import com.kenkwok.assignment.domain.people.model.Person;
import com.kenkwok.assignment.ports.GetPersonPort;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("people")
public class GetPersonAdapter implements GetPersonPort {

	PersonService personService;

	public GetPersonAdapter(PersonService personService) {
		this.personService = personService;
	}

	@Override
	@GetMapping()
	public List<PersonInfo> getPersonBasicInfo() {
		List<Person> personList = personService.getPersonList();
		return personList.stream()
			.map(person -> toPersonInfo(person))
			.collect(Collectors.toList());
	}

	private PersonInfo toPersonInfo(Person person) {
		return new PersonInfo(
			person.getLastName(),
			person.getFirstName(),
			person.getPicture(),
			person.getLatitude(),
			person.getLongitude()
		);
	}
}
