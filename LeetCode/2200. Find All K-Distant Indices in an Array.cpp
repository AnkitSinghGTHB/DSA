class Solution {
public:
    vector<int> findKDistantIndices(vector<int>& nums, int key, int k) {
        //first we find all indices where nums[j] == key
        //then we mark indices in range [j-k, j+k] as k-distant
        //finally collect all marked indices
        vector<int> indcs;
        int n = nums.size();
        vector<bool> isKDistant(n, false);
        for (int j=0;j<n;++j) {
            if (nums[j] == key) {
                int start = max(0,j-k);
                int end = min(n-1,j+k);
                for (int i = start;i<=end;++i) {
                    isKDistant[i] = true;
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (isKDistant[i]) {
                indcs.push_back(i);
            }
        }
        return indcs; //this should do it
    }
};
