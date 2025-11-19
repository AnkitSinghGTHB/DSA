class Solution {
public:
    int findFinalValue(vector<int>& nums, int original) {
        //uh. doing a linear search everytime is bad, lets shift to bsearch
        unordered_set<int> numSet(nums.begin(), nums.end());
        while (numSet.count(original)) {
            original *= 2;
        }
        return original;
    }
};
