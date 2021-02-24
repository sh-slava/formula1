package com.formulaOne;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.formulaOne.objects.*;
import com.formulaOne.utils.Parser;
import com.formulaOne.utils.Reader;

class ResultTest {
	Result result = new Result(new Reader(), new Parser());
	private static final DateTimeFormatter DATA_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

	@Test
	void findResultLaps_ShouldReturnList() throws IOException {
		Reader readerMock = mock(Reader.class);
		Parser parserMock = mock(Parser.class);
		Result result = new Result(readerMock, parserMock);

		String abbreviationsFileName = "abbreviations.txt";
		String startTimeFileName = "start.log";
		String endTimeFileName = "end.log";

		List<String> racersList = List.of("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
				"SVF_Sebastian Vettel_FERRARI", "LHM_Lewis Hamilton_MERCEDES");

		List<String> startList = List.of("DRR2018-05-24_12:14:12.054", "SVF2018-05-24_12:02:58.917",
				"LHM2018-05-24_12:18:20.125");

		List<String> endList = List.of("DRR2018-05-24_12:15:24.067", "SVF2018-05-24_12:04:03.332",
				"LHM2018-05-24_12:19:32.585");

		List<LocalDateTime> timeList = List.of(LocalDateTime.parse("2018-05-24_12:14:12.054", DATA_TIME_PATTERN),
				LocalDateTime.parse("2018-05-24_12:15:24.067", DATA_TIME_PATTERN),
				LocalDateTime.parse("2018-05-24_12:02:58.917", DATA_TIME_PATTERN),
				LocalDateTime.parse("2018-05-24_12:04:03.332", DATA_TIME_PATTERN),
				LocalDateTime.parse("2018-05-24_12:18:20.125", DATA_TIME_PATTERN),
				LocalDateTime.parse("2018-05-24_12:19:32.585", DATA_TIME_PATTERN));

		List<Lap> expected = List.of(
				new Lap(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
						Duration.between(timeList.get(0), timeList.get(1))),
				new Lap(new Racer("SVF", "Sebastian Vettel", "FERRARI"),
						Duration.between(timeList.get(2), timeList.get(3))),
				new Lap(new Racer("LHM", "Lewis Hamilton", "MERCEDES"),
						Duration.between(timeList.get(4), timeList.get(5))));

		when(readerMock.readFile(abbreviationsFileName)).thenReturn(racersList);
		when(readerMock.readFile(startTimeFileName)).thenReturn(startList);
		when(readerMock.readFile(endTimeFileName)).thenReturn(endList);

		when(parserMock.parseRacer(anyString())).thenReturn(expected.get(0).getRacer())
				.thenReturn(expected.get(1).getRacer()).thenReturn(expected.get(2).getRacer());

		when(parserMock.parseTime(anyString())).thenReturn(timeList.get(0)).thenReturn(timeList.get(1))
				.thenReturn(timeList.get(2)).thenReturn(timeList.get(3)).thenReturn(timeList.get(4))
				.thenReturn(timeList.get(5));

		assertEquals(expected, result.findResultLaps(startTimeFileName, endTimeFileName, abbreviationsFileName));
		verify(readerMock, times(3)).readFile(anyString());
		verify(parserMock, times(3)).parseRacer(anyString());
		verify(parserMock, times(6)).parseTime(anyString());
		verifyNoMoreInteractions(readerMock);
		verifyNoMoreInteractions(parserMock);
	}

	@Test
	void findResultLaps_ShouldThrowFileNotFoundException_whenNoFile() {
		assertThrows(FileNotFoundException.class, () -> result.findResultLaps("start", "end", "abbreviations"));
	}

	@Test
	void findResultLaps_ShouldThrowIllegalArgumentException_whenEmptyFile() {
		assertThrows(IllegalArgumentException.class,
				() -> result.findResultLaps("emptyTestFile.txt", "emptyTestFile.txt", "emptyTestFile.txt"));
	}
}
