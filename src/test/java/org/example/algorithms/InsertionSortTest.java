package org.example.algorithms;

import org.example.metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        InsertionSort sorter = new InsertionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        InsertionSort sorter = new InsertionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        InsertionSort sorter = new InsertionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseArray() {
        int[] arr = {5, 4, 3, 2, 1};
        InsertionSort sorter = new InsertionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {4, 2, 2, 4, 1};
        InsertionSort sorter = new InsertionSort(new PerformanceTracker());
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 2, 4, 4}, arr);
    }
}
