package com.github.DiachenkoMD.week4.n6_org_a_lottery;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrgALottery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        long[][] segments = new long[n][2];

        for(int i = 0; i < n; i++){
            segments[i] = new long[]{scanner.nextLong(), scanner.nextLong()};
        }

        long[] dots = new long[m];

        for(int i = 0; i < m; i++){
            dots[i] = scanner.nextLong();
        }

        System.out.println(Arrays.stream(getCollisions(segments, dots)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int[] getCollisions(long[][] segments, long[] dots){
        Arrays.sort(segments, ((o1, o2) -> {
            int segmentEndsComparison = Long.compare(o1[1], o2[1]);

            if(segmentEndsComparison == 0){
                return Long.compare(o1[0], o2[0]);
            }else{
                return segmentEndsComparison;
            }
        }));

        int[] collisions = new int[dots.length];

        for(int i = 0; i < dots.length; i++){
            // Using something similar to binary search
            collisions[i] = getCollisionForDotBinary(segments, dots[i]);
        }

        return collisions;
    }

    public static int getCollisionForDot(long[][] segments, long dot){
        int collisions = 0;

        int marker = 0;
        while(marker < segments.length && segments[marker][1] <= dot){
            marker += 100;
        }

        if(marker >= segments.length){
            marker = segments.length-1;
        }

        if(segments[marker][1] <= dot)
            return 0;

        int leftMovementMarker = marker;
        while(leftMovementMarker >= 0 && dot >= segments[leftMovementMarker][0] && dot <= segments[leftMovementMarker][1]){
            collisions++;
            leftMovementMarker--;
        }

        int rightMovementMarker = marker+1;
        while(rightMovementMarker < segments.length && dot >= segments[rightMovementMarker][0] && dot <= segments[rightMovementMarker][1]){
            collisions++;
            rightMovementMarker++;
        }

        return collisions;
    }

    public static int getCollisionForDotBinary(long[][] segments, long dot){
        if(dot > segments[segments.length-1][1] || dot < segments[0][0])
            return 0;

        int mid = segments.length / 2;
        while(mid >= 0 && mid < segments.length && segments[mid][1] < dot){
            mid = (segments.length + mid) / 2;
        }

        if(segments[mid][1] < dot)
            return 0;

        int collisions = 0;

        int leftMovementMarker = mid-1;
        while(leftMovementMarker >= 0 && dot >= segments[leftMovementMarker][0] && dot <= segments[leftMovementMarker][1]){
            collisions++;
            leftMovementMarker--;
        }

        int rightMovementMarker = mid;
        while(rightMovementMarker < segments.length && dot >= segments[rightMovementMarker][0] && dot <= segments[rightMovementMarker][1]){
            collisions++;
            rightMovementMarker++;
        }

        return collisions;
    }
}
