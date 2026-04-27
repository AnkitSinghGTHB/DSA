class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //left, right, up, down
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        //opposite direction indices
        int[] opposite = {1, 0, 3, 2};
        //allowed directions for each street type (1-indexed)
        boolean[][] allowed = new boolean[7][4];
        //type 1: left and right
        allowed[1][0] = allowed[1][1] = true;
        //type 2: up and down
        allowed[2][2] = allowed[2][3] = true;
        //type 3: left and down
        allowed[3][0] = allowed[3][3] = true;
        //type 4: right and down
        allowed[4][1] = allowed[4][3] = true;
        //type 5: left and up
        allowed[5][0] = allowed[5][2] = true;
        // type 6: right and up
        allowed[6][1] = allowed[6][2] = true;

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];
            int type = grid[r][c];
            for (int d = 0; d < 4; d++) {
                if (allowed[type][d]) {
                    int nr = r + dirs[d][0];
                    int nc = c + dirs[d][1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                        int opp = opposite[d];
                        if (allowed[grid[nr][nc]][opp]) {
                            visited[nr][nc] = true;
                            queue.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        return visited[m - 1][n - 1];
    }
}
