class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int layers = Math.min(m, n) / 2; // number of layers (all rings)

        for (int layer = 0; layer < layers; layer++) {
            int top = layer;
            int bottom = m - 1 - layer;
            int left = layer;
            int right = n - 1 - layer;

            // collect elements in clockwise order
            List<Integer> clockwise = new ArrayList<>();
            // top row left to right
            for (int col = left; col <= right; col++) clockwise.add(grid[top][col]);
            // right column top+1 to bottom
            for (int row = top + 1; row <= bottom; row++) clockwise.add(grid[row][right]);
            // bottom row right-1 to left
            for (int col = right - 1; col >= left; col--) clockwise.add(grid[bottom][col]);
            // left column bottom-1 to top+1
            for (int row = bottom - 1; row > top; row--) clockwise.add(grid[row][left]);

            int size = clockwise.size();
            int shift = k % size;
            if (shift == 0) continue;

            // left rotate by 'shift'
            List<Integer> rotated = new ArrayList<>();
            for (int i = shift; i < size; i++) rotated.add(clockwise.get(i));
            for (int i = 0; i < shift; i++) rotated.add(clockwise.get(i));

            // write back in clockwise order
            int idx = 0;
            // top row
            for (int col = left; col <= right; col++) grid[top][col] = rotated.get(idx++);
            // right column
            for (int row = top + 1; row <= bottom; row++) grid[row][right] = rotated.get(idx++);
            // bottom row
            for (int col = right - 1; col >= left; col--) grid[bottom][col] = rotated.get(idx++);
            // left column
            for (int row = bottom - 1; row > top; row--) grid[row][left] = rotated.get(idx++);
        }
        return grid;
    }
}
