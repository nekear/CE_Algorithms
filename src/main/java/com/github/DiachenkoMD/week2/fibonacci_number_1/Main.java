package com.github.DiachenkoMD.week2.fibonacci_number_1;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int fibIndex = scanner.nextInt();

        System.out.println(getFib(fibIndex));

    }
    public static BigInteger getFib(int index){
        BigInteger[] numbers = new BigInteger[]{BigInteger.ZERO, BigInteger.ONE};

        if(index < 2)
            return numbers[index];

        BigInteger sum = BigInteger.ZERO;

        for(int i = 2; i <= index; i++){
            BigInteger fib = numbers[0].add(numbers[1]);
            numbers[0] = numbers[1];
            numbers[1] = fib;
            sum.add(fib);
        }

        System.out.println(sum);

        return numbers[1];
    }
}
