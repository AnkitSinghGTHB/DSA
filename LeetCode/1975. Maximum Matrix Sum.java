class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        long totalAbs = 0;
        int minAbs = Integer.MAX_VALUE;
        int countNegative = 0;
        boolean hasZero = false;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                totalAbs += Math.abs(val);
                if (val == 0) {
                    hasZero = true;
                } 
                else if (val < 0) {
                    countNegative++;
                }
                minAbs = Math.min(minAbs, Math.abs(val));
            }
        }
        
        if (hasZero || countNegative % 2 == 0) {
            return totalAbs;
        }
        else {
            return totalAbs - 2L * minAbs; //btw 2L * minAbs is long, no overflow
        }
    }
}
