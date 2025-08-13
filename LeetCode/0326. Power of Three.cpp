class Solution {
public:
    bool isPowerOfThree(int n) {
        //uh ok? lets do bit manipulation again
        //will either check ternary base or n-2 casees
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) { //this is not efficient ig??
            n /= 3;
        }
        return n == 1;
    }
};
