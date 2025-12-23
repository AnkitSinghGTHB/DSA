class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        int n = events.size();
        vector<pair<int, int>> endVal; //(end, value)
        for (const auto& e : events) {
            endVal.emplace_back(e[1], e[2]);
        }
        sort(endVal.begin(), endVal.end());
        vector<int> ends, vals;
        for (const auto& p : endVal) {
            ends.push_back(p.first);
            vals.push_back(p.second);
        }
        vector<int> prefixMax(n);
        prefixMax[0] = vals[0];
        for (int i = 1; i < n; ++i) {
            prefixMax[i] = max(prefixMax[i - 1], vals[i]);
        }
        int ans = 0;
        for (const auto& e : events) {
            int start = e[0];
            int value = e[2];
            auto it = lower_bound(ends.begin(), ends.end(), start);//find the rightmost index with end < start
            int idx = it - ends.begin() - 1;
            int bestPrev = (idx >= 0) ? prefixMax[idx] : 0;
            ans = max(ans, value + bestPrev);
        }
        return ans;
    }
};
