class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int[] arr = new int[m * n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[idx++] = grid[i][j];
            }
        }
        //check modulo consistency
        int rem = arr[0] % x;
        for (int v : arr) {
            if (v % x != rem) return -1;
        }
        Arrays.sort(arr);
        int median = arr[arr.length / 2];
        long ops = 0;
        for (int v : arr) {
            ops += Math.abs(v - median) / x;
        }
        return (int) ops;
    }
}
