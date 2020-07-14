package com.jitaida.tiancai.leetcode.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给两个整数数组?A?和?B?，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 *
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * 通过次数22,805提交次数43,309
 */
public class FindCommonLength {
    public int findLength(int[] A, int[] B){
        Map<Integer, List<Integer>> index = new HashMap<>();
        List<Integer> tmp;
        for (int i = 0; i < B.length; i++) {
            tmp = index.getOrDefault(B[i], null);
            if (tmp == null) {
                tmp = new ArrayList<>();
                tmp.add(i);
                index.put(B[i], tmp);
            } else {
                tmp.add(i);
            }
        }

        int i = 0;
        int maxLength = 0;
        int tmp1Length = 0;
        int tmpLength = 0;

        while (i < A.length){
            tmp =  index.getOrDefault(A[i], null);
            if (tmp == null){
                i++;
                continue;
            }

            int ak;
            int bk;
            for (int j = 0; j < tmp.size(); j++) {
                tmp1Length = 0;
                bk = tmp.get(j);
                ak = i;

                while(ak < A.length && bk < B.length && A[ak] == B[bk]){
                   tmp1Length++;
                   ak++;
                   bk++;
                }

                if (tmp1Length > tmpLength){
                    tmpLength = tmp1Length;
                }
            }

            if (tmpLength > maxLength){
                maxLength = tmpLength;
            }
            i = i + 1;
        }

        return maxLength;
    }

    /**
     * 动态规划解法
     */
    public int findLengthDP(int[] A, int[] B){
        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args){
        FindCommonLength fcl = new FindCommonLength();
        int[] a = new int[]{0,0,0,0,0,0,1,0,0,0};
        int[] b = new int[]{0,0,0,0,0,0,0,1,0,0};
        System.out.println(fcl.findLengthDP(a, b));
    }
}
