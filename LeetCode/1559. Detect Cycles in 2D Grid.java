class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    //use a stack for DFS: store (x, y, parentX, parentY)
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{i, j, -1, -1});
                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        int x = cur[0], y = cur[1], px = cur[2], py = cur[3];
                        if (visited[x][y]) {
                            //already visited means we found a cycle (since we only push when not visited initially)
                            return true;
                        }
                        visited[x][y] = true;
                        for (int[] d : dirs) {
                            int nx = x + d[0], ny = y + d[1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == grid[x][y]) {
                                if (nx == px && ny == py) continue; //skip parent
                                stack.push(new int[]{nx, ny, x, y});
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
