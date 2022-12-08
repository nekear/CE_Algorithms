package com.github.DiachenkoMD.week3.n4_max_ad_revenue;

import java.util.Arrays;
import java.util.Scanner;

public class MaxAdRevenue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] clicks = new int[n];
        int[] prices = new int[n];

        for(int i = 0; i < n; i++){
            clicks[i] = scanner.nextInt();
        }

        for(int i = 0; i < n; i++){
            prices[i] = scanner.nextInt();
        }

        System.out.println(getMaxAdRevenue2(clicks, prices));
    }

    public static long getMaxAdRevenue(int[] clicks, int[] prices){
        int maxClickIndex = 0;
        int maxPriceIndex = 0;

        for(int i = 0; i < clicks.length; i++){
            if(clicks[i] > clicks[maxClickIndex])
                maxClickIndex = i;

            if(prices[i] > prices[maxPriceIndex])
                maxPriceIndex = i;
        }

        if(clicks[maxClickIndex] == 0 || prices[maxPriceIndex] == 0)
            return 0;

        long prod = (long) clicks[maxClickIndex] * prices[maxPriceIndex];

        clicks[maxClickIndex] = 0;
        prices[maxPriceIndex] = 0;

        return prod + getMaxAdRevenue(clicks, prices);
    }

    public static int getMaxAdRevenue2(int[] clicks, int[] prices){
        Arrays.sort(clicks);
        Arrays.sort(prices);

        int sum = 0;
        for(int i = 0; i < clicks.length; i++){
            sum += clicks[i] * prices[i];
        }

        return sum;
    }
}
