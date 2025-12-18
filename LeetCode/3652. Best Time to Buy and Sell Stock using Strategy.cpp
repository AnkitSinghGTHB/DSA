class Solution {
public:
    long long maxProfit(vector<int>& prices, vector<int>& strategy, int k) {
        int n = prices.size();
        int half = k / 2;
        vector<long long> pref_price(n + 1, 0);
        vector<long long> pref_prod(n + 1, 0);
        long long base = 0;
        for (int i = 0; i < n; ++i) {
            pref_price[i + 1] = pref_price[i] + prices[i];
            long long prod = (long long)strategy[i] * prices[i];
            pref_prod[i + 1] = pref_prod[i] + prod;
            base += prod;
        }
        long long ans = base;
        for (int L = 0; L <= n - k; ++L) {
            long long sum_prices_right = pref_price[L + k] - pref_price[L + half];
            long long sum_prod_window = pref_prod[L + k] - pref_prod[L];
            long long candidate = base + sum_prices_right - sum_prod_window;
            if (candidate > ans) ans = candidate;
        }
        return ans;
    }
};
