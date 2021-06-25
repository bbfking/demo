package com.pfxiong.algorithm;

import org.junit.Test;

/**
 * @author: pfXiong
 * @datetime: 2021/3/2 23:11
 * @description:
 */
public class DynamicProgramTest {
    /**
     * 爬楼梯
     *
     */
    public int climbStairsV2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * 爬楼梯
     *
     */
    @Test
    public void climbStairs() {
        System.out.println(climbStairsV2(10));
    }

    /**
     *
     * 爬楼梯 递归
     * @param n
     * @return
     */
    private int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }


    /**
     * 抢劫房间
     */
    @Test
    public void houseRobber() {
        System.out.println(houseRobber(5,2,6,3,1,7));
    }

    private int houseRobber(int... h) {
        int[] dp = new int[h.length];
        dp[0] = h[0];
        dp[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i < h.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + h[i]);
        }
        return dp[h.length - 1];
    }
}