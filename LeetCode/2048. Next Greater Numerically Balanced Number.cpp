class Solution {
public:
    int nextBeautifulNumber(int n) {
        for (int num = n + 1; ; num++) {
            if (isNumericallyBalanced(num)) {
                return num;
            }
        }
        return -1; // Should never reach here
    }
    bool isNumericallyBalanced(int num) {
        vector<int> count(10, 0);
        int temp = num;
        
        // Count frequency of each digit
        while (temp > 0) {
            int digit = temp % 10;
            count[digit]++;
            temp /= 10;
        }
        
        // Check if each digit d appears exactly d times
        for (int digit = 0; digit <= 9; digit++) {
            if (count[digit] > 0 && count[digit] != digit) {
                return false;
            }
        }
        return true;
    }
};
