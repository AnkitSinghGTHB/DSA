class Solution {
public:
    int maxFreeTime(int eventTime, int k, vector<int>& startTime, vector<int>& endTime) {
        int n = startTime.size();
        vector<int> gaps;
        gaps.push_back(startTime[0] - 0);
        for (int i = 1; i < n; i++) {
            gaps.push_back(startTime[i] - endTime[i - 1]);
        }
        gaps.push_back(eventTime - endTime[n - 1]);
        int maxFree = 0;
        int windowSum = 0;
        for (int i = 0; i < gaps.size(); ++i) {
            windowSum += gaps[i];
            if (i > k) {
                windowSum -= gaps[i - (k + 1)];
            }
            if (i >= k) {
                maxFree = max(maxFree, windowSum);
            }
        }
        return maxFree;
    }
};
