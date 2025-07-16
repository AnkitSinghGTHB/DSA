
class Solution {
  public:
    vector<int> maxOfMins(vector<int>& arr) {
        //uh
        int n = arr.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> s;
        for (int i = 0; i < n; ++i) {
            while (!s.empty() && arr[s.top()] >= arr[i]) {
                s.pop();
            }
            if (!s.empty()) {
                left[i] = s.top();
            }
            s.push(i);
        }
        while (!s.empty()) {
            s.pop();
        }
        for (int i = n - 1; i >= 0; --i) {
            while (!s.empty() && arr[s.top()] >= arr[i]) {
                s.pop();
            }
            if (!s.empty()) {
                right[i] = s.top();
            }
            s.push(i);
        }
        vector<int> result(n, 0);
        for (int i = 0; i < n; ++i) {
            int windowSize = right[i] - left[i] - 1;
            result[windowSize - 1] = max(result[windowSize - 1], arr[i]);
        }
        for (int i = n - 2; i >= 0; --i) {
            result[i] = max(result[i], result[i + 1]);
        }
        return result;
    }
};
