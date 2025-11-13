class Solution {
public:
    int maxOperations(string s) {
        int n = s.size();
        vector<int> groups;
        for (int i = 0; i < n; ) {
            if (s[i] == '1') {
                int count = 0;
                while (i < n && s[i] == '1') {
                    count++;
                    i++;
                }
                groups.push_back(count);
            } else {
                i++;
            }
        }
        int m = groups.size();
        int I = (n > 0 && s[n-1] == '0') ? 1 : 0;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            ans += groups[i] * (m - i - 1 + I);
        }
        return ans;
    }
};
