package com.formulaOne.objects;

import java.util.Objects;

public class Racer {
	private final String name;
	private final String team;
	private final String abbreviation;

	public Racer(String abbreviation, String name, String team) {
		this.name = name;
		this.team = team;
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Racer racer = (Racer) o;
		return Objects.equals(name, racer.name) && Objects.equals(team, racer.team)
				&& Objects.equals(abbreviation, racer.abbreviation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, team, abbreviation);
	}

}
