package core;

import java.time.Instant;

public class MyTimer {
    final static long MS_PER_SECOND = 1000;
    final static long MS_PER_MINUTE = MS_PER_SECOND * 60;

    private Instant startInstant, stopInstant;

    public MyTimer() {
        startInstant = Instant.now();
    }

    public MyTimer(long startTime) {
        startInstant = Instant.ofEpochMilli(startTime);
    }

    public void stop() {
        stopInstant = Instant.now();
    }

    public void stop(long stopTime) {
        if (startInstant.isAfter(stopInstant))
            throw new RuntimeException("Invalid stop time");

        stopInstant = Instant.ofEpochMilli(stopTime);
    }

    @Override
    public String toString() {
        long hours = hours() % 24;
        long minutes = minutes() % 60;

        return String.format("(%dms) %d days %d hours %d minutes", deltaTime(), days(), hours, minutes);
        
    }

    public long deltaTime() {

        Instant temp = stopInstant.minusMillis(startInstant.toEpochMilli());

        return temp.toEpochMilli();
    }

    public long days() {
        return hours() / 24;
    }

    public long hours() {
        return minutes() / 60;
    }

    public long minutes() {
        return deltaTime() / MS_PER_MINUTE; 
    }
}
