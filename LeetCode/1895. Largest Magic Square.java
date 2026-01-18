class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // prefix sums for rows
        long[][] rowPS = new long[m][n];
        for (int i = 0; i < m; i++) {
            rowPS[i][0] = grid[i][0];
            for (int j = 1; j < n; j++) {
                rowPS[i][j] = rowPS[i][j-1] + grid[i][j];
            }
        }
        
        // prefix sums for columns
        long[][] colPS = new long[m][n];
        for (int j = 0; j < n; j++) {
            colPS[0][j] = grid[0][j];
            for (int i = 1; i < m; i++) {
                colPS[i][j] = colPS[i-1][j] + grid[i][j];
            }
        }
        
        // diagonals (main and anti)
        int diagCount = m + n - 1;
        List<Integer>[] diag1Lists = new ArrayList[diagCount];
        List<Integer>[] diag2Lists = new ArrayList[diagCount];
        for (int i = 0; i < diagCount; i++) {
            diag1Lists[i] = new ArrayList<>();
            diag2Lists[i] = new ArrayList<>();
        }
        int[][] idx1 = new int[m][n]; // index in main diagonal list
        int[][] idx2 = new int[m][n]; // index in anti diagonal list
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int d1 = i - j + (n - 1);
                idx1[i][j] = diag1Lists[d1].size();
                diag1Lists[d1].add(grid[i][j]);
                
                int d2 = i + j;
                idx2[i][j] = diag2Lists[d2].size();
                diag2Lists[d2].add(grid[i][j]);
            }
        }
        
        // prefix sums for diagonals
        long[][] diag1Prefix = new long[diagCount][];
        long[][] diag2Prefix = new long[diagCount][];
        for (int d = 0; d < diagCount; d++) {
            int len = diag1Lists[d].size();
            diag1Prefix[d] = new long[len + 1];
            for (int t = 0; t < len; t++) {
                diag1Prefix[d][t+1] = diag1Prefix[d][t] + diag1Lists[d].get(t);
            }
            len = diag2Lists[d].size();
            diag2Prefix[d] = new long[len + 1];
            for (int t = 0; t < len; t++) {
                diag2Prefix[d][t+1] = diag2Prefix[d][t] + diag2Lists[d].get(t);
            }
        }
        
        int maxK = 1;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int maxPossible = Math.min(m - r, n - c);
                if (maxPossible <= maxK) continue;
                // try k from large to small
                for (int k = maxPossible; k > maxK; k--) {
                    long target = getRowSum(rowPS, r, c, c + k - 1);
                    boolean ok = true;
                    // check rows
                    for (int i = r; i < r + k; i++) {
                        if (getRowSum(rowPS, i, c, c + k - 1) != target) {
                            ok = false;
                            break;
                        }
                    }
                    if (!ok) continue;
                    // check columns
                    for (int j = c; j < c + k; j++) {
                        if (getColSum(colPS, j, r, r + k - 1) != target) {
                            ok = false;
                            break;
                        }
                    }
                    if (!ok) continue;
                    // check main diagonal
                    int d1 = (r - c) + (n - 1);
                    int start1 = idx1[r][c];
                    long diag1Sum = diag1Prefix[d1][start1 + k] - diag1Prefix[d1][start1];
                    if (diag1Sum != target) continue;
                    // check anti diagonal
                    int d2 = r + (c + k - 1);
                    int start2 = idx2[r][c + k - 1];
                    long diag2Sum = diag2Prefix[d2][start2 + k] - diag2Prefix[d2][start2];
                    if (diag2Sum != target) continue;
                    
                    maxK = k;
                    break; // no need to try smaller k for this (r,c)
                }
            }
        }
        
        return maxK;
    }
    
    private long getRowSum(long[][] rowPS, int i, int c1, int c2) {
        if (c1 == 0) return rowPS[i][c2];
        return rowPS[i][c2] - rowPS[i][c1 - 1];
    }
    
    private long getColSum(long[][] colPS, int j, int r1, int r2) {
        if (r1 == 0) return colPS[r2][j];
        return colPS[r2][j] - colPS[r1 - 1][j];
    }
}
