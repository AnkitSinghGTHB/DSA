class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        //so a bitwise AND [&] maximiser using sliding window (Or not maybe?)
        //or maybe we could count the max number that repeats the max times
        //then that would be easy, just use a loop to check, get the max, 
        //check the max counter, new max, new maxcounter <here sameNumLen>
        int n = nums.size();
        int ans = 0;
        int maxIdx = 0;
        int sameNumLen = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == nums[maxIdx]) {
                ans = max(ans, ++sameNumLen);
            }
            else if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
                sameNumLen = 1;
                ans = 1;
            }
            else {
                sameNumLen = 0;
            }
        }
        return ans;
    }
};
