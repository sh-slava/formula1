package com.formulaOne.constants;

import java.time.format.DateTimeFormatter;

public class FormatterConstants {
	public static final DateTimeFormatter DATA_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
   public static final DateTimeFormatter DURATION_TIME_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");

   private FormatterConstants() {}
}
