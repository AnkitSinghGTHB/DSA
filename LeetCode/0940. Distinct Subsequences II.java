class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int[] end = new int[26];      // end[c] = number of distinct subsequences ending with char c
        int total = 0;                // total distinct non-empty subsequences so far

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            // New subsequences ending with ch: 
            // - all existing subsequences + ch (total)
            // - plus the single character ch (1)
            int newEnd = (1 + total) % MOD;

            // Update total: remove old count for ch, add new count
            total = (total - end[idx] + newEnd) % MOD;
            if (total < 0) total += MOD;

            end[idx] = newEnd;
        }

        return total;
    }
}
