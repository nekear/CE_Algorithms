package com.github.DiachenkoMD.week3.n7_max_salary;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MaxSalary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] numbers = new int[n];
        for(int i = 0; i < n; i++){
            numbers[i] = scanner.nextInt();
        }

        System.out.println(getMaxSalary(numbers));
    }

    public static String getMaxSalary(int[] numbers){
       return Arrays.stream(numbers).boxed().sorted(
               (o1, o2) -> Integer.parseInt(o2 + o1.toString()) - Integer.parseInt(o1 + o2.toString())
       ).map(Object::toString).collect(Collectors.joining(""));
    }
}
