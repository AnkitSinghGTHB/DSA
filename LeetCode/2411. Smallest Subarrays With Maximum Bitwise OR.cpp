class Solution {
public:
    vector<int> smallestSubarrays(vector<int>& nums) {
        //i think this is gonna need bSearch and sliding window apprch
        int n = nums.size();
        vector<int> suffixOR(n);
        suffixOR[n-1] = nums[n-1];
        for (int i = n-2; i >= 0; i--) {
            suffixOR[i] = nums[i] | suffixOR[i+1];
        }        
        vector<int> nearest(32, -1);
        vector<int> ans(n);
        for (int i = n-1; i >= 0; i--) {
            for (int b = 0; b < 32; b++) {
                if (nums[i] & (1 << b)) {
                    nearest[b] = i;
                }
            }
            int max_index = i;
            for (int b = 0; b < 32; b++) {
                if (suffixOR[i] & (1 << b)) {
                    if (nearest[b] > max_index) {
                        max_index = nearest[b];
                    }
                }
            }
            ans[i] = max_index - i + 1;
        }        
        return ans;
    }
};
