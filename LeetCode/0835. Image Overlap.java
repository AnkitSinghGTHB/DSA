class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) ones1.add(new int[]{i, j});
                if (img2[i][j] == 1) ones2.add(new int[]{i, j});
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;

        for (int[] a : ones1) {
            for (int[] b : ones2) {
                // Shift img1 by (dx, dy) so that a aligns with b
                int dx = b[0] - a[0];
                int dy = b[1] - a[1];

                // encode (dx, dy) into a single integer
                int key = dx * 100 + dy;

                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);
                ans = Math.max(ans, count);
            }
        }

        return ans;
    }
}
