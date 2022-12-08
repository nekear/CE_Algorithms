package com.github.DiachenkoMD.week4.n2_binary_search_dupl;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BinarySearchDuplicates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long[] ks = new long[n];

        for(int i = 0; i < n; i++){
            ks[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] matches = new int[m];

        for(int i = 0; i < m; i++){
            matches[i] = binarySearch(ks, scanner.nextInt());
        }

        System.out.println(Arrays.stream(matches).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int binarySearch(long[] values, long key){
        if(key < values[0] || key > values[values.length - 1])
            return -1;

        int ceilingBound = 0;
        int upperBound = values.length-1;
        while(upperBound >= ceilingBound){
            int middle = (ceilingBound + upperBound) / 2;
            if(values[middle] == key){
                while(middle >= 0 && values[middle] == key)
                    middle--;

                return middle+1;
            }

            if(values[middle] > key) {
                upperBound = middle-1;
            }else{
                ceilingBound = middle+1;
            }
        }

        return -1;
    }
}