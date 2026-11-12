package code;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LogStreamAnalyzerTest {

    private LogStreamAnalyzer analyzer;

    @Before
    public void setUp() {
        analyzer = new LogStreamAnalyzer();
    }

    @Test
    public void testFindTopKSequences() {
        List<LogEntry> logs = Arrays.asList(
                new LogEntry(100L, "cust1", "Home"),
                new LogEntry(101L, "cust1", "Shop"),
                new LogEntry(102L, "cust1", "Cart"),
                new LogEntry(103L, "cust1", "Checkout"),
                new LogEntry(104L, "cust2", "Home"),
                new LogEntry(105L, "cust2", "Shop"),
                new LogEntry(106L, "cust2", "Cart")
        );

        // cust1 sequence 1: Home -> Shop -> Cart
        // cust1 sequence 2: Shop -> Cart -> Checkout
        // cust2 sequence 1: Home -> Shop -> Cart
        // "Home->Shop->Cart" appears 2 times total.

        List<Map.Entry<String, Integer>> topSequences =
                analyzer.findTopKSequences(logs, 1);

        assertFalse(topSequences.isEmpty());
        assertEquals("Home->Shop->Cart", topSequences.get(0).getKey());
        assertEquals(Integer.valueOf(2), topSequences.get(0).getValue());
    }

    @Test
    public void testFindFirstNonRepeatingRequestId() {
        List<String> stream = Arrays.asList(
                "req-A",
                "req-B",
                "req-A",
                "req-C",
                "req-B",
                "req-D"
        );

        // "req-A" and "req-B" repeat.
        // "req-C" is the first non-repeating request ID.
        String firstUnique =
                analyzer.findFirstNonRepeatingRequestId(stream);

        assertEquals("req-C", firstUnique);
    }

    @Test
    public void testFindFirstNonRepeatingRequestIdNoneFound() {
        List<String> stream = Arrays.asList(
                "req-A",
                "req-B",
                "req-A",
                "req-B"
        );

        String firstUnique =
                analyzer.findFirstNonRepeatingRequestId(stream);

        assertNull(firstUnique);
    }

    @Test
    public void testFindTopKSequencesEmptyInput() {
        List<Map.Entry<String, Integer>> result =
                analyzer.findTopKSequences(Arrays.asList(), 1);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindTopKSequencesInvalidK() {
        List<LogEntry> logs = Arrays.asList(
                new LogEntry(1L, "cust1", "Home"),
                new LogEntry(2L, "cust1", "Shop"),
                new LogEntry(3L, "cust1", "Cart")
        );

        List<Map.Entry<String, Integer>> result =
                analyzer.findTopKSequences(logs, 0);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindFirstNonRepeatingRequestIdEmptyInput() {
        assertNull(
                analyzer.findFirstNonRepeatingRequestId(Arrays.asList())
        );
    }
}