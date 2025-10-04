class Solution {
public:
    int maxArea(vector<int>& height) {
        //bruh, nvm
        //area b/w 2 lines = min(height[i], height[j]) * (j-i)
        int left = 0;
        int right = height.size() - 1;
        int maxArea = 0;
        while (left < right) {
            int currentArea = min(height[left], height[right]) * (right - left);
            maxArea = max(maxArea, currentArea);
            if (height[left] < height[right]) {
                left++;
            } 
            else {
                right--;
            }
        }
        return maxArea;
    }
};
