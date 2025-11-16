class Solution {
public:
    int numSub(string s) {
        const int MOD = 1e9 + 7;
        long long result = 0;
        int currentCount = 0;        
        for (char c : s) {
            if (c == '1') {
                currentCount++;
                result = (result + currentCount) % MOD;
            } 
            else {
                currentCount = 0;
            }
        }        
        return result;
    }
};
