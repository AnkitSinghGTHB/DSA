class Solution {
public:
    int numberOfWays(int n, int x) {
        //so another dp ques?? T-T
        //ughhh, got it. made a smol mistake
        //move the "if (power > n) break" out of the inner loop
        //after calculation, check if the candidate should be used or not
        const int mod = 1000000007;
        vector<int> dp(n + 1, 0);
        dp[0] = 1;
        for (int i = 1; ; ++i){
            long long power = 1;
            for (int k = 0; k < x; ++k){
                power *= i;
                if (power > n) break;
            }
            if (power > n) break;
            int p = static_cast<int>(power);
            for (int j = n; j >= p; --j){
                dp[j] = (dp[j] + dp[j-p]) % mod;
            }
        }
        return dp[n] % mod;
    }
};
