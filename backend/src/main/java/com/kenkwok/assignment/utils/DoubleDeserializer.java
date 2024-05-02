package com.kenkwok.assignment.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class DoubleDeserializer extends StdDeserializer<Double> {

	public DoubleDeserializer() {
		this(null);
	}

	protected DoubleDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Double deserialize(JsonParser p, DeserializationContext ctxt)
		throws IOException, JacksonException {
		String doubleStr = p.getText();
		if(doubleStr == null || doubleStr.isEmpty()) {
			return 0.0;
		}
		return Double.valueOf(doubleStr);
	}
}
