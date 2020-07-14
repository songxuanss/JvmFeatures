package com.jitaida.tiancai.leetcode.midium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例?2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
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
