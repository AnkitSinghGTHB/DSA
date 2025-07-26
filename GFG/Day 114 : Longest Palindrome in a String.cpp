class Solution {
  public:
    string longestPalindrome(string s) {
        if (s.empty()) return "";
        int n = s.size();
        int start = 0, max_len = 1;
        for (int i = 0; i < n; i++) {
            int l = i, r = i;
            while (l >= 0 && r < n && s[l] == s[r]) {
                if (r - l + 1 > max_len) {
                    start = l;
                    max_len = r - l + 1;
                }
                l--;
                r++;
            }
            l = i, r = i + 1;
            while (l >= 0 && r < n && s[l] == s[r]) {
                if (r - l + 1 > max_len) {
                    start = l;
                    max_len = r - l + 1;
                }
                l--;
                r++;
            }
        }
        return s.substr(start, max_len);
    }
};
