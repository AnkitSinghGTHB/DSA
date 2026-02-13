//AI generated soln, i wasnt able to solve properly
class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int ans = 0;

        // ----- case 1: only one distinct character (longest run) -----
        int run = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                run++;
            } else {
                ans = Math.max(ans, run);
                run = 1;
            }
        }
        ans = Math.max(ans, run);

        // ----- case 2: exactly two distinct characters -----
        char[] chars = {'a', 'b', 'c'};
        // all three pairs
        for (int p1 = 0; p1 < 3; p1++) {
            for (int p2 = p1 + 1; p2 < 3; p2++) {
                char c1 = chars[p1];
                char c2 = chars[p2];
                char c3 = (char) ('a' + (3 - p1 - p2)); // the third character
                ans = Math.max(ans, longestTwoChars(s, c1, c2, c3));
            }
        }

        // ----- case 3: all three characters appear with equal counts -----
        ans = Math.max(ans, longestThreeChars(s));

        return ans;
    }

    // Longest substring that contains only c1 and c2 (no c3)
    // and has count(c1) == count(c2)
    private int longestTwoChars(String s, char c1, char c2, char c3) {
        int n = s.length();
        int maxLen = 0;
        int i = 0;
        while (i < n) {
            // skip to next segment that does not contain c3
            while (i < n && s.charAt(i) == c3) i++;
            if (i >= n) break;
            int start = i;
            HashMap<Integer, Integer> first = new HashMap<>();
            int diff = 0;
            // prefix before segment: diff = 0 at index start-1
            first.put(0, start - 1);
            while (i < n && s.charAt(i) != c3) {
                char ch = s.charAt(i);
                if (ch == c1) diff++;
                else if (ch == c2) diff--;
                // ch must be c1 or c2, otherwise already filtered
                if (first.containsKey(diff)) {
                    int prev = first.get(diff);
                    maxLen = Math.max(maxLen, i - prev);
                } else {
                    first.put(diff, i);
                }
                i++;
            }
        }
        return maxLen;
    }

    // Longest substring where counts of a, b, c are all equal
    private int longestThreeChars(String s) {
        int n = s.length();
        int[] cntA = new int[n + 1];
        int[] cntB = new int[n + 1];
        int[] cntC = new int[n + 1];
        for (int i = 0; i < n; i++) {
            cntA[i + 1] = cntA[i] + (s.charAt(i) == 'a' ? 1 : 0);
            cntB[i + 1] = cntB[i] + (s.charAt(i) == 'b' ? 1 : 0);
            cntC[i + 1] = cntC[i] + (s.charAt(i) == 'c' ? 1 : 0);
        }

        int offset = n; // range of diff is [-n, n]
        long base = 2L * offset + 1;
        HashMap<Long, Integer> first = new HashMap<>();
        // prefix at index 0
        first.put((0L + offset) * base + (0L + offset), 0);

        int maxLen = 0;
        for (int i = 1; i <= n; i++) {
            int d1 = cntB[i] - cntA[i];
            int d2 = cntC[i] - cntA[i];
            long key = (d1 + offset) * base + (d2 + offset);
            if (first.containsKey(key)) {
                int prev = first.get(key);
                maxLen = Math.max(maxLen, i - prev);
            } 
            else {
                first.put(key, i);
            }
        }
        return maxLen;
    }
}
