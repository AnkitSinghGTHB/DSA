class Solution {
public:
    int minCost(string colors, vector<int>& neededTime) {
        int totalTime = 0;
        int n = colors.size();
        
        for (int i = 1; i < n; i++) {
            // If current balloon has same color as previous
            if (colors[i] == colors[i - 1]) {
                // We need to remove one of them
                // Keep the one with larger removal time, remove the smaller one
                totalTime += min(neededTime[i], neededTime[i - 1]);
                // Update neededTime[i] to be the max for the next comparison
                neededTime[i] = max(neededTime[i], neededTime[i - 1]);
            }
        }
        
        return totalTime;
    }
};
