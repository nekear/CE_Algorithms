package com.github.DiachenkoMD.week3.n5_collecting_signatures;

import java.util.*;
import java.util.stream.Collectors;

// basically searching for the nearest segment`s end,
// placing point, invalidating all segments, which are intersected
// by line on that point, and repeating the process from last point`s position
public class CollectingSignatures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[][] segments = new int[n][2];

        for(int i = 0; i < n; i++){
            segments[i] = new int[]{scanner.nextInt(), scanner.nextInt()};
        }

        List<Integer> signatures = collectSignatures(segments);

        System.out.println(signatures.size());
        System.out.println(signatures.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }

    public static List<Integer> collectSignatures(int[][] segments){
        List<Integer> points = new ArrayList<>();

        Arrays.sort(segments, Comparator.comparingInt(o -> o[1]));

        int n = segments.length;
        int lastEl = 0;
        while(n > 0){
            int lowestEnd = lastEl;
            for(int i = lastEl; i < segments.length; i++){
                if(segments[i][1] != -1){
                    lowestEnd = i;
                    break;
                }
            }

            int point = segments[lowestEnd][1];

            for(int i = lowestEnd; i < segments.length; i++){
                if(segments[i][1] >= point && segments[i][0] <= point) {
                    segments[i][1] = -1;
                    --n;
                }
            }

            points.add(point);

            lastEl = lowestEnd;
        }

        return points;
    }
}
