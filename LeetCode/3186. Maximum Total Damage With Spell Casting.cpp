class Solution {
public:
    long long maximumTotalDamage(vector<int>& power) {
        unordered_map<int, int> freq;
        for (int p : power) {
            freq[p]++;
        }
        vector<int> uniquePowers;
        for (auto& [p, count] : freq) {
            uniquePowers.push_back(p);
        }
        sort(uniquePowers.begin(), uniquePowers.end());
        int n = uniquePowers.size();
        vector<long long> dp(n, 0);
        int j = -1; //pointer to the last compatible power (at least 3 less)
        for (int i = 0; i < n; i++) {
            int currentPower = uniquePowers[i];
            long long currentDamage = (long long)currentPower * freq[currentPower];
            while (j + 1 < i && uniquePowers[j + 1] <= currentPower - 3) {
                j++;
            }
            long long skip = (i > 0) ? dp[i - 1] : 0;
            long long use = currentDamage;
            if (j >= 0) {
                use += dp[j];
            }
            dp[i] = max(skip, use);
        }
        return dp[n - 1];
    }
};
