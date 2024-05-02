package com.kenkwok.assignment.ports;

import com.kenkwok.assignment.adapters.dto.PersonInfo;
import java.util.List;

public interface GetPersonPort {
	List<PersonInfo> getPersonBasicInfo();
}
