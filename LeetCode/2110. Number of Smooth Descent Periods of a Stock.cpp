class Solution {
public:
    long long getDescentPeriods(vector<int>& prices) {
        int n = prices.size();
        long long ans = 0;
        int runLength = 1; //start with the first element as a run of length 1
        for (int i = 1; i < n; ++i) {
            if (prices[i] == prices[i-1] - 1) {
                ++runLength; //continue the current run
            }
            else {
                ans += (long long)runLength * (runLength + 1) / 2;
                runLength = 1; //start a new run
            }
        }       
        ans += (long long)runLength * (runLength + 1) / 2; //add the last run
        return ans;
    }
};
