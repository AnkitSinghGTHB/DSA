//used ds cuz i couldnt

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        char[][] grid = new char[m][n];
        int sr = -1, sc = -1;
        List<int[]> litterPos = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            grid[i] = classroom[i].toCharArray();
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    sr = i;
                    sc = j;
                } else if (grid[i][j] == 'L') {
                    litterPos.add(new int[]{i, j});
                }
            }
        }
        int L = litterPos.size();
        int fullMask = (1 << L) - 1;
        if (L == 0) return 0;

        // map each litter cell to its bit index
        int[][] litterBit = new int[m][n];
        for (int i = 0; i < L; i++) {
            int[] pos = litterPos.get(i);
            litterBit[pos[0]][pos[1]] = 1 << i;
        }

        int maxE = energy;
        int maskSize = 1 << L;
        int totalStates = m * n * (maxE + 1) * maskSize;
        byte[] visited = new byte[totalStates];

        Queue<Integer> queue = new ArrayDeque<>();
        int startCode = encode(sr, sc, maxE, 0, n, maxE, maskSize);
        visited[startCode] = 1;
        queue.offer(startCode);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int t = 0; t < size; t++) {
                int state = queue.poll();
                int mask = state % maskSize;
                int tmp = state / maskSize;
                int e = tmp % (maxE + 1);
                int cell = tmp / (maxE + 1);
                int r = cell / n;
                int c = cell % n;

                if (mask == fullMask) return steps;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    if (grid[nr][nc] == 'X') continue;
                    if (e == 0) continue; // cannot move

                    int ne = e - 1;
                    int newMask = mask;
                    if (grid[nr][nc] == 'L') {
                        newMask |= litterBit[nr][nc];
                    }
                    if (grid[nr][nc] == 'R') {
                        ne = maxE; // reset to full energy
                    }

                    int code = encode(nr, nc, ne, newMask, n, maxE, maskSize);
                    if (visited[code] == 0) {
                        visited[code] = 1;
                        queue.offer(code);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    private int encode(int r, int c, int e, int mask, int n, int maxE, int maskSize) {
        int cell = r * n + c;
        return ((cell * (maxE + 1) + e) * maskSize + mask);
    }
}
