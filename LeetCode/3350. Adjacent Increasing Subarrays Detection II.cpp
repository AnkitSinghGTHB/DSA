class Solution {
public:
    int maxIncreasingSubarrays(vector<int>& nums) {
        int n = nums.size();        
        //precompute the length of increasing sequence ending at each position
        vector<int> incLen(n, 1);
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                incLen[i] = incLen[i - 1] + 1;
            } else {
                incLen[i] = 1;
            }
        }        
        int maxK = 1;        
        //check all possible split points
        for (int i = 1; i < n; i++) {
            // first subarray ends at i-1, second starts at i
            int leftK = incLen[i - 1];  //max length for first subarray
            
            //for the second subarray starting at i, we need to check
            //if there's an increasing sequence of length k starting at i
            //this is true if incLen[i+k-1] >= k
            
            //binary search for maximum k for this split
            int low = 1, high = min(leftK, n - i);
            int best = 1;            
            while (low <= high) {
                int k = low + (high - low) / 2;                
                if (incLen[i + k - 1] >= k) {
                    best = k;
                    low = k + 1;
                } else {
                    high = k - 1;
                }
            }            
            maxK = max(maxK, best);
        }        
        return maxK;
    }
};
