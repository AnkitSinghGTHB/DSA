class Solution {
public:
    int triangularSum(vector<int>& nums) {
        //recursion? nvm im gonna use a while loop
        //alright changing the nums array directly would also make it lossy
        while (nums.size() > 1) {
            vector<int> newNums;
            for (int i = 0; i < nums.size() - 1; i++) {
                newNums.push_back((nums[i] + nums[i + 1]) % 10);
            }
            nums = newNums;
        }
        return nums[0];
    }
};
