class Solution {
public:
    bool hasIncreasingSubarrays(vector<int>& nums, int k) {
        //precompute which positions can start a strictly increasing subarray of length k
        //check for two adjacent increasing subarrays
        int n = nums.size();
        vector<bool> isIncreasing(n, false);        
        for (int i = 0; i <= n - k; i++){
            bool valid = true;
            for (int j = i + 1; j < i + k; j++){
                if (nums[j] <= nums[j - 1]) {
                    valid = false;
                    break;
                }
            }
            isIncreasing[i] = valid;
        }        
        for (int i = 0; i <= n - 2 * k; i++) {
            if (isIncreasing[i] && isIncreasing[i + k]) {
                return true;
            }
        }        
        return false;
    }
};
