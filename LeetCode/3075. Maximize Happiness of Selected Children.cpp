class Solution {
public:
    long long maximumHappinessSum(vector<int>& happiness, int k) {
        //lol we sort in descending order
        sort(happiness.rbegin(), happiness.rend());
        long long ans = 0;
        for (int i = 0; i < k; ++i) {
            long long current = happiness[i] - i;
            if (current > 0) {
                ans += current;
            }
        }
        return ans;
    }
};
