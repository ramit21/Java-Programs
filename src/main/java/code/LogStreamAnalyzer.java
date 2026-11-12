package code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

public class LogStreamAnalyzer {

    /**
     * Classic Example 1: Find the top K most visited 3-page sequences.
     * Sequences are tracked per customer in chronological order.
     */
    public List<Map.Entry<String, Integer>> findTopKSequences(List<LogEntry> logs, int k) {
        if (logs == null || logs.isEmpty() || k <= 0) {
            return Collections.emptyList();
        }

        // Sort logs globally by timestamp
        logs.sort(Comparator.comparingLong(LogEntry::getTimestamp));

        // Group page visits by customerId
        Map<String, List<String>> customerPages = new HashMap<>();
        for (LogEntry log : logs) {
            customerPages
                    .computeIfAbsent(log.getCustomerId(), key -> new ArrayList<>())
                    .add(log.getPageId());
        }

        // Count frequencies of all 3-page sliding windows
        Map<String, Integer> sequenceCounts = new HashMap<>();
        for (List<String> pages : customerPages.values()) {
            if (pages.size() < 3) {
                continue;
            }

            for (int i = 0; i <= pages.size() - 3; i++) {
                String seq = pages.get(i) + "->"
                        + pages.get(i + 1) + "->"
                        + pages.get(i + 2);

                sequenceCounts.put(
                        seq,
                        sequenceCounts.getOrDefault(seq, 0) + 1
                );
            }
        }

        // Min heap for top K
        PriorityQueue<Map.Entry<String, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<String, Integer> entry : sequenceCounts.entrySet()) {
            minHeap.offer(entry);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Return results in descending frequency order
        List<Map.Entry<String, Integer>> result = new ArrayList<>(minHeap);
        result.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        return result;
    }

    /**
     * Classic Example 2: Find the first non-repeating request ID.
     */
    public String findFirstNonRepeatingRequestId(List<String> requestIds) {
        if (requestIds == null || requestIds.isEmpty()) {
            return null;
        }

        Map<String, Integer> frequencyMap = new LinkedHashMap<>();

        for (String requestId : requestIds) {
            frequencyMap.put(
                    requestId,
                    frequencyMap.getOrDefault(requestId, 0) + 1
            );
        }

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return null;
    }
}

// --- Supporting Model Class ---

@Data
@AllArgsConstructor
@NoArgsConstructor
class LogEntry {
    private long timestamp;
    private String customerId;
    private String pageId;
}