package com.kenkwok.assignment.adapters.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kenkwok.assignment.utils.DoubleDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Location {

	@JsonDeserialize(using = DoubleDeserializer.class)
	public Double latitude;

	@JsonDeserialize(using = DoubleDeserializer.class)
	public Double longitude;
}
