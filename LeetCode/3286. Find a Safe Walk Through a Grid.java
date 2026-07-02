class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        //looks easy but tought at the same time, some bsf question ig
        int m = grid.size();
        int n = grid.get(0).size();
        
        // dist[i][j] = minimum number of unsafe cells (1s) encountered
        // when reaching cell (i,j), including the cost of (i,j) itself.
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        
        Deque<int[]> dq = new ArrayDeque<>();
        // Start cell cost is grid[0][0] (unsafe cell counts)
        dist[0][0] = grid.get(0).get(0);
        dq.offerFirst(new int[]{0, 0});
        
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int r = cur[0], c = cur[1];
            if (r == m - 1 && c == n - 1) break; // early exit (still need to process? we can just continue)
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                
                int cost = grid.get(nr).get(nc); // 0 or 1
                int nd = dist[r][c] + cost;
                if (nd < dist[nr][nc]) {
                    dist[nr][nc] = nd;
                    if (cost == 0) dq.offerFirst(new int[]{nr, nc});
                    else dq.offerLast(new int[]{nr, nc});
                }
            }
        }
        
        // Need health - dist[m-1][n-1] >= 1  =>  dist <= health - 1
        return dist[m - 1][n - 1] <= health - 1;
        }
}
