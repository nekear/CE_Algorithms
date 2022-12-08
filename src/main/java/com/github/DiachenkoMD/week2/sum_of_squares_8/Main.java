package com.github.DiachenkoMD.week2.sum_of_squares_8;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long fibIndex = scanner.nextLong();

        System.out.println(sumOfSquaresLastDigit(fibIndex % 60));
    }

    /**
     * That method uses naive approach just to check values
     */
    public static BigInteger sumOfSquaresLastDigit2(long index){
        long[] numbers = new long[]{0, 1};

        if(index < 2)
            return BigInteger.valueOf(numbers[(int) index]);

        BigInteger sum = BigInteger.ONE;

        for(int i = 2; i <= index; i++){
            long fib = numbers[0] + numbers[1];
            numbers[0] = numbers[1];
            numbers[1] = fib;
            sum = sum.add(BigInteger.valueOf(numbers[1]).multiply(BigInteger.valueOf(numbers[1])));
        }

        return sum;
    }

    public static long sumOfSquaresLastDigit(long index){
        long[] numbers = new long[]{0, 1};

        if(index < 2)
            return numbers[(int) index];

        long sum = 1;

        for(int i = 2; i <= index; i++){
            long fib = numbers[0] + numbers[1];
            numbers[0] = numbers[1];
            numbers[1] = fib % 10;
            sum += numbers[1] * numbers[1];
        }

        return sum % 10;
    }
}
