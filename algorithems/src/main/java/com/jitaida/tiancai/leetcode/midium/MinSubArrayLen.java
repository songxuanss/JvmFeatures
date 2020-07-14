package com.jitaida.tiancai.leetcode.midium;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 给定一个含有?n?个正整数的数组和一个正整数?s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例:?
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组?[4,3]?是该条件下的长度最小的连续子数组。
 * 进阶:
 *
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试?O(n log n) 时间复杂度的解法。
 *
 */
public class MinSubArrayLen {
    public int solution(int[] array, int s){
        int bestLen = Integer.MAX_VALUE;
        int tmp;
        int start = 0;
        int end = 0;
        for( ; end < array.length; end++){
            tmp = checkBest(start, end, bestLen, array, s);

            if (tmp > 0 && tmp < bestLen){
                bestLen = tmp;
                start = end - tmp;
            }
        }

        return (bestLen == Integer.MAX_VALUE)? 0: bestLen ;
    }

    public int checkBest(int start, int end, int bestLen, int[] array, int s){
        int loop = (end - start);
        int len = 0;
        int sum = 0;
        if (bestLen < loop){
            loop = bestLen;
        }

        if  (start == end && array[start] >= s){
            return 1;
        }

        for (int i = 0; i <= loop; i++){
            sum += array[end - i];
            len++;
            if (sum >= s) {
                return len;
            }
        }

        return 0;
    }

    public static void main(String[] args){
        MinSubArrayLen msal = new MinSubArrayLen();
        System.out.println(msal.solution(new int[]{5,1,3,5,10,7,4,9,2,8}, 15));
    }
}
