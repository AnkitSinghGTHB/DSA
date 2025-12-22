class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int n = strs.size();          // number of rows
        int m = strs[0].size();       // number of columns        
        // compat[k][j] = true if column k can come before column j (k < j)
        vector<vector<bool>> compat(m, vector<bool>(m, false));        
        // precompute compatibility for all pairs of columns
        for (int j = 0; j < m; ++j) {
            for (int k = 0; k < j; ++k) {
                bool ok = true;
                for (int i = 0; i < n; ++i) {
                    if (strs[i][k] > strs[i][j]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) compat[k][j] = true;
            }
        }        
        // dp[j] = length of longest chain ending at column j
        vector<int> dp(m, 1);
        int max_len = 1;        
        for (int j = 0; j < m; ++j) {
            for (int k = 0; k < j; ++k) {
                if (compat[k][j]) {
                    dp[j] = max(dp[j], dp[k] + 1);
                }
            }
            max_len = max(max_len, dp[j]);
        }        
        // min deletions = total columns - columns kept
        return m - max_len;
    }
};
