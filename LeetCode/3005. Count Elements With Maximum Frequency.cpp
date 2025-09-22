class Solution {
public:
    int maxFrequencyElements(vector<int>& nums) {
        //uh lets get freq, then get max freq
        //if multiple max freq, then add them
        unordered_map<int, int> d;
        for (int i=0;i<nums.size();i++){
            d[nums[i]]++;
        }
        int max_f=0,mc=1;
        for (auto i:d){
            if (i.second>max_f){
                max_f=i.second;
                mc=0;
            }
            if (i.second==max_f){
                mc++;
            }
        }
        return max_f*mc;
    }
};
