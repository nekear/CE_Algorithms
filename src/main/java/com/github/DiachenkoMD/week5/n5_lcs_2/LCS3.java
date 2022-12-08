package com.github.DiachenkoMD.week5.n5_lcs_2;

import java.util.Scanner;

public class LCS3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long[] seq1 = new long[n];
        for(int i = 0; i < n; i++){
            seq1[i] = scanner.nextLong();
        }

        int m = scanner.nextInt();
        long[] seq2 = new long[m];
        for(int i = 0; i < m; i++){
            seq2[i] = scanner.nextLong();
        }

        int k = scanner.nextInt();
        long[] seq3 = new long[k];
        for(int i = 0; i < k; i++){
            seq3[i] = scanner.nextLong();
        }

        System.out.println(getLCS(seq1, seq2, seq3));
    }

    public static long getLCS(long[] seq1, long[] seq2, long[] seq3){
        long[][][] matrix = new long[seq1.length+1][seq2.length+1][seq3.length+1];

        for(int i = 1; i <= seq1.length; i++){
            for(int j = 1; j <= seq2.length; j++){
                for(int m = 1; m <= seq3.length; m++){
                    if(seq1[i-1] == seq2[j-1] && seq1[i-1] == seq3[m-1]){
                        matrix[i][j][m] = matrix[i-1][j-1][m-1] + 1;
                    }else{
                        matrix[i][j][m] = Math.max(Math.max(matrix[i-1][j][m], matrix[i][j-1][m]), matrix[i][j][m-1]);
                    }
                }
            }
        }

        return matrix[seq1.length][seq2.length][seq3.length];
    }
}
