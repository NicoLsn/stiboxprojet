package java.time;

import java.io.Serializable;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public final class LocalDate extends Object implements Temporal, TemporalAdjuster, ChronoLocalDate, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public long getLong(TemporalField field) {
		return 0;
	}

	@Override
	public boolean isSupported(TemporalField field) {
		return false;
	}

	@Override
	public int compareTo(ChronoLocalDate o) {

		return 0;
	}

	@Override
	public Chronology getChronology() {
		
		return null;
	}

	@Override
	public int lengthOfMonth() {

		return 0;
	}

	@Override
	public ChronoPeriod until(ChronoLocalDate endDateExclusive) {

		return null;
	}

	@Override
	public Temporal adjustInto(Temporal temporal) {

		return null;
	}

	@Override
	public boolean isSupported(TemporalUnit unit) {
	
		return false;
	}

	@Override
	public long until(Temporal endExclusive, TemporalUnit unit) {

		return 0;
	}

	public static LocalDate parse(String string, DateTimeFormatter formatage) {

		return null;
	}

	public static LocalDate now() {

		return null;
	}

	public static LocalDate of(int i, Month january, int j) {

		return null;
	}

	




}
