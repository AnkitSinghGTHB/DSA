class Solution {
public:
    int countPartitions(vector<int>& nums) {
        //imp: diff b/w sum of lsubar and rsubar is even 
        //odd-odd = even : 3-1=2, 213-17=196
        //even-even=even : 6-2=4, 994-46=948
        //odd-even= odd  : 5-4=1, 255-44=211
        //even-odd= odd? : 4-3=1, 256-41=215
        //so i need to check if both side sum is even or odd
        int n = nums.size();
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % 2 != 0) {
            return 0;
        }
        return n - 1;
    }
};
