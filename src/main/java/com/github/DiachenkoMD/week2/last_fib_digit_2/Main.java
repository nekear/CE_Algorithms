package com.github.DiachenkoMD.week2.last_fib_digit_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long fibIndex = scanner.nextLong();

        System.out.println(getLastDigitOfFib(fibIndex));
    }
    public static long getLastDigitOfFib(long index){
        return getFibSum(index % 60) % 10;
    }

    public static long getFibSum(long index){
        long[] numbers = new long[]{0, 1};

        if(index < 2)
            return numbers[(int) index];

        long sum = 1;

        for(int i = 2; i <= index; i++){
            long fib = numbers[0] + numbers[1];
            numbers[0] = numbers[1];
            numbers[1] = fib;
            sum += fib;
        }

        return sum;
    }
}
