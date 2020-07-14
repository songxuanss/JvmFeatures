package com.jitaida.tiancai.leetcode.midium;

import java.awt.event.AdjustmentListener;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * ����һ��?n x n?��������ÿ�к�ÿ��Ԫ�ؾ������������ҵ������е� k С��Ԫ�ء�
 * ��ע�⣬���������ĵ� k СԪ�أ������ǵ� k ����ͬ��Ԫ�ء�
 * <p>
 * ?
 * <p>
 * ʾ����
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * ���� 13��
 * ?
 * <p>
 * ��ʾ��
 * ����Լ��� k ��ֵ��Զ����Ч�ģ�1 �� k �� n2?��
 */

public class FindKthSmallestInMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n-1][n-1];
        int mid;
        while(right > left){
            mid = left + (right- left)/2;

            if (!checkLessThanK(matrix, mid, k)){
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean checkLessThanK(int[][] matrix, int mid, int k) {
        int n = matrix.length;
        int i = n - 1;
        int j = 0;
        int num = 0;

        while (i >= 0 && j < n){
            if (matrix[i][j] <= mid ){
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }

        if (num >= k ) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };

        FindKthSmallestInMatrix m = new FindKthSmallestInMatrix();
        System.out.println(m.kthSmallest(matrix, 8));
    }
}
