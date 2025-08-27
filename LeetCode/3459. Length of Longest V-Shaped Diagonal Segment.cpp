class Solution {
public:
    int lenOfVDiagonal(vector<vector<int>>& grid) {
        //note: this solution was made with the help of ai tools
        int m = grid.size();
        int n = grid[0].size();
        
        typedef vector<int> VI;
        typedef vector<VI> VVI;
        typedef vector<VVI> VVVI;
        typedef vector<VVVI> VVVVI;
        
        VVVVI dp(m, VVVI(n, VVI(4, VI(2, 0))));
        
        vector<int> di = {1, 1, -1, -1};
        vector<int> dj = {1, -1, -1, 1};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        dp[i][j][d][0] = 1;
                    }
                }
            }
        }
        
        auto clockwise = [](int d) {
            return (d + 1) % 4;
        };
        
        bool changed = true;
        while (changed) {
            changed = false;
            
            for (int d = 0; d < 4; d++) {
                int start_i, start_j, end_i, end_j, step_i, step_j;
                if (d == 0) {
                    start_i = 0; end_i = m; step_i = 1;
                    start_j = 0; end_j = n; step_j = 1;
                } else if (d == 1) {
                    start_i = 0; end_i = m; step_i = 1;
                    start_j = n-1; end_j = -1; step_j = -1;
                } else if (d == 2) {
                    start_i = m-1; end_i = -1; step_i = -1;
                    start_j = n-1; end_j = -1; step_j = -1;
                } else {
                    start_i = m-1; end_i = -1; step_i = -1;
                    start_j = 0; end_j = n; step_j = 1;
                }
                
                for (int i = start_i; i != end_i; i += step_i) {
                    for (int j = start_j; j != end_j; j += step_j) {
                        int pi = i - di[d];
                        int pj = j - dj[d];
                        if (pi >= 0 && pi < m && pj >= 0 && pj < n) {
                            int L = dp[pi][pj][d][0];
                            if (L > 0) {
                                int required = ((L + 1) % 2 == 0) ? 2 : 0;
                                if (grid[i][j] == required) {
                                    if (L + 1 > dp[i][j][d][0]) {
                                        dp[i][j][d][0] = L + 1;
                                        changed = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int old_d = 0; old_d < 4; old_d++) {
                        if (dp[i][j][old_d][0] > 0) {
                            int new_d = clockwise(old_d);
                            if (dp[i][j][old_d][0] > dp[i][j][new_d][1]) {
                                dp[i][j][new_d][1] = dp[i][j][old_d][0];
                                changed = true;
                            }
                        }
                    }
                }
            }
            
            for (int d = 0; d < 4; d++) {
                int start_i, start_j, end_i, end_j, step_i, step_j;
                if (d == 0) {
                    start_i = 0; end_i = m; step_i = 1;
                    start_j = 0; end_j = n; step_j = 1;
                } else if (d == 1) {
                    start_i = 0; end_i = m; step_i = 1;
                    start_j = n-1; end_j = -1; step_j = -1;
                } else if (d == 2) {
                    start_i = m-1; end_i = -1; step_i = -1;
                    start_j = n-1; end_j = -1; step_j = -1;
                } else {
                    start_i = m-1; end_i = -1; step_i = -1;
                    start_j = 0; end_j = n; step_j = 1;
                }
                
                for (int i = start_i; i != end_i; i += step_i) {
                    for (int j = start_j; j != end_j; j += step_j) {
                        int pi = i - di[d];
                        int pj = j - dj[d];
                        if (pi >= 0 && pi < m && pj >= 0 && pj < n) {
                            int L = dp[pi][pj][d][1];
                            if (L > 0) {
                                int required = ((L + 1) % 2 == 0) ? 2 : 0;
                                if (grid[i][j] == required) {
                                    if (L + 1 > dp[i][j][d][1]) {
                                        dp[i][j][d][1] = L + 1;
                                        changed = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 4; d++) {
                    for (int t = 0; t < 2; t++) {
                        ans = max(ans, dp[i][j][d][t]);
                    }
                }
            }
        }
        return ans;
    }
};
