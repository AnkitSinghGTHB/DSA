class Solution {
public:
    int maximumLength(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> one(k, 0);
        vector<vector<int>> best2(k, vector<int>(k, 0));
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int r_i = nums[i] % k;
            for (int r = 0; r < k; r++) {
                if (one[r]) {
                    int v = (r + r_i) % k;
                    int total = max(2, best2[v][r] + 1);
                    if (total > best2[v][r_i]) {
                        best2[v][r_i] = total;
                    }
                    if (total > ans) {
                        ans = total;
                    }
                }
            }

            one[r_i] = 1;
        }
        return ans;
    }
};
