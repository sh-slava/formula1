package com.formulaOne.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.formulaOne.objects.Racer;

class ParserTest {
	private Parser parser = new Parser();
	private static final DateTimeFormatter DATA_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

	@TestInstance(PER_CLASS)
	@Nested
	class parserRacerTest {

		@Test
		void parsRacer_shouldThrowIllegalArgumentException_whenNullOrEmptyInput() {
			assertAll(
					() -> assertThrows(IllegalArgumentException.class, () -> parser.parseRacer(null)),
					() -> assertThrows(IllegalArgumentException.class, () -> parser.parseRacer("")));
		}

		@ParameterizedTest
		@MethodSource("racersInfoProvider")
		void parsRacer_shouldReturnRacer_whenValidFormatInput(String racerString, Object racerObject) {
			assertEquals(racerObject, parser.parseRacer(racerString));

		}

		private Object[][] racersInfoProvider() {
			return new Object[][] {
				{ "SVF_Sebastian Vettel_FERRARI", new Racer("SVF", "Sebastian Vettel", "FERRARI") },
				{ "LHM_Lewis Hamilton_MERCEDES", new Racer("LHM", "Lewis Hamilton", "MERCEDES") },
				{ "KRF_Kimi Raikkonen_FERRARI", new Racer("KRF", "Kimi Raikkonen", "FERRARI") },
				{ "QQQ_Some Unknown Racer_Best Team!", new Racer("QQQ", "Some Unknown Racer", "Best Team!") } 
				};
		}

		@ParameterizedTest
		@MethodSource("racersInvalidInfoProvider")
		void parsRacer_shouldThrowIllegalArgumentException_whenInvalidFormat(String invalidRacerInfo) {
			assertThrows(IllegalArgumentException.class, () -> parser.parseRacer(invalidRacerInfo));

		}

		private List<String> racersInvalidInfoProvider() {
			List<String> list = List.of(
					"svf_Sebastian Vettel_FERRARI", 
					"457_Lewis Hamilton_MERCEDES",
					"LHM-Lewis Hamilton-MERCEDES", 
					"LHM Hamilton MERCEDES", 
					"LHME_Hamilton_MERCEDES",
					"KRF5_Kimi Raikkonen_FERRARI", 
					"KRF_Kimi_Raik5konen_FERRARI", 
					"KRF_Kimi*Raikkonen*FERRARI",
					"KRF_Kimi Raikkonen_14578", 
					"QQQ_452 852 695_Best Team!"
					);
			return list;
		}
	}

	@TestInstance(PER_CLASS)
	@Nested
	class parserTimeTest {

		@Test
		void parsTime_shouldThrowIllegalArgumentException_whenNullOrEmptyInput() {
			assertAll(
					() -> assertThrows(IllegalArgumentException.class, () -> parser.parseTime(null)),
					() -> assertThrows(IllegalArgumentException.class, () -> parser.parseTime("")));
		}

		@ParameterizedTest
		@MethodSource("timeInfoProvider")
		void parsRacer_shouldReturnRacer_whenValidFormatInput(String timeString, Object timeObject) {
			assertEquals(timeObject, parser.parseTime(timeString));

		}

		private Object[][] timeInfoProvider() {
			return new Object[][] {
					{ "2018-05-24_12:02:58.917", LocalDateTime.parse(("2018-05-24_12:02:58.917"), DATA_TIME_PATTERN) },
					{ "2018-05-24_12:02:49.914", LocalDateTime.parse(("2018-05-24_12:02:49.914"), DATA_TIME_PATTERN) },
					{ "2018-05-24_12:05:58.778", LocalDateTime.parse(("2018-05-24_12:05:58.778"), DATA_TIME_PATTERN) },
					{ "2018-05-24_12:06:27.441", LocalDateTime.parse(("2018-05-24_12:06:27.441"), DATA_TIME_PATTERN) },
					{ "2020-12-24_05:01:07.111", LocalDateTime.parse(("2020-12-24_05:01:07.111"), DATA_TIME_PATTERN) } };
		}

		@ParameterizedTest
		@MethodSource("timeInvalidInfoProvider")
		void parsRacer_shouldThrowIllegalArgumentException_whenInvalidFormat(String invalidTimeInfo) {
			assertThrows(IllegalArgumentException.class, () -> parser.parseTime(invalidTimeInfo));
		}

		private List<String> timeInvalidInfoProvider() {
			List<String> list = List.of(
					"2018-05-24-12:02:58.917", 
					"2018-05-24_12:02:58.9171", 
					"2018-05-24_12:02:58:917",
					"20187-05-24_12:02:58.917", 
					"SVF2018-12-24_12:02:58.917", 
					"2018-05-33_12:02:58.917",
					"2018-05-24 12:02:58.917"
					);
			return list;
		}

		@ParameterizedTest
		@MethodSource("incorrectDataTimeInfo")
		void parsRacer_shouldThrowIllegalArgumentException_whenInvalidDataTimeInformation(String invalidDataTimeInfo) {
			assertThrows(IllegalArgumentException.class, () -> parser.parseTime(invalidDataTimeInfo));
		}

		private List<String> incorrectDataTimeInfo() {
			List<String> list = List.of(
					"2018-13-24_12:02:58.917", 
					"2018-05-33_12:02:58.917", 
					"2018-05-24_25:02:58.917",
					"2018-05-24_12:61:58.917", 
					"2018-05-24_12:02:60.917", 
					"2018-05-24_12:02:61.917"
					);
			return list;
		}
	}
}
