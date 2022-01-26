package com.danielmapar;

import java.util.*;
import java.util.concurrent.*;

public final class VotingAppConcurrent {
    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(12);

        Map<String, Integer> votes = new ConcurrentHashMap<>();

        List<Future<?>> futures = new ArrayList<>(10_000);
        for (int i = 0; i < 10_000; i++) {
            futures.add(
                    executor.submit(() -> {
                        votes.compute("Larry", (k, v) -> (v == null) ? 1 : v + 1);
                    }));
        }
        for (Future<?> future : futures) {
            future.get();
        }
        executor.shutdown();

        System.out.println(votes);
    }
}
