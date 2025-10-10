package org.example.cli;

import org.example.algorithms.InsertionSort;
import org.example.metrics.PerformanceTracker;

import java.util.Random;
import java.util.Scanner;

public class BenchmarkRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter array size (e.g. 1000): ");
        int n = scanner.nextInt();

        int[] arr = generateRandomArray(n);

        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSort sorter = new InsertionSort(tracker);

        System.out.println("Sorting " + n + " elements...");
        sorter.sort(arr);

        tracker.printReport();
        System.out.println("Working directory: " + System.getProperty("user.dir"));

        if (isSorted(arr)) {
            System.out.println("Array sorted correctly ✅");
        } else {
            System.out.println("❌ Sorting failed!");
        }

        tracker.saveToCSV(n, "insertion-sort");
    }

    private static int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}
