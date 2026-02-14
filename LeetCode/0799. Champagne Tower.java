class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        //dp[r][c] = amount of champagne that has flowed into glass (r, c)
        double[][] dp = new double[query_row + 2][query_row + 2];
        dp[0][0] = poured;

        for (int r = 0; r <= query_row; r++) {
            for (int c = 0; c <= r; c++) {
                if (dp[r][c] > 1) {
                    double overflow = dp[r][c] - 1;
                    double half = overflow / 2.0;
                    dp[r + 1][c] += half;
                    dp[r + 1][c + 1] += half;
                }
            }
        }
        //the glass cannot hold more than 1 cup
        return Math.min(dp[query_row][query_glass], 1.0);
    }
}
