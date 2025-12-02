class Solution {
public:
    int countTrapezoids(vector<vector<int>>& points) {
        const int MOD = 1000000007;
        unordered_map<int, int> y_count;
        for (const auto& p : points) {
            y_count[p[1]]++;
        }        
        long long S = 0, Sq = 0;
        for (const auto& [y, cnt] : y_count) {
            if (cnt >= 2) {
                long long a = (long long)cnt * (cnt - 1) / 2 % MOD;
                S = (S + a) % MOD;
                Sq = (Sq + a * a) % MOD;
            }
        }        
        long long ans = (S * S % MOD - Sq + MOD) % MOD;
        ans = ans * 500000004 % MOD; // Multiply by inverse of 2 modulo MOD
        return (int)ans;
    }
};
