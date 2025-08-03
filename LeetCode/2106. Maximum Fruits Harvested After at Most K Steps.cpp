class Solution {
public:
    int maxTotalFruits(vector<vector<int>>& fruits, int startPos, int k) {
        vector<int> pos, arr;
        for (auto& f : fruits) {
            pos.push_back(f[0]);
            arr.push_back(f[1]);
        }
        int n = pos.size();
        vector<long long> prefix(n + 1, 0);
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }
        int left = 0;
        long long ans = 0;
        for (int right = 0; right < n; right++) {
            while (left <= right) {
                int min_pos = pos[left];
                int max_pos = pos[right];
                long long steps;
                if (startPos >= max_pos) {
                    steps = startPos - min_pos;
                }
                else if (startPos <= min_pos) {
                    steps = max_pos - startPos;
                }
                else {
                    steps = (long long)(max_pos-min_pos) + min((long long)(startPos-min_pos), (long long)(max_pos-startPos));
                }
                if (steps <= k) 
                    break;
                left++;
            }
            if (left <= right) {
                ans = max(ans, prefix[right+1] - prefix[left]);
            }
        }
        return ans;
    }
};
