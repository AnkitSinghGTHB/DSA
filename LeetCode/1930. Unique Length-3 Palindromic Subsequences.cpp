class Solution {
public:
    int countPalindromicSubsequence(string s) {
        vector<vector<int>> indices(26);
        int n = s.size();
        for (int i = 0; i < n; i++) {
            int idx = s[i] - 'a';
            indices[idx].push_back(i);
        }
        int ans = 0;
        for (int a = 0; a < 26; a++) {
            if (indices[a].size() < 2) continue;
            int l = indices[a][0];
            int r = indices[a].back();
            if (l >= r) continue;
            for (int x = 0; x < 26; x++) {
                if (indices[x].empty()) continue;
                auto it = upper_bound(indices[x].begin(), indices[x].end(), l);
                if (it != indices[x].end() && *it < r) {
                    ans++;
                }
            }
        }
        return ans;
    }
};
