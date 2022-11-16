package com.woniu.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {
	
	/**
	 * Date格式化字符串
	 */
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {
		String string = p.getText().trim();
		  return LocalDate.parse(string, DateTimeFormatter.ofPattern(DATE_FORMAT));
	}

}
