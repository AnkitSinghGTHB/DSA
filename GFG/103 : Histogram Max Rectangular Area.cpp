class Solution {
  public:
    int getMaxArea(vector<int> &arr) {
        stack<int> s;
        int maxArea = 0;
        int n = arr.size();
        for (int i = 0; i <= n; ++i) {
            while (!s.empty() && (i == n || arr[s.top()] > arr[i])) {
                int height = arr[s.top()];
                s.pop();
                int width = s.empty() ? i : i - s.top() - 1;
                maxArea = max(maxArea, height * width);
            }
            if (i < n) s.push(i);
        }
        return maxArea;
    }
};
