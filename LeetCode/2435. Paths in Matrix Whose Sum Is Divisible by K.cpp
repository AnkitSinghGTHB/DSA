class Solution {
public:
    int numberOfPaths(vector<vector<int>>& grid, int k) {
        int mod = 1e9 + 7;
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>> prev(n, vector<int>(k, 0));
        vector<vector<int>> curr(n, vector<int>(k, 0));
        
        // Initialize the first row
        int first_rem = grid[0][0] % k;
        curr[0][first_rem] = 1;
        for (int j = 1; j < n; j++) {
            int val = grid[0][j] % k;
            for (int r = 0; r < k; r++) {
                if (curr[j-1][r] > 0) {
                    int new_r = (r + val) % k;
                    curr[j][new_r] = (curr[j][new_r] + curr[j-1][r]) % mod;
                }
            }
        }
        prev = curr;
        
        for (int i = 1; i < m; i++) {
            // Reset curr to zeros
            for (int j = 0; j < n; j++) {
                fill(curr[j].begin(), curr[j].end(), 0);
            }
            
            // Process first column of current row
            int val0 = grid[i][0] % k;
            for (int r = 0; r < k; r++) {
                if (prev[0][r] > 0) {
                    int new_r = (r + val0) % k;
                    curr[0][new_r] = (curr[0][new_r] + prev[0][r]) % mod;
                }
            }
            
            // Process remaining columns
            for (int j = 1; j < n; j++) {
                int val = grid[i][j] % k;
                // From left
                for (int r = 0; r < k; r++) {
                    if (curr[j-1][r] > 0) {
                        int new_r = (r + val) % k;
                        curr[j][new_r] = (curr[j][new_r] + curr[j-1][r]) % mod;
                    }
                }
                // From top
                for (int r = 0; r < k; r++) {
                    if (prev[j][r] > 0) {
                        int new_r = (r + val) % k;
                        curr[j][new_r] = (curr[j][new_r] + prev[j][r]) % mod;
                    }
                }
            }
            prev = curr;
        }
        
        return prev[n-1][0];
    }
};
