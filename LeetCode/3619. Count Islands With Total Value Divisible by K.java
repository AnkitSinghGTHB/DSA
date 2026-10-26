import java.util.*;
//i used deepseek, i ctually didnt spend time understnding this
class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;

                long sum = 0;
                Deque<int[]> stack = new ArrayDeque<>();
                stack.push(new int[]{i, j});

                // Mark start as visited after reading it inside loop
                while (!stack.isEmpty()) {
                    int[] cur = stack.pop();
                    int r = cur[0], c = cur[1];

                    if (grid[r][c] == 0) continue;

                    sum += grid[r][c];
                    grid[r][c] = 0; // mark visited

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] != 0) {
                            stack.push(new int[]{nr, nc});
                        }
                    }
                }

                if (sum % k == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}
