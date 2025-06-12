class Solution {
public:
    int maxAdjacentDistance(vector<int>& nums) {
        //the input doesnt look circular to me, let me do it myself
        int n=nums.size();
        nums.push_back(nums[0]);
        //i need to find max abs diff so lets do it
        int mDiff=INT_MIN;
        for (int i=0;i<n;i++){
            int tDiff=abs(nums[i]-nums[i+1]);
            mDiff=max(tDiff,mDiff);
        }
        return mDiff;
    }
};
