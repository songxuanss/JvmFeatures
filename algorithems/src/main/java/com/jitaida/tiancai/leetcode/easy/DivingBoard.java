package com.jitaida.tiancai.leetcode.easy;

/**
 * ������ʹ��һ��ľ�彨����ˮ�塣���������͵�ľ�壬���г��Ƚ϶̵�ľ�峤��Ϊshorter�����Ƚϳ���ľ�峤��Ϊlonger�����������ʹ��k��ľ�塣��дһ��������������ˮ�����п��ܵĳ��ȡ�
 *
 * ���صĳ�����Ҫ��С�������С�
 *
 * ʾ����
 *
 * ���룺
 * shorter = 1
 * longer = 2
 * k = 3
 * ����� {3,4,5,6}
 * ��ʾ��
 *
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 */
public class DivingBoard {
    public int[] divingBoard(int shorter, int longer, int k){
        if(k == 0){
            return new int[0];
        }

        if (shorter == longer){
            return new int[]{shorter * k};
        }

        int ret[] = new int[k+1];

        ret[0] = shorter * k;

        for (int i = 1;  i <= k; i++){
            ret[i] = ret[i-1] - shorter + longer;
        }

        return ret;
    }
}
