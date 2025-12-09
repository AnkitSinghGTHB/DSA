class Solution {
public:
    int specialTriplets(vector<int>& nums) {
        const int MOD = 1e9 + 7;
        int n = nums.size();
        const int MAX_VAL = 200001; // 2 * 100000 = 200000, so we need indices up to 200000
        vector<int> left(MAX_VAL, 0), right(MAX_VAL, 0);
        
        // Initialize right frequency array with all elements
        for (int x : nums) {
            right[x]++;
        }
        
        long long ans = 0;
        for (int j = 0; j < n; ++j) {
            int val = nums[j];
            right[val]--;               // remove current element from right side
            int target = 2 * val;       // value we need for i and k
            if (target < MAX_VAL) {     // safety check (always true given constraints)
                long long l = left[target];
                long long r = right[target];
                ans = (ans + l * r) % MOD;
            }
            left[val]++;                // add current element to left side
        }
        return ans;
    }
};
