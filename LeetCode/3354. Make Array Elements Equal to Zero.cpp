class Solution {
public:
    int countValidSelections(vector<int>& nums) {
        int n = nums.size();
        int count = 0;        
        for (int start = 0; start < n; start++) {
            if (nums[start] != 0) continue;            
            for (int dir : {-1, 1}) {
                if (simulateWithLargeLimit(nums, start, dir)) {
                    count++;
                }
            }
        }        
        return count;
    }
    
    bool simulateWithLargeLimit(vector<int> nums, int start, int initDir) {
        int n = nums.size();
        int curr = start;
        int dir = initDir;        
        // Very large iteration limit for difficult cases
        int max_iter = 1000000;
        int iter = 0;        
        while (iter++ < max_iter) {
            curr += dir;            
            if (curr < 0 || curr >= n) {
                for (int num : nums) if (num > 0) return false;
                return true;
            }            
            if (nums[curr] > 0) {
                nums[curr]--;
                dir = -dir;
            }
        }        
        // After many iterations, check current state
        for (int num : nums) if (num > 0) return false;
        return true;
    }
};
