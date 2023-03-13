import java.lang.management.ThreadMXBean;
import java.lang.management.ManagementFactory;

public class Timer {

    private static final double NANOSECONDS_PER_SECOND = 1000000000;

    private final ThreadMXBean threadTimer;
    private final long start;

    public Timer() {
        threadTimer = ManagementFactory.getThreadMXBean();
        start = threadTimer.getCurrentThreadCpuTime();
    }

    public double elapsed() {
        long now = threadTimer.getCurrentThreadCpuTime();
        return (now - start) / NANOSECONDS_PER_SECOND;
    }
}
