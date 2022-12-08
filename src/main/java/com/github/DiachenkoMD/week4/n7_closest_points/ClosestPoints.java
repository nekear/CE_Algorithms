package com.github.DiachenkoMD.week4.n7_closest_points;

import java.util.*;

public class ClosestPoints {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        long[][] points = new long[n][2];

        for(int i = 0; i < n; i++){
            points[i] = new long[]{scanner.nextLong(), scanner.nextLong()};
        }

        System.out.printf(Locale.ROOT, "%.6f", getClosestPointsDistance(points));
    }

    public static double getClosestPointsDistance(long[][] points){
        Arrays.sort(points, ((o1, o2) -> {
            int segmentEndsComparison = Long.compare(o1[0], o2[0]);

            if(segmentEndsComparison == 0){
                return Long.compare(o1[1], o2[1]);
            }else{
                return segmentEndsComparison;
            }
        }));

        return getCPDRecursive(points, 0, points.length-1);
    }

    private static double getCPDRecursive(long[][] points, int start, int end){
        if(end - start + 1 == 2)
            return dist(points[start], points[end]);

        if(end - start + 1 == 3)
            return Math.min(
                    dist(points[start], points[end]),
                    Math.min(
                        dist(points[start+1], points[end]),
                        dist(points[start], points[start+1])
                    )
            );

        // Dividing and conquering
        int mid = (start + end) / 2;

        double dl = getCPDRecursive(points, start, mid);
        double dr = getCPDRecursive(points, mid + 1, end);

        double d = Math.min(dl, dr);

        // Collecting all points which are in range [mid.x-d, mid.x+d]
        List<long[]> points_in_band = new LinkedList<>();

        for(int i = start; i <= end; i++){
            long[] current = points[i];
            if(points[mid][0] - d <= current[0] && points[mid][0] + d >= current[0])
                points_in_band.add(current);
        }

        // Finding the closest distance between dots in range
        for(int i = 0; i < points_in_band.size(); i++){
            for(int j = i+1; j < Math.min(i+6, points_in_band.size()); j++){
                d = Math.min(d, dist(points_in_band.get(i), points_in_band.get(j)));
            }
        }

        return d;
    }

    private static double dist(long[] point1, long[] point2){
        return Math.sqrt(Math.pow(point1[0] - point2[0], 2) + Math.pow(point1[1] - point2[1], 2));
    }
}
