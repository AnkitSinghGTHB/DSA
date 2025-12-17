class Solution {
public:
    long long maximumProfit(vector<int>& prices, int k) {
        int n = prices.size();
        // To solve this problem, I have used some amount of AI to accelerate
        // We'll use two 2D arrays: prev and cur, each of size (k+1) x 3
        // dp[t][s] = max profit with t transactions completed and state s
        // s=0: no position, s=1: in normal transaction, s=2: in short transaction
        // Initialize with -inf
        const long long INF = -1e18;
        vector<vector<long long>> prev(k+1, vector<long long>(3, INF));
        prev[0][0] = 0;  // start with no transaction and no position

        for (int i = 0; i < n; ++i) {
            vector<vector<long long>> cur(k+1, vector<long long>(3, INF));
            for (int t = 0; t <= k; ++t) {
                // State 0: no position
                if (prev[t][0] != INF) {
                    long long profit = prev[t][0];                    
                    cur[t][0] = max(cur[t][0], profit);// Do nothing                    
                    cur[t][1] = max(cur[t][1], profit - prices[i]);// Start normal transaction
                    cur[t][2] = max(cur[t][2], profit + prices[i]);// Start short transaction
                }
                // State 1: in normal transaction (holding a stock)
                if (prev[t][1] != INF) {
                    long long profit = prev[t][1];                    
                    cur[t][1] = max(cur[t][1], profit);// Do nothing                   
                    if (t < k) {
                        cur[t+1][0] = max(cur[t+1][0], profit + prices[i]); // Complete normal transaction (sell)
                    }
                }
                // State 2: in short transaction (sold and waiting to buy back)
                if (prev[t][2] != INF) {
                    long long profit = prev[t][2];
                    // Do nothing
                    cur[t][2] = max(cur[t][2], profit);
                    // Complete short transaction (buy back)
                    if (t < k) {
                        cur[t+1][0] = max(cur[t+1][0], profit - prices[i]);
                    }
                }
            }
            prev = move(cur);
        }
        long long ans = 0;
        for (int t = 0; t <= k; ++t) {
            ans = max(ans, prev[t][0]);
        }
        return ans;
    }
};
