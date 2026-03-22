class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        //check 0 deg rotation
        if (isEqual(mat, target)) return true;
        //check 90 deg rotation
        if (isEqual(rotate90(mat), target)) return true;
        //check 180 deg rotation
        if (isEqual(rotate180(mat), target)) return true;
        //check 270 deg rotation (or rotate 90 three times)
        if (isEqual(rotate270(mat), target)) return true;
        return false;
    }

    private boolean isEqual(int[][] a, int[][] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }

    private int[][] rotate90(int[][] mat) {
        int n = mat.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[j][n-1-i] = mat[i][j];
            }
        }
        return res;
    }

    private int[][] rotate180(int[][] mat) {
        int n = mat.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[n-1-i][n-1-j] = mat[i][j];
            }
        }
        return res;
    }

    private int[][] rotate270(int[][] mat) {
        int n = mat.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[n-1-j][i] = mat[i][j];
            }
        }
        return res;
    }
}
