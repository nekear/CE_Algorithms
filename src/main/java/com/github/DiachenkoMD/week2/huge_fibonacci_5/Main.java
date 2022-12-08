package com.github.DiachenkoMD.week2.huge_fibonacci_5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long fibIndex = scanner.nextLong();
        int mod = scanner.nextInt();

        System.out.println(getModOfFib(fibIndex,mod));
    }

    public static long getPisano(long m){
        long prev = 0;
        long curr = 1;
        long res = 0;

        for(int i = 0; i < m * m; i++)
        {
            long temp = curr;
            curr = (prev + curr) % m;
            prev = temp;

            if (prev == 0 && curr == 1)
                res = i + 1;
        }

        return res;
    }

    public static long getModOfFib(long index, int mod){
        long period = getPisano(mod);

        index = index % period;

        long[] numbers = new long[]{0, 1};

        if(index < 2)
            return numbers[(int) index];

        for(int i = 2; i <= index; i++){
            long fib = numbers[0] + numbers[1];
            numbers[0] = numbers[1];
            numbers[1] = fib % mod;
        }

        return numbers[1];
    }
}
