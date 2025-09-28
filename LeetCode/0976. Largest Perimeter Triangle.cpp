class Solution {
public:
    int largestPerimeter(vector<int>& nums) {
        //this is suspiciously not easy at all
        sort(nums.begin(), nums.end());
        for (int i = nums.size()-1; i >= 2; i--) {
            if (nums[i-2] + nums[i-1] > nums[i]) {
                return nums[i-2] + nums[i-1] + nums[i];
            }
        }
        return 0;
    }
};
