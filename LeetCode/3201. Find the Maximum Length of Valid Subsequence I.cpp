class Solution {
public:
    int maximumLength(vector<int>& nums) {
        int count_even = 0, count_odd = 0;
        int alt_even = 0, alt_odd = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                count_even++;
                alt_even = alt_odd + 1;
            }
            else {
                count_odd++;
                alt_odd = alt_even + 1;
            }
        }
        return max({count_even, count_odd, alt_even, alt_odd});
    }
};
