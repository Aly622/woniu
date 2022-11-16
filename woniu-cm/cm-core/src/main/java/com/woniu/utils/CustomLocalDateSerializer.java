package com.woniu.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomLocalDateSerializer extends JsonSerializer<LocalDate> {
	
	/**
	 * Date格式化字符串
	 */
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(value.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
	}

}
