package com.github.DiachenkoMD.week5.n1_money_change_again;

import java.util.Scanner;

public class MoneyChangeAgain {
    private static final int[] denominations = new int[]{1, 3, 4};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        System.out.println(moneyChange(n));
    }

    public static int moneyChange(int n){
        return new Changer(n, denominations).count();
    }
}

class Changer{
    private final int[] ways;
    private final int[] denominations;

    public Changer(int n, int[] denominations){
        this.ways = new int[n+1];
        this.denominations = denominations;
    }

    public int count(){
        return recursiveCount(ways.length-1);
    }

    private int recursiveCount(int n){
        if(n < 0)
            return -1;

        if(ways[n] != 0 || n == 0)
            return ways[n];

        int min = 0;
        for(int den = 0; den < denominations.length; den++){
            int tmp = recursiveCount(n - denominations[den]);

            if(tmp == -1)
                continue;

            if(den == 0){
                min = tmp;
            }else if(min > tmp){
                min = tmp;
            }
        }

        ways[n] = min + 1;

        return ways[n];
    }
}