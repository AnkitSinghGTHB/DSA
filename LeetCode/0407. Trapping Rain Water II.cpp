class Solution {
public:
    int trapRainWater(vector<vector<int>>& heightMap) {
        //oh hell naw T_T i remember the original ques, lets just say i was annoyed
        //im pretty sure that we will be using bfs and min pq
        //also, yeah i took help from ai
        int m = heightMap.size(), n = heightMap[0].size();
        if (m < 3 || n < 3) return 0;
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq; //{height, index}
        vector<vector<bool>> visited(m, vector<bool>(n, false));
        //pushing border cells into the pq
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.push({heightMap[i][j], i * n + j});
                    visited[i][j] = true;
                }
            }
        }        
        int dirs[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int totalWater = 0;
        int maxHeight = 0;
        while (!pq.empty()) {
            auto [h, pos] = pq.top();
            pq.pop();
            int r = pos / n, c = pos % n;
            maxHeight = max(maxHeight, h);
            for (auto& dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if (heightMap[nr][nc] < maxHeight) {
                        totalWater += maxHeight - heightMap[nr][nc];
                    }
                    pq.push({heightMap[nr][nc], nr * n + nc});
                }
            }
        }
        return totalWater;
    }
};
