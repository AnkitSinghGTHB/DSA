class Solution {
public:
    int countMaxOrSubsets(vector<int>& nums) {
        //can i basically maintain a max and iterate through the whole array?
        //and then coutn everytime i have a max or?
        int n = nums.size();
        int maxOr = 0, count = 0;
        //calculate the maximum OR over all subsets
        for (int mask = 1; mask < (1 << n); ++mask) {
            int currOr = 0;
            for (int i = 0; i < n; ++i) {
                if (mask & (1 << i)) {
                    currOr |= nums[i];
                }
            }
            if (currOr > maxOr) {
                maxOr = currOr;
                count = 1;
            } else if (currOr == maxOr) {
                count++;
            }
        }
        return count;
    }
};
