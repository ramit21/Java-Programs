/**
 * Problem Statement: Interval & Time-Window Merging (Sorting + Two Pointers)
 *
 * Given a list of server maintenance windows [start, end], merge all overlapping intervals.
 * This pattern validates chronological sorting, boundary management, and pointer-based 
 * window merging common in distributed job scheduling and cloud infrastructure metrics.
 */
package code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaintenanceWindowMerger {

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return Collections.emptyList();
        }

        // Sort intervals by their start time to ensure chronological processing
        intervals.sort(Comparator.comparingLong(Interval::getStart));

        List<Interval> merged = new ArrayList<>();
        for (Interval current : intervals) {
            // If the merged list is empty or current interval does not overlap with the previous, add it
            if (merged.isEmpty() || merged.get(merged.size() - 1).getEnd() < current.getStart()) {
                merged.add(new Interval(current.getStart(), current.getEnd()));
            } else {
                // Overlap exists: merge by extending the end time of the previous interval if needed
                Interval last = merged.get(merged.size() - 1);
                last.setEnd(Math.max(last.getEnd(), current.getEnd()));
            }
        }

        return merged;
    }
}

// --- Supporting Model Class ---

@Data
@AllArgsConstructor
@NoArgsConstructor
class Interval {
    private long start;
    private long end;
}