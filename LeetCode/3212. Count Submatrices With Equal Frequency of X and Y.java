class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        //top 10 bruh moments?
        int m = grid.length;
        int n = grid[0].length;
        int[][] X = new int[m][n];
        int[][] Y = new int[m][n];
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int xAdd = (grid[i][j] == 'X') ? 1 : 0;
                int yAdd = (grid[i][j] == 'Y') ? 1 : 0;
                
                int xSum = xAdd;
                int ySum = yAdd;
                if (i > 0) {
                    xSum += X[i-1][j];
                    ySum += Y[i-1][j];
                }
                if (j > 0) {
                    xSum += X[i][j-1];
                    ySum += Y[i][j-1];
                }
                if (i > 0 && j > 0) {
                    xSum -= X[i-1][j-1];
                    ySum -= Y[i-1][j-1];
                }
                X[i][j] = xSum;
                Y[i][j] = ySum;
                
                if (xSum == ySum && xSum > 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
