package com.jitaida.tiancai.leetcode.hard;

public class FindMediumSortArray {
    //¿‡À∆πÈ≤¢≈≈–Ú
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1Last = Integer.MAX_VALUE;
        int nums2Last = Integer.MAX_VALUE;
        int sortLast = -1;
        int sortSecLast = -1;

        int num1Index = 0;
        int num2Index = 0;

        int mediumIndex = (nums1.length + nums2.length) / 2;
        boolean isAve = (nums1.length + nums2.length) % 2 == 0;

        int size = 0;
        while (size <= mediumIndex) {
            if (num1Index < nums1.length) {
                nums1Last = nums1[num1Index];
            }

            if (num2Index < nums2.length) {
                nums2Last = nums2[num2Index];
            }

            if ((nums1Last <= nums2Last && num1Index < nums1.length) || num2Index > nums2.length-1) {
                num1Index++;
                sortSecLast = sortLast;
                sortLast = nums1Last;

            } else if ((nums2Last <= nums1Last && num2Index < nums2.length) || num1Index > nums1.length-1) {
                num2Index++;
                sortSecLast = sortLast;
                sortLast = nums2Last;
            }

            size++;
        }

        if (isAve) {
            return ((double) sortLast + (double) sortSecLast) / 2;
        }

        return sortLast;
    }

    public static void main(String[] args) {
        FindMediumSortArray fmsa = new FindMediumSortArray();
        System.out.println(fmsa.findMedianSortedArrays(new int[]{1,3}, new int[]{2,5}));
    }
}

