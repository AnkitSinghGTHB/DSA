class Solution {
public:
    int countHillValley(vector<int>& nums) {
        vector<int> processed;
        for (int num : nums) {
            if (processed.empty() || num != processed.back()) {
                processed.push_back(num);
            }
        }
        int count = 0;
        for (int i = 1; i < processed.size() - 1; ++i) {
            int left = processed[i-1];
            int current = processed[i];
            int right = processed[i+1];
            if ((current > left && current > right) || (current < left && current < right)) {
                count++;
            }
        }
        return count;
    }
};
