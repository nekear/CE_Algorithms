package com.github.DiachenkoMD.week3.n2_max_value_loot;

import java.util.Locale;
import java.util.Scanner;

public class MaxValueLoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int W = scanner.nextInt();

        double[][] relations = new double[n][3];

        for(int i = 0; i < n; i++){
            relations[i] = new double[]{scanner.nextInt(), scanner.nextInt(), 0};
            relations[i][2] = relations[i][0] / relations[i][1];
        }


        System.out.printf(Locale.ROOT, "%.4f\n", fillBackpack(W, relations));
    }

    public static double fillBackpack(int backpackSize, double[][] relations){
        int maxCompoundIndex = 0;
        for(int i = 1; i < relations.length; i++){
            if(relations[i][2] > relations[maxCompoundIndex][2] && relations[i][1] != 0)
                maxCompoundIndex = i;
        }

        int min = Math.min(backpackSize, (int) relations[maxCompoundIndex][1]);

        if(min == 0)
            return 0;

        relations[maxCompoundIndex][1] -= min;

        return min * relations[maxCompoundIndex][2] + fillBackpack(backpackSize - min, relations);
    }
}
