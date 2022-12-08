package com.github.DiachenkoMD.week4.n3_majority_element;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MajorityElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        long[] values = new long[n];

        for(int i = 0; i < n; i++){
            values[i] = scanner.nextInt();
        }

        System.out.println(containsMajority(values));
    }

    public static int containsMajority(long[] values){
        final int n2 = values.length / 2;
        Map<Long, Integer> amounts = new HashMap<>();

        for (long current : values) {
            int amount = 0;
            if (!amounts.containsKey(current)) {
                amounts.put(current, 0);
            } else {
                amount = amounts.get(current);
            }

            amounts.put(current, amount + 1);

            if (amount + 1 > n2)
                return 1;
        }

        return 0;
    }
}
