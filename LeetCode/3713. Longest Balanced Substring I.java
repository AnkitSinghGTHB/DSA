class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int[][] prefix = new int[26][n + 1];
        // build prefix sums for each character
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            for (int c = 0; c < 26; c++) {
                prefix[c][i + 1] = prefix[c][i] + (c == ch ? 1 : 0);
            }
        }
        int best = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int len = j - i + 1;
                if (len <= best) continue; // no need to check if already smaller
                int common = -1;
                boolean ok = true;
                for (int c = 0; c < 26; c++) {
                    int cnt = prefix[c][j + 1] - prefix[c][i];
                    if (cnt == 0) continue;
                    if (common == -1) common = cnt;
                    else if (cnt != common) {
                        ok = false;
                        break;
                    }
                }
                if (ok) best = len;
            }
        }
        return best;
    }
}
