package com.jitaida.tiancai.leetcode.midium;

/**
 * һ��������λ��һ�� m x n ��������Ͻ� ����ʼ������ͼ�б��Ϊ��Start�� ����
 *
 * ������ÿ��ֻ�����»��������ƶ�һ������������ͼ�ﵽ��������½ǣ�����ͼ�б��Ϊ��Finish������
 *
 * ���ڿ������������ϰ����ô�����Ͻǵ����½ǽ����ж�������ͬ��·����
 *
 *
 *
 * �����е��ϰ���Ϳ�λ�÷ֱ��� 1 �� 0 ����ʾ��
 *
 * ˵����m?�� n ��ֵ�������� 100��
 *
 * ʾ��?1:
 *
 * ����:
 * [
 * ? [0,0,0],
 * ? [0,1,0],
 * ? [0,0,0]
 * ]
 * ���: 2
 * ����:
 * 3x3 ��������м���һ���ϰ��
 * �����Ͻǵ����½�һ���� 2 ����ͬ��·����
 * 1. ���� -> ���� -> ���� -> ����
 * 2. ���� -> ���� -> ���� -> ����
 *
 */
public class UniquePathWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        return getNumPath(obstacleGrid, 0, 0);
    }

    public int getNumPath(int[][] obstacleGrid, int curx, int cury){
        int xlen = obstacleGrid.length;
        int ylen = obstacleGrid[0].length;
        int ret = 0;

        if (obstacleGrid[curx][cury] == 1){
            return 0;
        }

        if (curx == xlen - 1 && cury == ylen - 1){
            return 1;
        }

        if (cury + 1 < ylen){
            ret += getNumPath(obstacleGrid, curx, cury + 1);
        }

        if (curx + 1 < xlen){
            ret += getNumPath(obstacleGrid, curx + 1, cury);
        }

        return ret;
    }

    public static void main(String[] args){
        int[][] grid = new int[][]{
                {0,0}
        };

        UniquePathWithObstacles x = new UniquePathWithObstacles();
        System.out.println(x.uniquePathsWithObstacles(grid));
    }
}
