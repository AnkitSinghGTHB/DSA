class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        //sliding window but i can delete one elem wow
        //what about i check for biggest subarray with at max 1 zero
        //instead of using i and j, here i used right and left to clarify
        int n = nums.size();
        int left = 0;
        int z_count = 0;
        int size = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) {
                z_count++;
            }
            while (z_count > 1) {
                if (nums[left] == 0) {
                    z_count--;
                }
                left++;
            }
            size = max(size, right-left);
        }
        return size;
    }
};
