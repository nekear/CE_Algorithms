package com.github.DiachenkoMD.week3.n1_money_exchange;

import java.util.Scanner;

public class MoneyExchange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(exchange(scanner.nextInt()));
    }

    public static int exchange(int sum){
        return sum / 10 + ((sum % 10) / 5) + sum % 5;
    }
}
