class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            // update heights for current row
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }

            // sort the heights to find best combination
            int[] sorted = heights.clone();
            Arrays.sort(sorted);
            // iterate from largest to smallest
            for (int j = n - 1; j >= 0; j--) {
                int h = sorted[j];
                int width = n - j;   // number of columns with height >= h
                maxArea = Math.max(maxArea, h * width);
            }
        }
        return maxArea;
    }
}
