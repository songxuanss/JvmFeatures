package com.jitaida.tiancai.leetcode.midium;

import java.util.Arrays;

/**
 * ����һ�� n?��?n �Ķ�ά�����ʾһ��ͼ��
 *
 * ��ͼ��˳ʱ����ת 90 �ȡ�
 *
 * ˵����
 *
 * �������ԭ����תͼ������ζ������Ҫֱ���޸�����Ķ�ά�����벻Ҫʹ����һ����������תͼ��
 *
 * ʾ�� 1:
 *
 * ���� matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * ԭ����ת�������ʹ���Ϊ:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * ʾ�� 2:
 *
 * ���� matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * ԭ����ת�������ʹ���Ϊ:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 *  ͨ����ʽ
 *  x` = y
 *  y` = n - x
 */
public class RotateArray {
    public void rotate(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;

        if (m != n){
            return;
        }

        int tmp;
        int x;
        int y;
        int all  = n;

        /*
               3��ѭ����
               1. ÿһȦ��һ��ѭ��
               2. ��ÿһ��Ȧ��ÿһ���������滻
               3. ��ÿһ������Ķ��������ĸ���λ���滻

                north
               1, 2, 3
          east 4, 5, 6 west
               7, 8, 9
                south
         */

        while( n > all/2 ) {
            for (int i = 0; i < n -1; i++) {
                x = n - 1;

                if (x <= all/2){
                    break;
                }

                y = n - 1 - i;
                tmp = matrix[x][y];
                int count = 0;
                while (count < 4) {
                    int newX = y;
                    int newY = n - 1 - x;
                    tmp = setTmp(tmp, matrix, newX, newY);
                    x = newX;
                    y = newY;
                    count++;
                }
            }
            n--;
        }
    }

    public int setTmp(int tmp, int[][] matrix, int x, int y){
        int curTmp = matrix[x][y];
        matrix[x][y] = tmp;
        return curTmp;
    }

    public static void main(String[] args){
        RotateArray ra = new RotateArray();
        int[][] nn = new int[][] {
                {1,2,3,4},
                {3,4,5,6},
                {4,5,6,7},
                {5,5,6,7},
        };

        ra.rotate(nn);

        for (int i = 0; i < nn.length; i ++){
            for (int j = 0; j < nn.length; j++){
                System.out.println(i+":"+j+":" +
                        nn[i][j] + "");
            }
        }
    }
}
