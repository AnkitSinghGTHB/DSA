class Solution {
public:
    long long minimumDifference(vector<int>& nums) {
        int m = nums.size();
        int n = m / 3;
        vector<long long> pref(m, 0);
        priority_queue<int> pq_max;
        long long sumA = 0;
        for (int i = 0; i < 2 * n; i++) {
            pq_max.push(nums[i]);
            sumA += nums[i];
            if (pq_max.size() > n) {
                int top = pq_max.top();
                pq_max.pop();
                sumA -= top;
            }
            if (i >= n - 1) {
                pref[i] = sumA;
            }
        }
        vector<long long> suf(m, LLONG_MIN);
        priority_queue<int, vector<int>, greater<int>> pq_min;
        long long sumB = 0;
        for (int i = m - 1; i >= 0; i--) {
            if (pq_min.size() < n) {
                pq_min.push(nums[i]);
                sumB += nums[i];
            } else if (nums[i] > pq_min.top()) {
                int top = pq_min.top();
                pq_min.pop();
                pq_min.push(nums[i]);
                sumB = sumB - top + nums[i];
            }
            if (pq_min.size() == n) {
                suf[i] = sumB;
            }
        }
        long long ans = LLONG_MAX;
        for (int i = n - 1; i < 2 * n; i++) {
            if (suf[i + 1] != LLONG_MIN) {
                ans = min(ans, pref[i] - suf[i + 1]);
            }
        }
        return ans;
    }
};
