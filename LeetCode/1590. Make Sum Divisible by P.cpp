class Solution {
public:
    int minSubarray(vector<int>& nums, int p) {
        int total = 0;
        for (int x : nums) {
            total = (total + x) % p;
        }
        if (total == 0) return 0;        
        int n = nums.size();
        unordered_map<int, int> last;
        last[0] = -1;
        int prefix = 0;
        int ans = n;        
        for (int i = 0; i < n; i++) {
            prefix = (prefix + nums[i]) % p;
            int need = (prefix - total + p) % p;
            if (last.find(need) != last.end()) {
                ans = min(ans, i - last[need]);
            }
            last[prefix] = i;
        }        
        return ans < n ? ans : -1;
    }
};
