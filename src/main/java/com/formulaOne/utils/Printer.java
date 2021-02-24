package com.formulaOne.utils;

import java.time.*;
import java.util.List;
import java.util.function.Consumer;

import com.formulaOne.constants.*;
import com.formulaOne.objects.*;

public class Printer {
	private final static String LINE_SEPARATOR = System.lineSeparator();

	public String getStringForPrinting(List<Lap> laps, int numberOfWinners) {
		StringBuilder output = new StringBuilder();
		int maxRacerLength;
		int maxTeamLength;
		int numbersLength;

		if (numberOfWinners <= 0) {
			throw new IllegalArgumentException(ExceptionsConstants.INVALID_NEGATIVE_NUMBER_ARGUMENT);
		}
		if (laps == null || laps.isEmpty()) {
			throw new IllegalArgumentException(ExceptionsConstants.EMPTY_ARGUMENT);
		}

		numbersLength = String.valueOf(laps.size()).length();
		maxRacerLength = laps.stream().mapToInt(lap -> lap.getRacer().getName().length()).max().getAsInt()
				+ numbersLength;
		maxTeamLength = laps.stream().mapToInt(lap -> lap.getRacer().getTeam().length()).max().getAsInt();

		laps.stream().sorted().forEach(new Consumer<>() {
			int index = 0;

			@Override
			public void accept(Lap lap) {
				index++;
				output.append(buildResult(lap, maxRacerLength, maxTeamLength, index, numberOfWinners));
			}
		});
		return output.toString();
	}

	private String buildResult(Lap lap, int maxRacerLength, int maxTeamLength, int index, int numberOfWinners) {
		StringBuilder output = new StringBuilder();
		Racer racer = lap.getRacer();
		int spacesInName = maxRacerLength - racer.getName().length() - String.valueOf(index).length();
		int spacesInTeam = maxTeamLength - racer.getTeam().length();

		String line = String.format("%d. %s%s | %s%s | %s", index, racer.getName(),
				buildString(spacesInName, " "), racer.getTeam(), buildString(spacesInTeam, " "),
				durationTimeFormatter(lap.getDuration()));
		output.append(line).append(LINE_SEPARATOR);

		if (index == numberOfWinners) {
			output.append(buildString(line.length(), "-")).append(LINE_SEPARATOR);
		}
		return output.toString();
	}

	private String durationTimeFormatter(Duration duration) {
		LocalTime time = LocalTime.ofNanoOfDay(duration.toNanos());		
		return time.format(FormatterConstants.DURATION_TIME_FORMATTER);
	}

	public static String buildString(int numberOfSymbols, String symbol) {
		return String.valueOf(symbol).repeat(numberOfSymbols);
	}
}
