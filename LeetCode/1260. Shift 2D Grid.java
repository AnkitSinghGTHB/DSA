class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        //is this sliding windwwo?
        //i was thinking
        //lets go k times ahead in the matrix position then swap the numbers nxm -k times with increment
        //or is there something we can do better?
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k = k % total; // effective shift

        // flatten the grid into a 1D array
        int[] flat = new int[total];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                flat[i * n + j] = grid[i][j];
            }
        }

        // create result grid
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // new index = (old index + k) % total
                int oldIdx = (i * n + j - k + total) % total;
                row.add(flat[oldIdx]);
            }
            result.add(row);
        }
        return result;
    }
}
