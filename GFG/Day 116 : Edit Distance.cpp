class Solution {
  public:
    // Function to compute the edit distance between two strings
    int editDistance(string& s1, string& s2) {
        //bruh lol, easy method O(n logn)
        //dp method can be difficult ngl, lets try
        int m = s1.size();
        int n = s2.size();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                } 
                else if (s1[i-1] == s2[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } 
                else {
                    dp[i][j] = 1 + min({dp[i][j-1], dp[i-1][j], dp[i-1][j-1]});
                }
            }
        }
        return dp[m][n];
    }
};
