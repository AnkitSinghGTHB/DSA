class Solution {
public:
    bool isPowerOfFour(int n) {
        //can use the logic of recursion but its asking to do w/o rec & loop
        //but let me use a loop cuz why not
        if (n <= 0) {
            return false;
        }
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
};
