class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        
        //build prefix sum matrix
        int[][] prefix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i - 1][j - 1] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
        
        //binary search on side length
        int left = 0, right = Math.min(m, n);
        int answer = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canFormSquare(mid, prefix, threshold)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
    
    private boolean canFormSquare(int k, int[][] prefix, int threshold) {
        if (k == 0) return true;
        int m = prefix.length - 1;
        int n = prefix[0].length - 1;
        for (int i = k; i <= m; i++) {
            for (int j = k; j <= n; j++) {
                int sum = prefix[i][j] - prefix[i - k][j] - prefix[i][j - k] + prefix[i - k][j - k];
                if (sum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}
