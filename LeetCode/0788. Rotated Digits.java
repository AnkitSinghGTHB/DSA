class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) count++;
        }
        return count;
    }
    
    private boolean isGood(int x) {
        boolean changed = false;
        while (x > 0) {
            int d = x % 10;
            if (d == 3 || d == 4 || d == 7) return false; // invalid digit
            if (d == 2 || d == 5 || d == 6 || d == 9) changed = true;
            x /= 10;
        }
        return changed;
    }
}
