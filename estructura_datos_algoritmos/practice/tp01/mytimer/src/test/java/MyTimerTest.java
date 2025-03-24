import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.itba.eda.MyTimer;

public class MyTimerTest {

    static final long MILIS_PER_SECOND = 1000;
    static final long MILIS_PER_MINUTE = MILIS_PER_SECOND * 60;
    static final long MILIS_PER_HOUR = MILIS_PER_MINUTE * 60;
    static final long MILIS_PER_DAY = MILIS_PER_HOUR * 24;

    MyTimer timer;

    static void initTest() {
        System.out.println("Initiating tests...");
    }

    @BeforeEach
    void initTimer(){
        timer = new MyTimer(100);
    }

    @Test
    void getDurationTest() {

        timer.stop(120);
        assertEquals(20, timer.deltaTime());
    }

    @Test
    void getDaysTest() {

        timer.stop((MILIS_PER_DAY * 3) + 100);
        assertEquals(3, timer.days());
    }

    @Test
    void getHoursTest() {

        timer.stop((MILIS_PER_HOUR * 3) + 100);
        assertEquals(3, timer.hours());
    }

    @Test
    void getMinutesTest() {

        timer.stop((MILIS_PER_MINUTE * 3) + 100);
        assertEquals(3, timer.minutes());
    }

    @Test
    void invalidStopTest() {
        assertThrows(RuntimeException.class, () -> timer.stop(2));
    }

    @Test
    void invalidDurationTest() {
        assertThrows(RuntimeException.class, () -> timer.deltaTime());
    }
}
