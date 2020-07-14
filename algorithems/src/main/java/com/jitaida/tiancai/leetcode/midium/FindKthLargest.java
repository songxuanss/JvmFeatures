package com.jitaida.tiancai.leetcode.midium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * ��δ������������ҵ��� k ������Ԫ�ء���ע�⣬����Ҫ�ҵ������������ĵ� k ������Ԫ�أ������ǵ� k ����ͬ��Ԫ�ء�
 *
 * ʾ�� 1:
 *
 * ����: [3,2,1,5,6,4] �� k = 2
 * ���: 5
 * ʾ��?2:
 *
 * ����: [3,2,3,1,2,4,5,5,6] �� k = 4
 * ���: 4
 * ˵��:
 *
 * ����Լ��� k ������Ч�ģ��� 1 �� k �� ����ĳ��ȡ�
 *
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k){
        // heaplify the array
        for (int i = nums.length/2; i >= 0; i--){
            buildHeap(nums, i, nums.length);
        }

        for (int i = nums.length - 1; i > nums.length - k; i-- ){
            // put the largest to the end
            swap(nums, 0, i);
            buildHeap(nums, 0, i);
        }

        return nums[0];
    }

    public void buildHeap(int[] nums, int root, int size){
        if (root >= size){
            return;
        }

        int left = 2 * root + 1;
        int right = 2 * root + 2;

        int maxNode = root;

        if (left < size && nums[left] > nums[maxNode]) maxNode = left;
        if (right < size && nums[right] > nums[maxNode]) maxNode = right;

        if (maxNode != root){
            swap(nums, root, maxNode);
            buildHeap(nums, maxNode, size);
        }
    }

    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args){
        FindKthLargest f = new FindKthLargest();

        int[] nums = new int[]{1,2,3,4,5,6};

        System.out.println(f.findKthLargest(nums, 1));

        for (int each : nums){
            System.out.println(each + " ");
        }

    }
}
