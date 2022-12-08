package com.github.DiachenkoMD.week2.greates_common_divisor_3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(getGCD(a,b));
    }

    public static int getGCD(int a, int b){
        if(b == 0)
            return a;

        return getGCD(b, a % b);
    }
}
