package com.jitaida.tiancai.leetcode.midium;

public class UniquePathWithObstaclesDP {
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        int xlen = obstacleGrid.length;
        int ylen = obstacleGrid[0].length;
        int[][] dp = new int[xlen][ylen];
        dp[0][0] = 0;

        for (int i = 0; i < xlen; i++){
            for (int j = 0; j< ylen; j++){
                return 0;
            }
        }

        return -1;
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

}
