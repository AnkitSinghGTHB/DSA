class Solution {
public:
    int findSmallestInteger(vector<int>& nums, int value) {
        int n = nums.size();
        vector<int> freq(value, 0);
        for (int num : nums) { // count frequencies of remainders
            int rem = (num % value + value) % value; //handle negative numbers
            freq[rem]++;
        }        
        for (int x = 0; x <= n; x++) {//try to build numbers from 0 upwards
            int rem = x % value;
            if (freq[rem] > 0) {
                freq[rem]--;
            } 
            else {
                return x;
            }
        }        
        return n; //if we can build all numbers from 0 to n-1
    }
};
