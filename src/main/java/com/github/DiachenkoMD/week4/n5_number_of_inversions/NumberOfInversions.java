package com.github.DiachenkoMD.week4.n5_number_of_inversions;

import java.util.*;

public class NumberOfInversions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        Long[] values = new Long[n];

        for(int i = 0; i < n; i++){
            values[i] = scanner.nextLong();
        }

        int inversionsNumber = mergeSort(values, Long::compareTo);
        System.out.println(inversionsNumber);
    }

    public static <T> int mergeSort(T[] values, Comparator<T> comparator){
        int checksCounter = 0;

        if(values.length <= 1)
            return checksCounter;

        int middle = values.length / 2;
        T[] leftArray = (T[]) new Object[middle];
        T[] rightArray = (T[]) new Object[values.length - middle];

        int lPointer = 0;
        int rPointer = 0;

        for(; lPointer < values.length; lPointer++){
            if(lPointer < middle){
                leftArray[lPointer] = values[lPointer];
            }else{
                rightArray[rPointer] = values[lPointer];
                rPointer++;
            }
        }

        checksCounter += mergeSort(leftArray, comparator);
        checksCounter += mergeSort(rightArray, comparator);
        return checksCounter + merge(leftArray, rightArray, values, comparator);
    }

    /**
     * Концепция в том, что у нас по факту существуют перестановки, если значения записываются в финальный массив (array) из правого массива,
     * то есть изначально они стояли позже, чем будут стоять в конечном итоге.
     * + надо учесть, что все элементы из левого, что не успели найти своё место (второй цикл в функции)
     * тоже по факту изменили свою позицию, следовательно увеличиваем число перестановок.
     * В этом алгоритме есть частный случай, когда в каждом из массивов по 1 эл. и в правом меньше, чем в левом.
     * Тогда надо вернуть 1 перестановку вне выполнения алгоритм, т.к. алгоритм даст 2.
     * @param leftArray
     * @param rightArray
     * @param array
     * @param comparator
     * @return
     * @param <T>
     */
    private static <T> int merge(T[] leftArray, T[] rightArray, T[] array, Comparator<T> comparator){
        if(leftArray.length == 1 && rightArray.length == 1){
            if(comparator.compare(leftArray[0],rightArray[0]) > 0){
                array[0] = rightArray[0];
                array[1] = leftArray[0];
                return 1;
            }else{
                array[0] = leftArray[0];
                array[1] = rightArray[0];
                return 0;
            }
        }

        int lPointer = 0, rPointer = 0, arrIndex = 0;

        int inversions = 0;

        while(lPointer < leftArray.length && rPointer < rightArray.length){
            if(comparator.compare(leftArray[lPointer], rightArray[rPointer]) <= 0){
                array[arrIndex] = leftArray[lPointer];
                lPointer++;
            }else{
                array[arrIndex] = rightArray[rPointer];
                rPointer++;

                inversions += leftArray.length - lPointer;
            }
            arrIndex++;
        }

        while(lPointer < leftArray.length){
            array[arrIndex] = leftArray[lPointer];
            arrIndex++;
            lPointer++;
        }

        while(rPointer < rightArray.length){
            array[arrIndex] = rightArray[rPointer];
            arrIndex++;
            rPointer++;
        }

        return inversions;
    }
}
