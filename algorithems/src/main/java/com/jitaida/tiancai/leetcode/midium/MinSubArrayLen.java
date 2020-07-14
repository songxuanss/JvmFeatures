package com.jitaida.tiancai.leetcode.midium;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * ����һ������?n?���������������һ��������?s ���ҳ���������������� �� s �ĳ�����С�����������飬�������䳤�ȡ���������ڷ������������������飬���� 0��
 *
 * ʾ��:?
 *
 * ����: s = 7, nums = [2,3,1,2,4,3]
 * ���: 2
 * ����: ������?[4,3]?�Ǹ������µĳ�����С�����������顣
 * ����:
 *
 * ������Ѿ������O(n) ʱ�临�ӶȵĽⷨ, �볢��?O(n log n) ʱ�临�ӶȵĽⷨ��
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
