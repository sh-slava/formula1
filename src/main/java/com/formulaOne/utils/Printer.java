package com.formulaOne.utils;

import java.time.*;
import java.util.List;
import java.util.function.Consumer;

import com.formulaOne.constants.*;
import com.formulaOne.objects.Lap;

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

		laps.stream().sorted().forEachOrdered(new Consumer<>() {
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
		int spacesInName = maxRacerLength - lap.getRacer().getName().length() - String.valueOf(index).length();
		int spacesInTeam = maxTeamLength - lap.getRacer().getTeam().length();

		String line = String.format("%d. %s%s | %s%s | %s", index, lap.getRacer().getName(),
				buildString(spacesInName, " "), lap.getRacer().getTeam(), buildString(spacesInTeam, " "),
				durationTime(lap));
		output.append(line).append(LINE_SEPARATOR);

		if (index == numberOfWinners) {
			output.append(buildString(line.length(), "-")).append(LINE_SEPARATOR);
		}
		return output.toString();
	}

	private String durationTime(Lap lap) {
		LocalTime time = LocalTime.ofNanoOfDay(Duration.between(lap.getStart(), lap.getFinish()).toNanos());
		return time.format(FormatterConstants.DURATION_TIME_FORMATTER);
	}

	public static String buildString(int numberOfSymbols, String symbol) {
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < numberOfSymbols; i++) {
			output.append(symbol);
		}
		return output.toString();
	}

}
