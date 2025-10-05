package org.example.algorithms;

import org.example.metrics.PerformanceTracker;

public class InsertionSort {

    private final PerformanceTracker tracker;

    public InsertionSort(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        tracker.start();
        int n = arr.length;
        boolean swapped;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            tracker.incrementArrayAccesses();

            int j = i - 1;
            swapped = false;

            while (j >= 0) {
                tracker.incrementComparisons();
                tracker.incrementArrayAccesses();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    tracker.incrementArrayAccesses();
                    tracker.incrementSwaps();
                    j--;
                    swapped = true;
                } else {
                    break;
                }
            }

            arr[j + 1] = key;
            tracker.incrementArrayAccesses();
            if (!swapped && i > n / 2) {
                break;
            }
        }
        tracker.stop();
    }
}
