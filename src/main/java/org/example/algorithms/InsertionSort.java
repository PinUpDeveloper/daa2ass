package org.example.algorithms;

import org.example.metrics.PerformanceTracker;

public class InsertionSort {
    private final PerformanceTracker tracker;

    public InsertionSort(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        tracker.start();
        try {
            int n = arr.length;
            for (int i = 1; i < n; i++) {
                tracker.incrementArrayAccesses();
                int key = arr[i];
                int j = i - 1;

                while (j >= 0) {
                    tracker.incrementArrayAccesses();
                    tracker.incrementComparisons();

                    if (arr[j] > key) {
                        arr[j + 1] = arr[j];
                        tracker.incrementArrayAccesses(2);
                        tracker.incrementSwaps();
                        j--;
                    } else {
                        break;
                    }
                }

                arr[j + 1] = key;
                tracker.incrementArrayAccesses();
            }
        } finally {
            tracker.stop();
        }
    }
}
