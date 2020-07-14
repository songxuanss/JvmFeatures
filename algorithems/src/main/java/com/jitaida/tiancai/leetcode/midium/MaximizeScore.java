package com.jitaida.tiancai.leetcode.midium;

/**
 * 给定正整数数组?A，A[i]?表示第 i 个观光景点的评分，并且两个景点?i 和?j?之间的距离为?j - i。
 * <p>
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i?- j）：景点的评分之和减去它们两者之间的距离。
 * <p>
 * 返回一对观光景点能取得的最高分。
 * <p>
 * ?
 * <p>
 * 示例：
 * <p>
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 * ?
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 50000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximizeScore {

    /**
     * 最佳解答：A[i] + A[j] + i - j 分成 A[i] + i, A[j] - j 两部分，然后找A[i]+i 或者 A[j] -j的最大值
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
