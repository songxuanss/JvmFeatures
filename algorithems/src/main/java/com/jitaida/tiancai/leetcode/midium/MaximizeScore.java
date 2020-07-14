package com.jitaida.tiancai.leetcode.midium;

/**
 * ��������������?A��A[i]?��ʾ�� i ���۹⾰������֣�������������?i ��?j?֮��ľ���Ϊ?j - i��
 * <p>
 * һ�Ծ��㣨i < j����ɵĹ۹���ϵĵ÷�Ϊ��A[i] + A[j] + i?- j�������������֮�ͼ�ȥ��������֮��ľ��롣
 * <p>
 * ����һ�Թ۹⾰����ȡ�õ���߷֡�
 * <p>
 * ?
 * <p>
 * ʾ����
 * <p>
 * ���룺[8,1,5,2,6]
 * �����11
 * ���ͣ�i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 * ?
 * <p>
 * ��ʾ��
 * <p>
 * 2 <= A.length <= 50000
 * <p>
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/best-sightseeing-pair
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 */
public class MaximizeScore {

    /**
     * ��ѽ��A[i] + A[j] + i - j �ֳ� A[i] + i, A[j] - j �����֣�Ȼ����A[i]+i ���� A[j] -j�����ֵ
     * @param A
     * @return
     */
    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0;
        int bestI = A[0] + 0;
        for (int j = 1; j < A.length; j++) {
            ans = Math.max( bestI + A[j] - j, ans);
            bestI = Math.max(bestI, A[j] + j);
        }

        return ans;
    }

    public static void main(String[] args) {
        MaximizeScore m = new MaximizeScore();
        System.out.println(m.maxScoreSightseeingPair(new int[]{8, 1, 5, 2, 6}));
    }
}
