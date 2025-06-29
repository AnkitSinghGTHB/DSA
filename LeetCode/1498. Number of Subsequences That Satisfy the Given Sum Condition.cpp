const int mod = 1000000007;
class Solution {
public:
    int numSubseq(vector<int>& nums, int target) {
        //im getting cooked with this one
        //i see its a 2 ptr arr bSearch sorting ques
        //let me use the hints to benefit me
        //first we sort the array nums,
        //then use 2 ptr apprch, givn idc i as the min in subseq, find the madx j where
        //j>=i and nums[i]+nums[j]<=target
        //finally count the number of subsequences
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<int> pows(n, 1);
        for (int i = 1; i < n; ++i) {
            pows[i] = (pows[i-1] * 2) % mod;
        }
        
        int left = 0, right = n - 1;
        long long result = 0;
        
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                result = (result + pows[right - left]) % mod;
                ++left;
            } else {
                --right;
            }
        }
        
        return static_cast<int>(result % mod);
    }
};
