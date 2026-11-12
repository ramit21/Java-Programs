package code;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MaintenanceWindowMergerTest {

    private MaintenanceWindowMerger merger;

    @Before
    public void setUp() {
        merger = new MaintenanceWindowMerger();
    }

    @Test
    public void testMergeOverlappingIntervals() {
        List<Interval> input = Arrays.asList(
                new Interval(10, 30),
                new Interval(20, 40),
                new Interval(50, 60),
                new Interval(55, 70)
        );

        List<Interval> result = merger.merge(input);

        assertEquals(2, result.size());
        assertEquals(new Interval(10, 40), result.get(0));
        assertEquals(new Interval(50, 70), result.get(1));
    }

    @Test
    public void testMergeNoOverlaps() {
        List<Interval> input = Arrays.asList(
                new Interval(10, 20),
                new Interval(30, 40),
                new Interval(50, 60)
        );

        List<Interval> result = merger.merge(input);

        assertEquals(3, result.size());
        assertEquals(input, result);
    }

    @Test
    public void testMergeCompletelyContainedIntervals() {
        List<Interval> input = Arrays.asList(
                new Interval(10, 50),
                new Interval(20, 30),
                new Interval(5, 60)
        );

        List<Interval> result = merger.merge(input);

        assertEquals(1, result.size());
        assertEquals(new Interval(5, 60), result.get(0));
    }

    @Test
    public void testEmptyOrNullInput() {
        assertTrue(merger.merge(null).isEmpty());
        assertTrue(merger.merge(Collections.emptyList()).isEmpty());
    }
}