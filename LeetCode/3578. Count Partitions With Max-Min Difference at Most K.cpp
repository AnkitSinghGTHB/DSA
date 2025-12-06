class Solution {
public:
    int countPartitions(vector<int>& nums, int k) {
        //ai soln
        int n = nums.size();
        const int MOD = 1e9 + 7;
        vector<long long> dp(n, 0);
        vector<long long> pref(n, 0);
        deque<int> max_dq, min_dq;
        int left = 0;

        for (int i = 0; i < n; ++i) {
            // Insert nums[i] into max deque (maintain decreasing order)
            while (!max_dq.empty() && nums[max_dq.back()] <= nums[i]) {
                max_dq.pop_back();
            }
            max_dq.push_back(i);
            // Insert nums[i] into min deque (maintain increasing order)
            while (!min_dq.empty() && nums[min_dq.back()] >= nums[i]) {
                min_dq.pop_back();
            }
            min_dq.push_back(i);

            // Shrink the window from the left until it becomes valid
            while (left <= i) {
                int cur_max = nums[max_dq.front()];
                int cur_min = nums[min_dq.front()];
                if (cur_max - cur_min > k) {
                    if (max_dq.front() == left) max_dq.pop_front();
                    if (min_dq.front() == left) min_dq.pop_front();
                    ++left;
                } else {
                    break;
                }
            }

            // Compute dp[i]
            if (left == 0) {
                dp[i] = (1 + (i > 0 ? pref[i - 1] : 0)) % MOD;
            } else {
                long long prev_sum = (i > 0 ? pref[i - 1] : 0);
                long long subtract = (left - 2 >= 0 ? pref[left - 2] : 0);
                dp[i] = (prev_sum - subtract + MOD) % MOD;
            }

            // Update prefix sum
            pref[i] = ((i > 0 ? pref[i - 1] : 0) + dp[i]) % MOD;
        }

        return dp[n - 1] % MOD;
    }
};
