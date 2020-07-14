package com.jitaida.tiancai.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * ����һ���������� nums?��һ��Ŀ��ֵ target�������ڸ��������ҳ���ΪĿ��ֵ����?����?���������������ǵ������±ꡣ
     *
     * ����Լ���ÿ������ֻ���Ӧһ���𰸡����ǣ�������ͬһ��Ԫ�ز���ʹ�����顣
     *
     * ?
     *
     * ʾ��:
     *
     * ���� nums = [2, 7, 11, 15], target = 9
     *
     * ��Ϊ nums[0] + nums[1] = 2 + 7 = 9
     * ���Է��� [0, 1]
     *
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/two-sum
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     */

    public static int[] twoSum(int[] nums, int target) {
        int startPtr;
        int endPtr;
        for (startPtr=0; startPtr < nums.length; startPtr++){

            for (endPtr=startPtr+1; endPtr<nums.length; endPtr++){

                if (nums[startPtr] + nums[endPtr] == target){
                    return new int[]{startPtr, endPtr};
                }
            }
        }

        return new int[0];
    }

    public static int[] twoSum2(int[] nums, int target) {
        int startPtr;
        Map<Integer, Integer> passedValue = new HashMap<Integer, Integer>();
        for (startPtr=0; startPtr < nums.length; startPtr++){
            if (passedValue.containsKey(target-nums[startPtr])){
                return new int[]{passedValue.get(target-nums[startPtr]), startPtr};
            }

            passedValue.put(nums[startPtr], startPtr);
        }

        return new int[0];
    }

    public static void main(String[] args){
        int[] x = TwoSum.twoSum(new int[]{-3,4,3,90}, 0);

        for (int i : x){
            System.out.println(i);
        }
    }
}
