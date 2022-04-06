package de.sollder1.stuff.lerecursion;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        fibonacciTest();
    }

    private static void fibonacciTest() {
        List<Long> recursiveTimes = new ArrayList<>();
        for (int i = 0 ; i < 10; i++) {
            recursiveTimes.add(benchmark(() -> Fibonacci.optimizedRecursiveFibonacci(800_000)));
        }
        System.out.println("Rekursive Iterationen[ms]: " + recursiveTimes);

        List<Long> iterativeTimes = new ArrayList<>();
        for (int i = 0 ; i < 10; i++) {
            iterativeTimes.add(benchmark(() -> Fibonacci.iterativeFibonacci(800_000)));
        }
        System.out.println("Iterative Iterationen[ms]: " + iterativeTimes);

        //Ryzen 5600X: [8345, 6881, 8133, 8139, 8140, 8222, 8174, 8180, 8272, 8173]
        //Ryzen 5600X: [6291, 6287, 6322, 6392, 6368, 6283, 6259, 6438, 6407, 6376]

    }


    private static long benchmark(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }


}
