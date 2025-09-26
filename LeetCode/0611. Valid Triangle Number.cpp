class Solution {
public:
    int triangleNumber(vector<int>& nums) {
        //a+b>c was the criteria ig
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int count = 0;
        for (int i = n - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    //all pairs between l and r-1 will also satisfy the condition
                    count += (right - left);
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        return count;
    }
};
