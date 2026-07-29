class Solution {
    public String smallestPalindrome(String s, int k) {
        //can i cry?
        //i didnt write this soln
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        int[] half = new int[26];
        int totalHalf = n / 2;
        int middle = -1;
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            if (freq[i] % 2 == 1) middle = i;
        }

        final int CAP = 1_000_001; // larger than any k (k <= 1e6)

        // Precompute combinations up to totalHalf with cap
        int[][] comb = new int[totalHalf + 1][];
        for (int i = 0; i <= totalHalf; i++) {
            comb[i] = new int[i + 1];
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                int val = comb[i - 1][j - 1] + comb[i - 1][j];
                comb[i][j] = Math.min(val, CAP);
            }
        }

        // Count distinct permutations of the multiset `half`
        java.util.function.ToIntFunction<int[]> countPerms = (cnt) -> {
            long ways = 1;
            int rem = 0;
            for (int v : cnt) rem += v;
            for (int i = 0; i < 26; i++) {
                int c = cnt[i];
                if (c == 0) continue;
                ways = ways * comb[rem][c];
                if (ways >= CAP) return CAP;
                rem -= c;
            }
            return (int) ways;
        };

        if (countPerms.applyAsInt(half) < k) return "";

        StringBuilder firstHalf = new StringBuilder();
        for (int pos = 0; pos < totalHalf; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                int ways = countPerms.applyAsInt(half);
                if (ways >= k) {
                    firstHalf.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        String left = firstHalf.toString();
        String right = new StringBuilder(left).reverse().toString();

        if (middle == -1) return left + right;
        return left + (char) ('a' + middle) + right;
    }
}
