class Solution {
public:
    long long maxSubarraySum(vector<int>& nums, int k) {
        int n = nums.size();
        vector<long long> P(n + 1);
        P[0] = 0;
        for (int i = 1; i <= n; i++) {
            P[i] = P[i - 1] + nums[i - 1];
        }        
        vector<vector<int>> indices(k);
        vector<vector<long long>> min_prefix(k);
        indices[0].push_back(0);
        min_prefix[0].push_back(0);        
        long long ans = LLONG_MIN;
        for (int j = 1; j <= n; j++) {
            int r = j % k;
            if (j >= k) {
                if (!indices[r].empty()) {
                    vector<int>& ind = indices[r];
                    vector<long long>& minp = min_prefix[r];
                    int target = j - k;
                    auto it = upper_bound(ind.begin(), ind.end(), target);
                    if (it != ind.begin()) {
                        it--;
                        int pos = it - ind.begin();
                        long long min_val = minp[pos];
                        long long candidate = P[j] - min_val;
                        if (candidate > ans) ans = candidate;
                    }
                }
            }
            indices[r].push_back(j);
            if (min_prefix[r].empty()) {
                min_prefix[r].push_back(P[j]);
            } 
            else {
                long long last_min = min_prefix[r].back();
                min_prefix[r].push_back(min(last_min, P[j]));
            }
        }
        return ans;
    }
};
