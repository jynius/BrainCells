/*
 * 
 */
package us.jyni.cell.brain.base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import jakarta.persistence.AttributeConverter;

/**
 * @author jynius
 * @since 2020-08-05
 */
public class LocalDateTimeConverter implements AttributeConverter<String, LocalDateTime> {
	
	private final DateTimeFormatter formatter;

	/**
	 * formatter 설정
	 */
	public LocalDateTimeConverter() {
		this.formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd")
				.withResolverStyle(ResolverStyle.SMART);
	}
	
	/**
	 * String to DateTime
	 */
	@Override
	public LocalDateTime convertToDatabaseColumn(String attribute) {
		return LocalDateTime.parse(attribute, this.formatter);
	}

	/**
	 * DateTime to String
	 */
	@Override
	public String convertToEntityAttribute(LocalDateTime value) {
		return this.formatter.format(value);
	}
}
