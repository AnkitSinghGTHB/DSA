class Solution {
public:
    int intersectionSizeTwo(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), [](const vector<int>& a, const vector<int>& b) {
            if (a[1] == b[1]) return a[0] > b[0];
            return a[1] < b[1];
        });
        int ans = 0;
        int p1 = -1, p2 = -1;
        for (const auto& interval : intervals) {
            int a = interval[0];
            int b = interval[1];
            if (a > p2) {
                ans += 2;
                p1 = b - 1;
                p2 = b;
            } 
            else if (a > p1) {
                ans += 1;
                p1 = p2;
                p2 = b;
            }
        }
        return ans;
    }
};
