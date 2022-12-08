package com.github.DiachenkoMD.week6.n2_parentheses;

import java.util.Arrays;
import java.util.Scanner;

public class Parentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        System.out.println(getMaxValue(expression));
    }

    public static int getMaxValue(String exp){
        boolean minusPrefixed = exp.charAt(0) == '-';

        if(minusPrefixed)
            exp = exp.substring(1);

        int lvls = exp.toCharArray().length / 2 + 1;

        int[][] m = new int[lvls][lvls], M = new int[lvls][lvls];

        for(int i = 0; i < lvls; i++){
            m[i][i] = exp.charAt(i*2) - '0';
            M[i][i] = exp.charAt(i*2) - '0';
        }

        if(minusPrefixed){
            m[0][0] *= -1;
            M[0][0] *= -1;
        }

        for(int col = 1; col < lvls; col++){
            for(int row = 0; row < lvls - col; row++){
                int diagCol = col + row;

                int[] comparation = minAndMax(row, diagCol, M, m, exp);
                m[row][diagCol] = comparation[0];
                M[row][diagCol] = comparation[1];
            }
        }

        return M[0][lvls-1];
    }

    private static int[] minAndMax(int row, int col, int[][] M, int[][] m, String exp){
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for(int k = row; k < col; k++){
            char op = exp.charAt(k * 2 + 1);

            int a = countOperation(M[row][k], M[k+1][col], op);
            int b = countOperation(M[row][k], m[k+1][col], op);
            int c = countOperation(m[row][k], M[k+1][col], op);
            int d = countOperation(m[row][k], m[k+1][col], op);

            min = compareMany(false, min, a, b, c, d);
            max = compareMany(true, max, a, b, c, d);
        }

        return new int[]{min, max};
    }

    private static int countOperation(int a, int b, char op){
        if (op == '-')
            return a - b;
        if(op == '+')
            return a + b;
        if(op == '*')
            return a * b;

        return 0;
    }

    private static int compareMany(boolean flag, int... values){
        int val = values[0];

        for(int it : values){
            if(flag){
                if(it > val)
                    val = it;
            }else{
                if(it < val)
                    val = it;
            }
        }

        return val;
    }
}
