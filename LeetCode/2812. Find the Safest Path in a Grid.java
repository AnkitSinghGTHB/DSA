//i used deepseek to help me with the soln, my brain is slow rn
class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];
        Queue<int[]> q = new LinkedList<>();
        
        // Initialize distances and multi-source BFS queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                } else {
                    dist[i][j] = -1; // unvisited
                }
            }
        }
        
        // Multi-source BFS to compute nearest thief distance
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        
        // Binary search on the safeness factor
        int lo = 0, hi = 2 * n; // max possible distance
        int ans = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (canReach(n, dist, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
    
    private boolean canReach(int n, int[][] dist, int limit) {
        if (dist[0][0] < limit || dist[n-1][n-1] < limit) return false;
        
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];
            if (r == n-1 && c == n-1) return true;
            
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && dist[nr][nc] >= limit) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}
