class Solution {
public:
    int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    int minOperations(vector<int>& nums) {
        int n = nums.size();
        int count_ones = 0;
        for (int num : nums) {
            if (num == 1) count_ones++;
        }
        if (count_ones > 0) {
            return n - count_ones;
        }        
        int min_seg = INT_MAX;
        for (int i = 0; i < n; i++) {
            int g = nums[i];
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    min_seg = min(min_seg, j - i + 1);
                    break;
                }
            }
        }        
        if (min_seg == INT_MAX) return -1;
        return min_seg - 1 + n - 1;
    }
};
