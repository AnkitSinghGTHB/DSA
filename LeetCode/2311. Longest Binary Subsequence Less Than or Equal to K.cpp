class Solution {
public:
    int longestSubsequence(string s, int k) {
        int n = s.size();
        int count = 0;
        long long val = 0, pow = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == '0') {
                count++;
            }
            else {
                if (pow <= k && val + pow <= k) {
                    val += pow;
                    count++;
                }
            }
            pow <<= 1;
            if (pow > k) break;
        }
        for (int i = n - count - 1; i >= 0; --i) {
            if (s[i] == '0') count++;
        }
        return count;
    }
};
