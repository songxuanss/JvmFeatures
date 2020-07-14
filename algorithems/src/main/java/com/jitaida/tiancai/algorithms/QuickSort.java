package com.jitaida.tiancai.algorithms;

public class QuickSort {
        public void quickSort(int[] a, int start, int end){
            int i = start;
            int j = end;
            int base = a[start];

            while (i != j){
                while (a[i] < base && i < j)
                {
                    i ++;
                }

                while(a[j] > base && i < j){
                    j --;
                }

                if (i < j){
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }

            a[start] = a[i];
            a[i] = base;

            quickSort(a, start, i-1);
            quickSort(a,i+1, end);
        }

        public static void main(String[] args){
            QuickSort q = new QuickSort();
            int [] a = {1,2,99,80};
            q.quickSort(a,0,8);

            System.out.println(a);
        }
}
