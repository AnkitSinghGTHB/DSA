class Solution {
public:
    long long maxRunTime(int n, vector<int>& batteries) {
        sort(batteries.begin(), batteries.end());
        int m = batteries.size();
        vector<long long> prefix(m + 1, 0);
        for (int i = 0; i < m; i++) {
            prefix[i + 1] = prefix[i] + batteries[i];
        }
        long long total = prefix[m];
        long long left = 0, right = total / n;
        while (left < right) {
            long long mid = left + (right - left + 1) / 2;
            int idx = lower_bound(batteries.begin(), batteries.end(), mid) - batteries.begin();
            long long sum_contrib = prefix[idx] + mid * (m - idx);
            if (sum_contrib >= n * mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
