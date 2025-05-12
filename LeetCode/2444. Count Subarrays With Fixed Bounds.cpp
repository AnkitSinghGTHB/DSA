class Solution {
public:
    long long countSubarrays(vector<int>& nums, int minK, int maxK) {
        //my first hard question and idk what to do, sliding window?
        int n = nums.size();
        long long count=0;
        int indexMin=-1,indexMax=-1; //apparantly starting with 0 doesnt work
        for (int i=0,j=-1;i<n;++i){
            if (nums[i] < minK || nums[i] > maxK){
                j = i;
            }
            if (nums[i] == minK){
                indexMin = i;
            }
            if (nums[i] == maxK){
                indexMax = i;
            }
            count += max(0, min(indexMin, indexMax) - j);
        }
        return count;
        //it wasnt that difficult tbh (and yeah the hints made it more difficult)
    }
};
