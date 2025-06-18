class Solution {
public:
    vector<vector<int>> divideArray(vector<int>& nums, int k) {
        //n is a multiple of 3
        //n/3 subarrays of size 3
        //diff should be less than or equal to k
        //how about i sort and then divide?
        sort(nums.begin(),nums.end());
        vector<vector<int>> res;
        int n=nums.size();
        for (int i=0; i<n; i+=3){
            int a=nums[i], b=nums[i+1], c=nums[i+2];
            if (c-a<=k) {
                res.push_back({a,b,c});
            }
            else {//yk what? this condition makes this question actually way easier
                return {};
            }
        }
        return res;
    }
};
