package com.jitaida.tiancai.leetcode.midium;

import java.awt.event.AdjustmentListener;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 给定一个?n x n?矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 * <p>
 * ?
 * <p>
 * 示例：
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * 返回 13。
 * ?
 * <p>
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2?。
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
