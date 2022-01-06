package com.formulaOne.objects;

import java.time.Duration;
import java.util.Objects;

public class Lap implements Comparable<Lap> {
	private final Racer racer;
	private final Duration duration;

	public Lap(Racer racer, Duration duration) {
		this.racer = racer;
		this.duration = duration;
	}

	@Override
	public int compareTo(Lap lap) {
		return this.duration.compareTo(lap.getDuration());
	}

	public Racer getRacer() {
		return racer;
	}

	public Duration getDuration() {
		return duration;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Lap lap = (Lap) o;
		return Objects.equals(racer, lap.racer)
				&& Objects.equals(duration, lap.duration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(racer, duration);
	}
}
