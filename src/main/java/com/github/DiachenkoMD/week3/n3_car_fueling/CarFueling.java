package com.github.DiachenkoMD.week3.n3_car_fueling;

import java.util.Scanner;

public class CarFueling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int d = scanner.nextInt();
        int m = scanner.nextInt();

        int n = scanner.nextInt();

        int[] stops = new int[n+2];

        for(int i = 1; i <= n; i++){
            stops[i] = scanner.nextInt();
        }

        stops[0] = 0;
        stops[n+1] = d;

        System.out.println(countRefills(0, m, stops));
    }


    public static int countRefills(int currentStop, int m, int[] stops){
        int ongoingStop = currentStop;

        while(ongoingStop < stops.length && stops[ongoingStop] - stops[currentStop] <= m){
            ++ongoingStop;
        }

        ongoingStop--;

        if(ongoingStop != stops.length-1 && ongoingStop == currentStop)
            return -1;

        if(ongoingStop == stops.length-1 && ongoingStop != currentStop)
            return 0;

        int refills = countRefills(ongoingStop, m, stops);
        return refills == -1 ? -1 : refills + 1;
    }
}
