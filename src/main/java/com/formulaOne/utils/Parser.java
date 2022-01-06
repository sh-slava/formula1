package com.formulaOne.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.formulaOne.constants.*;
import com.formulaOne.objects.Racer;

public class Parser {
	public Racer parseRacer(String racerInfo) {
		verifyNotEmpty(racerInfo);
		if (!racerInfo.matches(ValidationPatternConstants.RACER_PATTERN)) {
			throw new IllegalArgumentException(ExceptionsConstants.INVALID_FORMAT);
		}	
		String[] param = racerInfo.split("_");
		String abbreviation = param[0];
		String name = param[1];
		String team = param[2];
		
		return new Racer(abbreviation, name, team);
	}

	public LocalDateTime parseTime(String timeString) {
		verifyNotEmpty(timeString);
		if (!timeString.matches(ValidationPatternConstants.TIME_PATTERN)) {
			throw new IllegalArgumentException(ExceptionsConstants.INVALID_FORMAT);
		}

		try {
			return LocalDateTime.parse(timeString, FormatterConstants.DATA_TIME_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(ExceptionsConstants.INVALID_DATA_ARGUMENT);
		}
	}

	private void verifyNotEmpty(String input) {
		if (input == null || input.isEmpty()) {
			throw new IllegalArgumentException(ExceptionsConstants.EMPTY_ARGUMENT);
		}
	}
}
