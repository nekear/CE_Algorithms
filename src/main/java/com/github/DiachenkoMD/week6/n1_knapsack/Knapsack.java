package com.github.DiachenkoMD.week6.n1_knapsack;

import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bp = scanner.nextInt();
        int n = scanner.nextInt();

        int[] gold = new int[n];
        for(int i = 0; i < n; i++){
            gold[i] = scanner.nextInt();
        }

        System.out.println(grabGold(bp, gold));
    }

    public static int grabGold(int bp, int[] gold){
        int[][] fillingMatrix = new int[gold.length][bp];

        for(int row = 0; row < fillingMatrix.length; row++){
            for(int col = 0; col < bp; col++){
                int availableSpace = col+1;
                int acquiringGold = gold[row];

                int value = 0;
                if(availableSpace - acquiringGold < 0){
                    value = row-1 >= 0 ? fillingMatrix[row-1][col] : 0;
                }else{
                    value += Math.max(acquiringGold +
                            (row-1 >= 0 && col - acquiringGold >= 0 ? fillingMatrix[row-1][col-acquiringGold] : 0),

                            row-1 >= 0 ? fillingMatrix[row-1][col] : 0
                    );
                }

                fillingMatrix[row][col] = value;
            }
        }

        return bp == 0 || gold.length == 0 ? 0 : fillingMatrix[gold.length-1][bp-1];
    }
}
