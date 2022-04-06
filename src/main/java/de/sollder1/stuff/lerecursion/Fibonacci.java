package de.sollder1.stuff.lerecursion;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private static final Map<Integer, BigInteger> PRECALCULATED_MAP = new HashMap<>();

    public static BigInteger optimizedRecursiveFibonacci(int depth) {

        if (depth < 3) {
            throw new IllegalArgumentException("Min 3");
        }

        for (int i = 3; i <= depth; i++) {
            PRECALCULATED_MAP.put(i, recursiveFibonacci(i));
            PRECALCULATED_MAP.remove(i - 1000);
        }

        return PRECALCULATED_MAP.get(depth);
    }

    public static BigInteger recursiveFibonacci(int n) {
        if (n <= 2) {
            return BigInteger.ONE;
        }
        if (PRECALCULATED_MAP.containsKey(n)) {
            return PRECALCULATED_MAP.get(n);
        }

        return recursiveFibonacci(n - 1).add(recursiveFibonacci(n - 2));
    }

    public static BigInteger iterativeFibonacci(int n) {

        BigInteger last = BigInteger.TWO;
        BigInteger lastBefore = BigInteger.ONE;
        BigInteger currVal = null;

        for (int i = 3; i < n; i++) {
            currVal = last.add(lastBefore);
            lastBefore = last;
            last = currVal;
        }

        return currVal;

    }


}
