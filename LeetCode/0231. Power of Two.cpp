class Solution {
public:
    bool isPowerOfTwo(int n) {
        //uhhh how did people fail this????
        //well i can check binary
        // n = 16 -> 10000, n-1 -> 01111, then AND will return 0
        // n = 15 -> 01111, n-1 -> 01110, then AND will return 01110
        //ofc negative n is wrong
        if (n>0 && (n & (n-1))==0){
            return true;
        }
        return false;
    }
};
