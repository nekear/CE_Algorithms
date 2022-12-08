package com.github.DiachenkoMD.week3.n6_max_number_of_prizes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MaxNumberOfPrizes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long s = scanner.nextLong();

        List<Long> splits = split(s);
        System.out.println(splits.size());
        System.out.println(splits.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }

    public static List<Long> split(long s){
        long discriminant = 1 + 8 * s;
        double n = (-1 + Math.sqrt(discriminant)) / 2;

        int nSliced = (int) n;
        boolean isValidN = nSliced == n;

        List<Long> splits = new ArrayList<>(nSliced);

        for(int i = 1; i <= nSliced; i++){
            if(i == nSliced && !isValidN){
                splits.add(s - (((long) i *(i-1))/2));
            }else{
                splits.add((long) i);
            }
        }

        return splits;
    }
}
