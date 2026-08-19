class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map each row to a bitmask of reserved seats (bit 0 corresponds to seat 1)
        Map<Integer, Integer> rowMask = new HashMap<>();
        for (int[] r : reservedSeats) {
            int row = r[0], seat = r[1];
            rowMask.put(row, rowMask.getOrDefault(row, 0) | (1 << (seat - 1)));
        }

        // Rows without any reservation can always accommodate 2 groups.
        int ans = 2 * (n - rowMask.size());

        // Pre‑computed masks for the three possible blocks
        int leftBlock = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4); // seats 2,3,4,5
        int rightBlock = (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8); // seats 6,7,8,9
        int middleBlock = (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6); // seats 4,5,6,7

        for (int mask : rowMask.values()) {
            boolean leftFree = (mask & leftBlock) == 0;
            boolean rightFree = (mask & rightBlock) == 0;

            if (leftFree && rightFree) {
                ans += 2;                 // both left and right blocks are free
            } else if (leftFree || rightFree) {
                ans += 1;                 // only one side is free
            } else {
                boolean middleFree = (mask & middleBlock) == 0;
                if (middleFree) ans += 1; // only the middle block works
            }
        }

        return ans;
    }
}
