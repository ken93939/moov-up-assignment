package com.kenkwok.assignment.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonInfo {

	public String lastName;

	public String firstName;

	public String picture;

	public double latitude;

	public double longitude;
}
