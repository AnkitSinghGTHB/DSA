class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;
        long sumLeft = 0, sumRight = 0;
        int qLeft = 0, qRight = 0;

        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);
            if (c == '?') qLeft++;
            else sumLeft += c - '0';
        }

        for (int i = half; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') qRight++;
            else sumRight += c - '0';
        }

        long cur = sumLeft - sumRight;       // known difference
        int diff = qLeft - qRight;           // difference in '?' counts

        if (diff == 0) {
            // Equal number of '?' on both sides.
            // Bob can mirror Alice's moves, forcing the final difference to be cur.
            return cur != 0;
        } else if (diff > 0) {
            // More '?' on the left.
            int d = diff;
            if (d % 2 == 1) {
                // Odd extra moves → Alice has the last move and can avoid equality.
                return true;
            } else {
                // Even extra moves → Bob can force the sum of extra digits to 9*(d/2).
                // Bob wins if cur == -9*(d/2), otherwise Alice wins.
                return cur != -9L * (d / 2);
            }
        } else {
            // More '?' on the right.
            int d = -diff;
            if (d % 2 == 1) {
                return true;
            } else {
                // Bob can force the sum of extra digits to 9*(d/2).
                // Bob wins if cur == 9*(d/2), otherwise Alice wins.
                return cur != 9L * (d / 2);
            }
        }
    }
}
