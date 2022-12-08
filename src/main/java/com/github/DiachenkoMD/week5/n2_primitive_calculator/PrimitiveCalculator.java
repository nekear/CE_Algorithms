package com.github.DiachenkoMD.week5.n2_primitive_calculator;

import java.util.*;
import java.util.stream.Collectors;

public class PrimitiveCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        List<Integer> vars = new Calculator(n).count();

        System.out.println(vars.size()-1);
        System.out.println(vars.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}

class Calculator{
    private final int[] ways;
    private static final int[] operands = new int[]{2, 3};
    private static final List<Integer> operations = new LinkedList<>();

    public Calculator(int n){
        this.ways = new int[n+1];
        ways[1] = 0;

        for(int i = 2; i < Math.min(4, ways.length); i++){
            ways[i] = 1;
        }
    }

    public List<Integer> count(){
        fillWays(ways.length-1);
        backtrack(ways.length - 1);
        Collections.reverse(operations);
        return operations;
    }

    private void backtrack(int n){
        operations.add(n);

        if(n <= 1)
            return;

        int min = ways[n - 1];
        int sent = n - 1;
        for(int operand : operands){
            if (n < operand || n % operand != 0)
                continue;

            if(ways[n / operand] < min) {
                min = ways[n / operand];
                sent = n / operand;
            }
        }

        backtrack(sent);
    }

    private int recursiveCount(int n){
        if(ways[n] != 0 || n == 0)
            return ways[n];

        int min = recursiveCount(n - 1);
        for(int operand : operands){
            if (n < operand || n % operand != 0)
                continue;

            int tmp = recursiveCount(n / operand);

            if(tmp < min) {
                min = tmp;
            }
        }

        ways[n] = min + 1;

        return ways[n];
    }

    private void fillWays(int n){
        for(int i = 2; i <= n; i++){
            if(ways[i] == 0 || ways[i] > ways[i-1] + 1){
                ways[i] = ways[i-1] + 1;
            }

            if(i*2 < ways.length && (ways[i*2] > ways[i] + 1 || ways[i*2] == 0)){
                ways[i*2] = ways[i] + 1;
            }

            if(i*3 < ways.length && (ways[i*3] > ways[i] + 1 || ways[i*3] == 0)){
                ways[i*3] = ways[i] + 1;
            }
        }
    }
}