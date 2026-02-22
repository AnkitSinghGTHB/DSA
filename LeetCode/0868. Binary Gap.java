class Solution {
    public int binaryGap(int n) {
        int prev = -1;
        int maxDist = 0;
        int i = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                if (prev != -1) {
                    maxDist = Math.max(maxDist, i - prev);
                }
                prev = i;
            }
            i++;
            n >>= 1;
        }
        return maxDist;
    }
}
