package com.kenkwok.assignment.domain.people.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Person {

	public String lastName;

	public String firstName;

	public String picture;

	public double latitude;

	public double longitude;
}
