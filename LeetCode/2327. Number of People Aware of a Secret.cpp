class Solution {
public:
    int peopleAwareOfSecret(int n, int delay, int forget) {
        //i really hate dp questions, but i'd let this one pass
        const int mod = 1e9 + 7;
        vector<long long> f(n + 1, 0);
        vector<long long> F(n + 1, 0);
        f[1] = 1;
        F[1] = 1;
        for (int i = 2; i <= n; i++) {
            int low = max(1, i - forget + 1);
            int high = i - delay;
            if (high < 1) {
                f[i] = 0;
            } else {
                if (low > high) {
                    f[i] = 0;
                } else {
                    f[i] = (F[high] - F[low - 1] + mod) % mod;
                }
            }
            F[i] = (F[i - 1] + f[i]) % mod;
        }
        long long ans = 0;
        int start = max(1, n - forget + 1);
        for (int i = start; i <= n; i++) {
            ans = (ans + f[i]) % mod;
        }
        return ans;
    }
};
