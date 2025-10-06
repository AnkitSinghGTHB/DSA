class Solution {
public:
    int swimInWater(vector<vector<int>>& grid) {
        //lord forbid a man who knows how to swim T_T
        int n = grid.size();
        int left = grid[0][0];
        int right = n*n -1;
        
        while (left<right) {
            int mid = left+(right-left)/2;
            if (canReach(grid,mid)){
                right = mid;
            } 
            else {
                left = mid+1;
            }
        }
        return left;
    }
    //helper
    bool canReach(vector<vector<int>>& grid,int t) {
        int n = grid.size();
        if (grid[0][0] > t) return false;
        vector<vector<bool>> visited(n, vector<bool>(n,false));
        queue<pair<int, int>> q;
        q.push({0,0});
        visited[0][0] = true;
        vector<pair<int, int>> directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        while (!q.empty()) {
            auto [r,c] = q.front();
            q.pop();            
            if (r== n-1 && c == n-1) return true;            
            for (auto& dir : directions) {
                int nr = r + dir.first;
                int nc = c + dir.second;                
                if (nr>=0&&nr<n&&nc>=0&&nc<n && !visited[nr][nc]&&grid[nr][nc]<=t){
                    visited[nr][nc] = true;
                    q.push({nr,nc});
                }
            }
        }
        return false;
    }
};
