package com.github.DiachenkoMD.week5.n3_edit_distance;

import java.util.Arrays;
import java.util.Scanner;

public class EditDistance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word1 = scanner.nextLine();
        String word2 = scanner.nextLine();

        System.out.println(getED(word1, word2));
    }

    // If letters the same -> take previous diagonal value
    // If letters different -> take minimum of prev diag, top and left, than add 1
    // Answer is the last number on main diagonal
    private static int getED(String word1, String word2){
        int[][] dis = new int[word2.length()+1][word1.length()+1];
        dis[0][0] = 0;

        for(int i = 1; i < word1.length(); i++){
            dis[0][i] = i;
        }

        for(int i = 1; i < word2.length(); i++){
            dis[i][0] = i;
        }

        for(int w2Letter = 1; w2Letter < dis.length; w2Letter++){
            for(int w1Letter = 1; w1Letter < dis[0].length; w1Letter++){
                if(word1.charAt(w1Letter-1) == word2.charAt(w2Letter-1)){
                    dis[w2Letter][w1Letter] = dis[w2Letter-1][w1Letter-1];
                }else{
                    dis[w2Letter][w1Letter] =
                            Math.min(
                                    dis[w2Letter][w1Letter-1],
                                    Math.min(
                                            dis[w2Letter-1][w1Letter],
                                            dis[w2Letter-1][w1Letter-1]
                                    )
                            ) + 1;
                }
            }
        }

        return dis[dis.length-1][dis[0].length-1];
    }
}
