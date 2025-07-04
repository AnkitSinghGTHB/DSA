class Solution {
public:
    char kthCharacter(long long k, vector<int>& operations) {
        //navigates the hierarchical structure of the string operations without
        //explicit construction, leveraging backward reduction and bit manipulation
        //to handle large inputs
        long long x = k - 1;
        int n = operations.size();
        long long t = 0;
        int max_i = min(60, n - 1);
        for (int i = max_i; i >= 0; i--) {
            long long bit_val = (1LL << i);
            if (x >= bit_val) {
                x -= bit_val;
                if (operations[i] == 1) {
                    t++;
                }
            }
        }
        t %= 26;
        return 'a' + t;
    }
};
