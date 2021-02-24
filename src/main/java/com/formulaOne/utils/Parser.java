package com.formulaOne.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.formulaOne.constants.*;
import com.formulaOne.objects.Racer;

public class Parser {
	public Racer parsRacer(String racerInfo) {
		verifyNotEmpty(racerInfo);
		if (!racerInfo.matches(ValidationPatternConstants.RACER_PATTERN)) {
			throw new IllegalArgumentException(ExceptionsConstants.INVALID_FORMAT);
		}
		String[] param = racerInfo.split("_");
		return new Racer(param[0], param[1], param[2]);
	}

	public LocalDateTime parsTime(String timeString) {
		verifyNotEmpty(timeString);
		if (!timeString.matches(ValidationPatternConstants.TIME_PATTERN)) {
			throw new IllegalArgumentException(ExceptionsConstants.INVALID_FORMAT);
		}

		try {
			return LocalDateTime.parse(timeString, FormatterConstants.DATA_TIME_PATTERN);
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
