package com.formulaOne.objects;

import java.time.*;
import java.util.Objects;

public class Lap implements Comparable<Lap> {
	private final Racer racer;
	private final LocalDateTime start;
	private final LocalDateTime finish;
	private final Duration duration;

	public Lap(Racer racer, LocalDateTime start, LocalDateTime finish, Duration duration) {
		this.racer = racer;
		this.start = start;
		this.finish = finish;
		this.duration = duration;
	}

	@Override
	public int compareTo(Lap lap) {
		return this.duration.compareTo(lap.getDuration());
	}

	public Racer getRacer() {
		return racer;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getFinish() {
		return finish;
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
		return Objects.equals(racer, lap.racer) && Objects.equals(start, lap.start) && Objects.equals(finish, lap.finish)
				&& Objects.equals(duration, lap.duration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(racer, start, finish, duration);
	}
}
