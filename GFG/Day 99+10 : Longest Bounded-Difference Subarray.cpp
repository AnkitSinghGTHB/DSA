class Solution {
  public:
    vector<int> longestSubarray(vector<int>& arr, int x) {
        int left = 0, max_len = 0, start = 0;
        int current_min = arr[0], current_max = arr[0];
        for (int right = 0; right < arr.size(); ++right) {
            current_min = min(current_min, arr[right]);
            current_max = max(current_max, arr[right]);
            while (current_max - current_min > x) {
                left++;
                current_min = *min_element(arr.begin() + left, arr.begin() + right + 1);
                current_max = *max_element(arr.begin() + left, arr.begin() + right + 1);
            }
            if (right - left + 1 > max_len) {
                max_len = right - left + 1;
                start = left;
            }
        }
        return vector<int>(arr.begin() + start, arr.begin() + start + max_len);
    }
};
