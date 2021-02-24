package com.formulaOne.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.*;

import com.formulaOne.constants.FormatterConstants;
import com.formulaOne.objects.*;

class PrinterTest {
	private final String LINE_SEPARATOR = System.lineSeparator();
	private static Printer printer;
	private static List<LocalDateTime> startTime;
	private static List<LocalDateTime> endTime;
	private static List<Racer> racers;

	@BeforeAll
	static void init() {
		printer = new Printer();

		startTime = List.of(
				LocalDateTime.parse("2018-05-24_12:14:51.985", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:09:41.921", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:03:15.145", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:14:12.054", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:17:58.810", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:13:04.512", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:02:51.003", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:03:01.250", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:18:20.125", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:06:13.511", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:04:45.513", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:02:49.914", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:07:23.645", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:05:14.511", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:12:01.035", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:16:11.648", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:02:58.917", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:18:37.735", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:00:00.000", FormatterConstants.DATA_TIME_FORMATTER)
				);

		endTime = List.of(
				LocalDateTime.parse("2018-05-24_12:16:05.164", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:10:54.750", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:04:28.095", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:15:24.067", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:19:11.838", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:14:17.169", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:04:04.396", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:04:13.889", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:19:32.585", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:07:26.834", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:05:58.778", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:04:02.979", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:08:36.586", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:06:27.441", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:13:13.883", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:17:24.354", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:04:03.332", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:19:50.198", FormatterConstants.DATA_TIME_FORMATTER),
				LocalDateTime.parse("2018-05-24_12:01:12.434", FormatterConstants.DATA_TIME_FORMATTER)
				);

		racers = List.of(
				new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA"),
				new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI"), new Racer("CSR", "Carlos Sainz", "RENAULT"),
				new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
				new Racer("EOF", "Esteban Ocon", "FORCE INDIA MERCEDES"),
				new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT"), new Racer("KMH", "Kevin Magnussen", "HAAS FERRARI"),
				new Racer("KRF", "Kimi Raikkonen", "FERRARI"), new Racer("LHM", "Lewis Hamilton", "MERCEDES"),
				new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES"),
				new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI"), new Racer("NHR", "Nico Hulkenberg", "RENAULT"),
				new Racer("PGS", "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA"),
				new Racer("RGH", "Romain Grosjean", "HAAS FERRARI"),
				new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES"),
				new Racer("SSW", "Sergey Sirotkin", "WILLIAMS MERCEDES"), new Racer("SVF", "Sebastian Vettel", "FERRARI"),
				new Racer("SVM", "Stoffel Vandoorne", "MCLAREN RENAULT"), new Racer("VBM", "Valtteri Bottas", "MERCEDES")
				);
	}

	@Test
	void getStringForPrinting_shouldPrintCorrectly() {
		String expected = "1. Sebastian Vettel   | FERRARI                   | 01:04.415" + LINE_SEPARATOR
				+ "2. Daniel Ricciardo   | RED BULL RACING TAG HEUER | 01:12.013" + LINE_SEPARATOR
				+ "3. Valtteri Bottas    | MERCEDES                  | 01:12.434" + LINE_SEPARATOR
				+ "4. Lewis Hamilton     | MERCEDES                  | 01:12.460" + LINE_SEPARATOR
				+ "5. Stoffel Vandoorne  | MCLAREN RENAULT           | 01:12.463" + LINE_SEPARATOR
				+ "6. Kimi Raikkonen     | FERRARI                   | 01:12.639" + LINE_SEPARATOR
				+ "7. Fernando Alonso    | MCLAREN RENAULT           | 01:12.657" + LINE_SEPARATOR
				+ "8. Sergey Sirotkin    | WILLIAMS MERCEDES         | 01:12.706" + LINE_SEPARATOR
				+ "9. Charles Leclerc    | SAUBER FERRARI            | 01:12.829" + LINE_SEPARATOR
				+ "10. Sergio Perez      | FORCE INDIA MERCEDES      | 01:12.848" + LINE_SEPARATOR
				+ "11. Romain Grosjean   | HAAS FERRARI              | 01:12.930" + LINE_SEPARATOR
				+ "12. Pierre Gasly      | SCUDERIA TORO ROSSO HONDA | 01:12.941" + LINE_SEPARATOR
				+ "13. Carlos Sainz      | RENAULT                   | 01:12.950" + LINE_SEPARATOR
				+ "14. Esteban Ocon      | FORCE INDIA MERCEDES      | 01:13.028" + LINE_SEPARATOR
				+ "15. Nico Hulkenberg   | RENAULT                   | 01:13.065" + LINE_SEPARATOR
				+ "-------------------------------------------------------------" + LINE_SEPARATOR
				+ "16. Brendon Hartley   | SCUDERIA TORO ROSSO HONDA | 01:13.179" + LINE_SEPARATOR
				+ "17. Marcus Ericsson   | SAUBER FERRARI            | 01:13.265" + LINE_SEPARATOR
				+ "18. Lance Stroll      | WILLIAMS MERCEDES         | 01:13.323" + LINE_SEPARATOR
				+ "19. Kevin Magnussen   | HAAS FERRARI              | 01:13.393" + LINE_SEPARATOR;

		List<Lap> laps = new ArrayList<>();
		for (int i = 0; i < racers.size(); i++) {
			Duration duration = Duration.between(startTime.get(i), endTime.get(i));
			Lap lap = new Lap(racers.get(i), startTime.get(i), endTime.get(i), duration);
			laps.add(lap);
		}
		assertEquals(expected, printer.getStringForPrinting(laps, 15));
	}

	@Test
	void getStringForPrinting_shouldPrintLine_whenDifferentNumberOfWinners() {
		String expected = "1. Sebastian Vettel   | FERRARI                   | 01:04.415" + LINE_SEPARATOR
				+ "2. Daniel Ricciardo   | RED BULL RACING TAG HEUER | 01:12.013" + LINE_SEPARATOR
				+ "3. Valtteri Bottas    | MERCEDES                  | 01:12.434" + LINE_SEPARATOR
				+ "4. Lewis Hamilton     | MERCEDES                  | 01:12.460" + LINE_SEPARATOR
				+ "5. Stoffel Vandoorne  | MCLAREN RENAULT           | 01:12.463" + LINE_SEPARATOR
				+ "6. Kimi Raikkonen     | FERRARI                   | 01:12.639" + LINE_SEPARATOR
				+ "7. Fernando Alonso    | MCLAREN RENAULT           | 01:12.657" + LINE_SEPARATOR
				+ "-------------------------------------------------------------" + LINE_SEPARATOR
				+ "8. Sergey Sirotkin    | WILLIAMS MERCEDES         | 01:12.706" + LINE_SEPARATOR
				+ "9. Charles Leclerc    | SAUBER FERRARI            | 01:12.829" + LINE_SEPARATOR
				+ "10. Sergio Perez      | FORCE INDIA MERCEDES      | 01:12.848" + LINE_SEPARATOR
				+ "11. Romain Grosjean   | HAAS FERRARI              | 01:12.930" + LINE_SEPARATOR
				+ "12. Pierre Gasly      | SCUDERIA TORO ROSSO HONDA | 01:12.941" + LINE_SEPARATOR
				+ "13. Carlos Sainz      | RENAULT                   | 01:12.950" + LINE_SEPARATOR
				+ "14. Esteban Ocon      | FORCE INDIA MERCEDES      | 01:13.028" + LINE_SEPARATOR
				+ "15. Nico Hulkenberg   | RENAULT                   | 01:13.065" + LINE_SEPARATOR
				+ "16. Brendon Hartley   | SCUDERIA TORO ROSSO HONDA | 01:13.179" + LINE_SEPARATOR
				+ "17. Marcus Ericsson   | SAUBER FERRARI            | 01:13.265" + LINE_SEPARATOR
				+ "18. Lance Stroll      | WILLIAMS MERCEDES         | 01:13.323" + LINE_SEPARATOR
				+ "19. Kevin Magnussen   | HAAS FERRARI              | 01:13.393" + LINE_SEPARATOR;

		List<Lap> laps = new ArrayList<>();
		for (int i = 0; i < racers.size(); i++) {
			Duration duration = Duration.between(startTime.get(i), endTime.get(i));
			Lap lap = new Lap(racers.get(i), startTime.get(i), endTime.get(i), duration);
			laps.add(lap);
		}
		assertEquals(expected, printer.getStringForPrinting(laps, 7));
	}

	@Test
	void getStringForPrinting_shouldPrintLine_whenOneWinner() {
		String expected = "1. Daniel Ricciardo  | RED BULL RACING TAG HEUER | 01:12.013" + LINE_SEPARATOR
				+ "------------------------------------------------------------" + LINE_SEPARATOR
				+ "2. Lewis Hamilton    | MERCEDES                  | 01:12.460" + LINE_SEPARATOR
				+ "3. Kimi Raikkonen    | FERRARI                   | 01:12.639" + LINE_SEPARATOR
				+ "4. Fernando Alonso   | MCLAREN RENAULT           | 01:12.657" + LINE_SEPARATOR
				+ "5. Charles Leclerc   | SAUBER FERRARI            | 01:12.829" + LINE_SEPARATOR
				+ "6. Carlos Sainz      | RENAULT                   | 01:12.950" + LINE_SEPARATOR
				+ "7. Esteban Ocon      | FORCE INDIA MERCEDES      | 01:13.028" + LINE_SEPARATOR
				+ "8. Brendon Hartley   | SCUDERIA TORO ROSSO HONDA | 01:13.179" + LINE_SEPARATOR
				+ "9. Lance Stroll      | WILLIAMS MERCEDES         | 01:13.323" + LINE_SEPARATOR
				+ "10. Kevin Magnussen  | HAAS FERRARI              | 01:13.393" + LINE_SEPARATOR;

		List<Lap> laps = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Duration duration = Duration.between(startTime.get(i), endTime.get(i));
			Lap lap = new Lap(racers.get(i), startTime.get(i), endTime.get(i), duration);
			laps.add(lap);
		}
		assertEquals(expected, printer.getStringForPrinting(laps, 1));
	}

	@Test
	void getStringForPrinting_shouldThrowIllegalArgumentException() {
		List<Lap> emptyLaps = new ArrayList<>();
		List<Lap> laps = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			Duration duration = Duration.between(startTime.get(i), endTime.get(i));
			Lap lap = new Lap(racers.get(i), startTime.get(i), endTime.get(i), duration);
			laps.add(lap);
		}
		assertAll(
				() -> assertThrows(IllegalArgumentException.class, () -> printer.getStringForPrinting(laps, 0)),
				() -> assertThrows(IllegalArgumentException.class, () -> printer.getStringForPrinting(laps, -1)),
				() -> assertThrows(IllegalArgumentException.class, () -> printer.getStringForPrinting(emptyLaps, 2)),
				() -> assertThrows(IllegalArgumentException.class, () -> printer.getStringForPrinting(null, 2)));
	}
}
