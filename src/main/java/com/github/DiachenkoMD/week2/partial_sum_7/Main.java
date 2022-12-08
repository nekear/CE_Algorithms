package com.github.DiachenkoMD.week2.partial_sum_7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long m = scanner.nextLong();
        long n = scanner.nextLong();

        System.out.println(getLastDigitPartialFibSum(m, n));
    }
    public static long getLastDigitPartialFibSum(long m, long n){
        m = m % 60;
        n = n % 60;

        if(m > n)
            n += 60;

        long[] numbers = new long[]{0, 1};

        long sum = 0;

        for(int i = 1; i <= n; i++){
            if(i >= 2){
                long fib = numbers[0] + numbers[1];
                numbers[0] = numbers[1];
                numbers[1] = fib % 10;
            }

            if(i >= m){
                sum += numbers[1];
            }
        }

        return sum % 10;
    }
}
