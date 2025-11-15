class Solution {
public:
    int numberOfSubstrings(string s) {
        int n = s.size();
        vector<int> Z;
        for (int i = 0; i < n; i++) {
            if (s[i] == '0') {
                Z.push_back(i);
            }
        }
        int m = Z.size();
        int ans = 0;
        for (int l = 0; l < n; l++) {
            int idx = lower_bound(Z.begin(), Z.end(), l) - Z.begin();
            if (idx >= m) {
                ans += (n - l);
            } 
            else {
                if (Z[idx] > l) {
                    ans += (Z[idx] - l);
                }
            }
            for (int k = 1; k <= 200; k++) {
                if (idx + k - 1 >= m) break;
                int p = Z[idx + k - 1];
                int q = (idx + k < m) ? Z[idx + k] : n;
                long long required_r = l + 1LL * k * k + k - 1;
                if (required_r >= n) break;
                int start_r = max(static_cast<long long>(p), required_r);
                if (start_r < q) {
                    ans += (q - start_r);
                }
            }
        }
        return ans;
    }
};
