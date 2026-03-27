class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int shift = k % n; // only need the effective shift modulo n
        
        for (int i = 0; i < m; i++) {
            int[] row = mat[i];
            if (i % 2 == 0) {
                // Even rows: left shift by `shift`
                for (int j = 0; j < n; j++) {
                    if (row[j] != row[(j + shift) % n]) {
                        return false;
                    }
                }
            } else {
                // Odd rows: right shift by `shift`
                for (int j = 0; j < n; j++) {
                    if (row[j] != row[(j - shift + n) % n]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
