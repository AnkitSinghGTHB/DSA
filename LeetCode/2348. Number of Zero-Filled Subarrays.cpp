class Solution {
public:
    long long zeroFilledSubarray(vector<int>& nums) {
        //basically count the subarrs filled with 0s
        //bruh i got hit with integer overflow
        long long total=0;
        long long curr_len=0;
        for(int n : nums){
            if (n==0){
                curr_len++;
                total+=curr_len;
            }a
            else{
                curr_len=0;
            }
        }
        return total;
    }
};
