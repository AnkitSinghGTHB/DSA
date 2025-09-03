class Solution {
public:
    int numberOfPairs(vector<vector<int>>& points) {
        sort(points.begin(), points.end(), [](const vector<int>& a, const vector<int>& b) {
            if (a[0] == b[0]) {
                return a[1] > b[1];
            }
            return a[0] < b[0];
        });
        int n = points.size();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int currentMax = INT_MIN;
            for (int j = i + 1; j < n; j++) {
                if (points[j][1] <= points[i][1]) {
                    if (currentMax < points[j][1]) {
                        count++;
                    }
                    currentMax = max(currentMax, points[j][1]);
                }
            }
        }
        return count;
    }
};
