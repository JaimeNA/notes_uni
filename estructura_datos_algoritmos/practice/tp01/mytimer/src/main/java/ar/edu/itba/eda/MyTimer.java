package ar.edu.itba.eda;

/*
 * Timer Class build from scratch for the Data Structures and Algorithms class. 
 * Will be used as a plugin for the practice.
 */
public class MyTimer {
    final static long MS_PER_SECOND = 1000;
    final static long MS_PER_MINUTE = MS_PER_SECOND * 60;

    private long startTime, stopTime;

    public MyTimer() {
        this.startTime = System.currentTimeMillis();
    }

    public MyTimer(long startTime) {
        this.startTime = startTime;
    }

    public void stop() {
        this.stopTime = System.currentTimeMillis();
    }

    public void stop(long stopTime) {

        if (stopTime < startTime)
            throw new RuntimeException("Incorrect stop time(or time travel?)");

        this.stopTime = stopTime;
    }

    @Override
    public String toString() {
        long hours = hours() % 24;
        long minutes = minutes() % 60;

        return String.format("(%dms) %d days %d hours %d minutes", deltaTime(), days(), hours, minutes);
        
    }

    public long deltaTime() {

        if (stopTime < startTime)
            throw new RuntimeException("Timer wasn't stopped");

        return this.stopTime - this.startTime;
    }

    public long days() {
        System.out.println((float)hours() / 24.0);
        return hours() / 24;
    }

    public long hours() {
        return minutes() / 60;
    }

    public long minutes() {
        return deltaTime() / MS_PER_MINUTE; 
    }
}