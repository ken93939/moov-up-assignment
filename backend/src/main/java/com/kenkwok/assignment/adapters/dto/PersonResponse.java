package com.kenkwok.assignment.adapters.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonResponse {
	@JsonProperty("_id")
	public String id;

	public Name name;

	public String email;

	public String picture;

	public Location location;
}
