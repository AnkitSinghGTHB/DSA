class Solution {
public:
    int makeTheIntegerZero(int num1, int num2) {
        for (int k = 1; k <= 60; k++) {
            long long S = (long long)num1 - (long long)k * num2;
            if (S < k) continue;
            if (__builtin_popcountll(S) <= k) return k;
        }
        return -1;
    }
};
