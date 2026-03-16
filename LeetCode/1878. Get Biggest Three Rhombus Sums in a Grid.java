class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Set<Integer> sums = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // maximum possible size for a rhombus centered at (i,j)
                int maxS = Math.min(i, m - 1 - i);
                maxS = Math.min(maxS, j);
                maxS = Math.min(maxS, n - 1 - j);

                for (int s = 0; s <= maxS; s++) {
                    int sum = 0;
                    if (s == 0) {
                        sum = grid[i][j];
                    } else {
                        // collect all cells with Manhattan distance exactly s from (i,j)
                        for (int r = i - s; r <= i + s; r++) {
                            int d = Math.abs(r - i);
                            int remaining = s - d;
                            if (remaining == 0) {
                                sum += grid[r][j];
                            } else {
                                sum += grid[r][j - remaining];
                                sum += grid[r][j + remaining];
                            }
                        }
                    }
                    sums.add(sum);
                }
            }
        }

        List<Integer> list = new ArrayList<>(sums);
        Collections.sort(list, Collections.reverseOrder());
        int k = Math.min(3, list.size());
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
