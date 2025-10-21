class Solution {
public:
    int maxFrequency(vector<int>& nums, int k, int numOperations) {
        sort(nums.begin(), nums.end());
        unordered_set<int> candidates;
        for (int num : nums) {
            candidates.insert(num);
            candidates.insert(num - k);
            candidates.insert(num + k);
        }
        
        int ans = 0;
        for (int T : candidates) {
            int left = T - k;
            int right = T + k;
            int M = upper_bound(nums.begin(), nums.end(), right) - lower_bound(nums.begin(), nums.end(), left);
            int A = upper_bound(nums.begin(), nums.end(), T) - lower_bound(nums.begin(), nums.end(), T);
            int freq = min(M, A + numOperations);
            ans = max(ans, freq);
        }
        return ans;
    }
};
