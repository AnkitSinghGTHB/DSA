class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int n = strs.size(); // number of rows
        int m = strs[0].size(); // number of columns
        vector<string> cur(n, ""); // current strings for each row
        int kept = 0; // number of columns kept

        for (int j = 0; j < m; ++j) {
            vector<string> nxt = cur;
            for (int i = 0; i < n; ++i) {
                nxt[i] += strs[i][j];
            }
            bool ok = true;
            for (int i = 0; i < n - 1; ++i) {
                if (nxt[i] > nxt[i + 1]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                cur = nxt;
                ++kept;
            }
        }
        return m - kept;
    }
};
