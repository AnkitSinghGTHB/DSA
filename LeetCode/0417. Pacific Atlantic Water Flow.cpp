class Solution {
public:
    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        //water flows from higher/equal height to lower/equal height, so we 
        //traverse from ocean inward following the reverse path 
        //(current height <= next height)
        if (heights.empty() || heights[0].empty()) return {};
        
        int m = heights.size(), n = heights[0].size();
        vector<vector<bool>> pacific(m, vector<bool>(n, false));
        vector<vector<bool>> atlantic(m, vector<bool>(n, false));
        
        // DFS from Pacific borders (left and top)
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, m, n); // left border
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, m, n); // top border
        }
        
        // DFS from Atlantic borders (right and bottom)
        for (int i = 0; i < m; i++) {
            dfs(heights, atlantic, i, n - 1, m, n); // right border
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, atlantic, m - 1, j, m, n); // bottom border
        }
        
        // Find cells that can reach both oceans
        vector<vector<int>> result;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.push_back({i, j});
                }
            }
        }
        
        return result;
    }

    void dfs(vector<vector<int>>& heights, vector<vector<bool>>& visited, 
             int i, int j, int m, int n) {
        visited[i][j] = true;
        
        vector<pair<int, int>> directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        for (auto& dir : directions) {
            int ni = i + dir.first;
            int nj = j + dir.second;
            
            // Check bounds and if not visited
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && !visited[ni][nj]) {
                // Water can flow from (ni, nj) to (i, j) if heights[ni][nj] >= heights[i][j]
                if (heights[ni][nj] >= heights[i][j]) {
                    dfs(heights, visited, ni, nj, m, n);
                }
            }
        }
    }
};
