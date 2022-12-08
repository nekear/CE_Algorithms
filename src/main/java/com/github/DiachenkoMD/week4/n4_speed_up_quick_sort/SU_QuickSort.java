package com.github.DiachenkoMD.week4.n4_speed_up_quick_sort;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Three-way quicksort
 * Суть в том, что мы вычисляем области как и в обычном квик-сорте, но потом левую область отправляем в слайд метод,
 * который просто все элементы, что похожи на крайний правый сдвигает вправо.
 * Таким образом формирует вот этот 3-й массив, содержащий только одинаковые элементы.
 *
 * Сам же принцип формирование областей в квик-сорте в том, что есть 2 указателя j (для меньшего) и i (для большего).
 * Первым делом, выбрав случайно (а можно и не случайно) пивот, надо поменять его местами с крайним элементом области.
 * Потом двигаясь от (крайнего элемента + 1) проверяем, если этот элемент меньше либо равен пивоту,
 * то увеличиваем j, если нет, то делаем свайп с j+1 (залазим на i, но это не страшно, т.к. по факту тот элемент, что нам не нужен отлетит в i, что сыграем нам на руку)
 * и увеличиваем j и i. В конце концов делаем свайп пивота с j, поместив пивот в центр (по факту) изначального массива, на котором делали партишн.
 */
public class SU_QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        long[] values = new long[n];

        for(int i = 0; i < n; i++){
            values[i] = scanner.nextInt();
        }

        quickSort(values, 0, values.length - 1);
        System.out.println(Arrays.stream(values).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }



    public static int[] partition(long[] values, int start, int end, int pivotIndex){
        int lowerBound = start, higherBound = start+1;

        long pivot = values[pivotIndex];

        swap(start, pivotIndex, values);

        for(int k = start+1; k <= end; k++){
            if(values[k] <= pivot){
                swap(lowerBound+1, k, values);
                lowerBound++;
            }else{
                higherBound++;
            }
        }

        swap(lowerBound, start, values);

        int lowPartitionEnd = slide(values, start, lowerBound);
        int highPartitionStart = lowerBound+1;

        return new int[]{lowPartitionEnd, highPartitionStart};
    }

    public static int slide(long values[], int start, int end){
        if(end - start <= 1)
            return end - 1;

        long boundedValue = values[end];

        int swappingIndex = end - 1;
        for(int i = end - 1; i >= start; i--){
            if(values[i] == boundedValue){
                swap(swappingIndex, i, values);
                swappingIndex--;
            }
        }

        return swappingIndex+1;
    }

    public static void quickSort(long[] values, int start, int end){
        if(end - start <= 0)
            return;

        if(end - start == 1 && values[end] < values[start]) {
            swap(start, end, values);
            return;
        }

        int[] partitions = partition(values, start, end, start + ((end - start) / 2));

        quickSort(values, start, partitions[0] - 1);
        quickSort(values, partitions[1], end);
    }

    private static void swap(int i, int j, long[] array){
        long tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }

    /**
     * BELOW ARE METHOD FOR EDUCATIONAL PURPOSES
     * @param values
     */

    public static void dutchNational(long[] values){
        int pivotIndex = values.length / 2;
        long pivot = values[pivotIndex];

        int swappingIndex = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] < pivot) {
                swap(swappingIndex, i, values);
                swappingIndex++;
            }
        }

        swappingIndex = values.length-1;
        for(int i = values.length-1; i >= pivotIndex; i--){
            if(values[i] > pivot){
                swap(swappingIndex, i, values);
                swappingIndex--;
            }
        }
    }

    public static void dutchNationalMod(long[] values){
        int pivotIndex = values.length / 2;
        long pivot = values[pivotIndex];

        int swappingIndex = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] < pivot) {
                swap(swappingIndex, i, values);
                swappingIndex++;
            }
        }

        swappingIndex = values.length-1;
        for(int i = values.length-1; i >= pivotIndex; i--){
            if(values[i] > pivot){
                swap(swappingIndex, i, values);
                swappingIndex--;
            }
        }
    }



    /**
     * NOT WORKING
     * @param values
     * @param start
     * @param end
     * @return
     */
    public static long[] quickSortx(long[] values, int start, int end){
        if((end - start) < 1)
            return values;

        int pivotIndex = end;
        long pivot = values[pivotIndex];

        int i = start - 1, j = start + 1;

        while(j != pivotIndex){
            if(values[j] < pivot){
                i++;
                swap(i, j, values);
            }

            j++;
        }

        swap(i+1, j, values);

        quickSort(values, start, i);
        quickSort(values, i+2, end);

        return values;
    }

}
