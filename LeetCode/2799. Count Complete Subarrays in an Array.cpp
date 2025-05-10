class Solution {
public:
    int countCompleteSubarrays(vector<int>& nums) {
        unordered_map<int, int> countD;
        for (int i : nums){
            countD[i]=1;
        }
        int freq1 = countD.size(); //we get the unique elemnts only by this
        countD.clear();
        int subarrcount=0;
        int n = nums.size();
        for (int i=0,j=0; j<n;++j){
            countD[nums[j]]++;
            while (countD.size()==freq1){
                subarrcount+=n-j;
                if(--countD[nums[i]]==0){
                    countD.erase(nums[i]);
                }
                ++i;
            }
        }
        return subarrcount;
    }
};
