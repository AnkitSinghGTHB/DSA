class Solution {
public:
    bool isDecimalPalindrome(long long num) {
        string s = to_string(num);
        string rev = s;
        reverse(rev.begin(), rev.end());
        return s == rev;
    }
    long long toDecimal(const vector<int>& digits, int k){//convert from b_k to I
        long long res = 0;
        for (int d : digits) {
            res = res * k + d;
        }
        return res;
    }
    void nextKMirror(vector<int>& A, int k) {//generate next base_k palindrome
        int n = A.size();
        for (int i = n / 2; i < n; ++i) {
            if (A[i] + 1 < k) {
                A[i]++;
                A[n - 1 - i] = A[i];
                for (int j = n / 2; j < i; ++j) {
                    A[j] = 0;
                    A[n - 1 - j] = 0;
                }
                return;
            }
        }
        vector<int> B(n + 1, 0);//overflow: increase length
        B[0] = 1;
        B[n] = 1;
        A = B;
    }
    long long kMirror(int k, int n) {
        long long ans = 0;
        vector<int> A = {0};

        for (int count = 0; count < n; ) {
            nextKMirror(A, k);
            long long num = toDecimal(A, k);
            if (isDecimalPalindrome(num)) {
                ans += num;
                count++;
            }
        }
        return ans;
    }
};
