class Solution {
public:
    int countUnguarded(int m, int n, vector<vector<int>>& guards, vector<vector<int>>& walls) {
        // 0 = empty, 1 = guard, 2 = wall, 3 = guarded
        vector<vector<int>> grid(m, vector<int>(n, 0));
        
        // Mark guards and walls
        for (auto& guard : guards) {
            grid[guard[0]][guard[1]] = 1;
        }
        for (auto& wall : walls) {
            grid[wall[0]][wall[1]] = 2;
        }
        
        // Directions: up, right, down, left
        vector<pair<int, int>> dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // For each guard, mark visible cells
        for (auto& guard : guards) {
            int r = guard[0], c = guard[1];
            
            for (auto& dir : dirs) {
                int nr = r + dir.first;
                int nc = c + dir.second;
                
                // Propagate in this direction until hitting obstacle or boundary
                while (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    // Stop if we hit a wall or another guard
                    if (grid[nr][nc] == 2 || grid[nr][nc] == 1) {
                        break;
                    }
                    // Mark as guarded (use 3 to indicate guarded cell)
                    grid[nr][nc] = 3;
                    
                    // Move to next cell in this direction
                    nr += dir.first;
                    nc += dir.second;
                }
            }
        }
        
        // Count unoccupied and unguarded cells
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) { // Empty and not guarded
                    count++;
                }
            }
        }
        
        return count;
    }
};
