class Solution {
public:
    int maxValue(vector<vector<int>>& events, int k) {
        sort(events.begin(), events.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        int n = events.size();
        vector<int> ends;
        for (auto& e : events) {
            ends.push_back(e[1]);
        }

        vector<int> p_index(n, -1);
        for (int i = 1; i < n; i++) {
            auto it = lower_bound(ends.begin(), ends.begin() + i, events[i][0]);
            if (it != ends.begin()) {
                p_index[i] = it - ends.begin() - 1;
            }
        }

        vector<vector<int>> dp(n, vector<int>(k+1, 0));
        for (int j = 1; j <= k; j++) {
            dp[0][j] = events[0][2];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                int option1 = dp[i-1][j];
                int option2 = events[i][2];
                if (p_index[i] != -1) {
                    option2 += dp[p_index[i]][j-1];
                }
                dp[i][j] = max(option1, option2);
            }
        }

        return dp[n-1][k];
    }
};
