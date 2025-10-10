package org.example.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long startTime;
    private long endTime;
    private boolean running = false;

    public void start() {
        comparisons = swaps = arrayAccesses = 0;
        startTime = System.nanoTime();
        running = true;
    }

    public void stop() {
        if (running) {
            endTime = System.nanoTime();
            running = false;
        }
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public void incrementArrayAccesses() {
        arrayAccesses++;
    }

    public void incrementArrayAccesses(int count) {
        arrayAccesses += count;
    }

    public long getElapsedTimeNano() {
        return endTime - startTime;
    }

    public long getElapsedTimeMillis() {
        return (endTime - startTime) / 1_000_000;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    public void printReport() {
        if (running) stop();
        System.out.println("=== Performance Report ===");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Array Accesses: " + arrayAccesses);
        System.out.println("Elapsed Time: " + getElapsedTimeMillis() + " ms");
        System.out.println("==========================");
    }

    public void saveToCSV(int n, String algorithmName) {
        if (running) stop();

        try {
            Path dir = Path.of(System.getProperty("user.dir"), "docs", "performance-plots");
            Files.createDirectories(dir);
            Path filePath = dir.resolve(algorithmName + "-results.csv");

            boolean newFile = Files.notExists(filePath);
            try (FileWriter writer = new FileWriter(filePath.toFile(), true)) {
                if (newFile) {
                    writer.write("n,time_ms,comparisons,swaps,array_accesses\n");
                }
                writer.write(String.format("%d,%d,%d,%d,%d%n",
                        n,
                        getElapsedTimeMillis(),
                        comparisons,
                        swaps,
                        arrayAccesses));
            }

            System.out.println("📊 Results saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("⚠️ Error saving results to CSV: " + e.getMessage());
        }
    }
}
