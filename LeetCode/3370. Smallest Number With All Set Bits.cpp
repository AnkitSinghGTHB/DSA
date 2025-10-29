class Solution {
public:
    int smallestNumber(int n) {
        int k = 1;
        while (true) {
            int candidate = (1 << k) - 1;  // 2^k - 1
            if (candidate >= n) {
                return candidate;
            }
            k++;
        }
    }
};
