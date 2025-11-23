class Solution {
public:
    int maxSumDivThree(vector<int>& nums) {
        vector<int> dp = {0, INT_MIN, INT_MIN};
        for (int num : nums) {
            vector<int> temp = dp;
            for (int i = 0; i < 3; i++) {
                if (dp[i] != INT_MIN) {
                    int rem = (i + num) % 3;
                    temp[rem] = max(temp[rem], dp[i] + num);
                }
            }
            dp = temp;
        }
        return dp[0];
    }
};
