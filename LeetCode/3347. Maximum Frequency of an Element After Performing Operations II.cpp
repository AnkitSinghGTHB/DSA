class Solution {
public:
    int maxFrequency(vector<int>& nums, int k, int numOperations) {
        sort(nums.begin(), nums.end());
        unordered_set<long long> candidates;
        for (int num : nums) {
            candidates.insert(num);
            candidates.insert((long long)num - k);
            candidates.insert((long long)num + k);
        }
        //uh        
        int ans = 0;
        for (long long T : candidates) {
            long long left = T - k;
            long long right = T + k;
            int M = upper_bound(nums.begin(), nums.end(), right) - lower_bound(nums.begin(), nums.end(), left);
            int A = upper_bound(nums.begin(), nums.end(), T) - lower_bound(nums.begin(), nums.end(), T);
            int freq = min(M, A + numOperations);
            if (freq > ans) ans = freq;
        }
        return ans;
    }
};
