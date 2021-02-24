package com.formulaOne;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import com.formulaOne.objects.*;
import com.formulaOne.utils.Parser;
import com.formulaOne.utils.Reader;

public class Result {
	private Reader reader;
	private Parser parser;

	public Result(Reader reader, Parser parser) {
		this.reader = reader;
		this.parser = parser;
	}

	public List<Lap> findResultLaps(String startTimeFileName, String endTimeFileName, String abbreviationFileName)
			throws FileNotFoundException {
		try {
			List<String> endTimeList = reader.readFile(endTimeFileName);
			List<String> startTimeList = reader.readFile(startTimeFileName);
			List<String> abbreviations = reader.readFile(abbreviationFileName);

			return findRacers(abbreviations).stream().map(racer -> buildLapByRacer(racer, startTimeList, endTimeList))
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new FileNotFoundException(e.getMessage());
		}
	}

	private List<Racer> findRacers(List<String> abbreviations) {
		return abbreviations.stream().map(parser::parseRacer).collect(Collectors.toList());
	}

	private Lap buildLapByRacer(Racer racer, List<String> startTime, List<String> endTime) {
		LocalDateTime start = findTimeByRacer(startTime, racer);
		LocalDateTime end = findTimeByRacer(endTime, racer);
		Duration bestLapTime = Duration.between(start, end);
		return new Lap(racer, start, end, bestLapTime);
	}

	private LocalDateTime findTimeByRacer(List<String> timeTable, Racer racer) {
		return parser.parseTime(timeTable.stream().filter(string -> string.contains(racer.getAbbreviation())).findAny()
				.orElseThrow(NoSuchElementException::new).substring(3));
	}
}
