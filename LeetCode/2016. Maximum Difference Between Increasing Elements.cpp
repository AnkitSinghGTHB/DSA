class Solution {
public:
    int maximumDifference(vector<int>& nums) {
        //how about i keep a min track and from there ig we can maximise
        int minn=INT_MAX;
        int d=-1;
        int n=nums.size();
        for (int i =0;i<n;i++){
            minn=min(minn,nums[i]);
            if (nums[i]-minn>d){
                d=nums[i]-minn;
            }
        }
        if(d==0){return-1;}
        return d;
    }
};
