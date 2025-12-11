class Solution {
public:
    int countCoveredBuildings(int n, vector<vector<int>>& buildings) {
        unordered_map<int, int> minY, maxY, minX, maxX;        
        // First pass: compute min and max for each x and y
        for (const auto& b : buildings) {
            int x = b[0], y = b[1];
            if (minY.find(x) == minY.end()) {
                minY[x] = y;
                maxY[x] = y;
            } 
            else {
                minY[x] = min(minY[x], y);
                maxY[x] = max(maxY[x], y);
            }
            if (minX.find(y) == minX.end()) {
                minX[y] = x;
                maxX[y] = x;
            } 
            else {
                minX[y] = min(minX[y], x);
                maxX[y] = max(maxX[y], x);
            }
        }        
        int count = 0;
        // Second pass: check each building
        for (const auto& b : buildings) {
            int x = b[0], y = b[1];
            if (y > minY[x] && y < maxY[x] && x > minX[y] && x < maxX[y]) {
                count++;
            }
        }
        return count;
    }
};
