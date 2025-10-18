class Solution {
public:
    int maxDistinctElements(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int count = 0;
        long long prev = LLONG_MIN; //last assigned value
        for (int num : nums) {
            //try to assign the smallest possible value ≥ max(prev+1, num-k)
            long long target = max(prev + 1, (long long)num - k);
            if (target <= (long long)num + k) {
                count++; //we can assign this value
                prev = target;
            } //otherwise skip this element 
            }
            return count;
        }
    };
