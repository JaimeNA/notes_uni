package ar.edu.itba.eda;

import org.joda.time.Instant;
import org.joda.time.Period;

public class MyTimer {
    private Instant instant;
    private Period period;

    public MyTimer() {
        this.instant = new Instant();
    }

    public MyTimer(long startTime) {
        this.instant = new Instant(startTime);
    }

    public void stop() {
        this.period = new Period(this.instant.getMillis(), Instant.now().getMillis());
    }

    public void stop(long stopTime) {
        if (this.instant.getMillis() > stopTime)
            throw new RuntimeException("Invalid stop time");

        this.period = new Period(this.instant.getMillis(), stopTime);
    }

    @Override
    public String toString() {
        long hours = period.getHours() % 24;
        long minutes = period.getMinutes() % 60;

        return String.format("(%dms) %d days %d hours %d minutes", period.getMillis(), period.getDays(), hours, minutes);
        
    }

}