class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int L = n + m - 1;
        char[] word = new char[L]; //0 means unknown

        //fix characters forced by 'T' constraints
        for (int i = 0; i < n; ++i) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; ++j) {
                    int pos = i + j;
                    char c = str2.charAt(j);
                    if (word[pos] == 0) {
                        word[pos] = c;
                    } else if (word[pos] != c) {
                        return "";
                    }
                }
            }
        }

        //KMP failure function for str2
        char[] pat = str2.toCharArray();
        int[] pi = new int[m];
        for (int i = 1; i < m; ++i) {
            int j = pi[i - 1];
            while (j > 0 && pat[i] != pat[j]) j = pi[j - 1];
            if (pat[i] == pat[j]) ++j;
            pi[i] = j;
        }

        //build automaton transitions: state 0..m
        int[][] next = new int[m + 1][26];
        for (int state = 0; state <= m; ++state) {
            for (int c = 0; c < 26; ++c) {
                int cur = state;
                if (cur == m) cur = pi[m - 1];
                while (cur > 0 && c != pat[cur] - 'a') cur = pi[cur - 1];
                if (c == pat[cur] - 'a') next[state][c] = cur + 1;
                else next[state][c] = 0;
            }
        }

        //forbid positions: end of 'F' windows
        boolean[] forbid = new boolean[L];
        for (int i = 0; i < n; ++i) {
            if (str1.charAt(i) == 'F') {
                forbid[i + m - 1] = true;
            }
        }

        //dp[pos][state] = can we fill from pos to end?
        boolean[][] dp = new boolean[L + 1][m + 1];
        for (int state = 0; state <= m; ++state) {
            dp[L][state] = true;
        }
        for (int pos = L - 1; pos >= 0; --pos) {
            for (int state = 0; state <= m; ++state) {
                if (word[pos] != 0) {
                    int c = word[pos] - 'a';
                    int ns = next[state][c];
                    if (forbid[pos] && ns == m) {
                        dp[pos][state] = false;
                    } 
                    else {
                        dp[pos][state] = dp[pos + 1][ns];
                    }
                } 
                else {
                    boolean ok = false;
                    for (int c = 0; c < 26; ++c) {
                        int ns = next[state][c];
                        if (forbid[pos] && ns == m) continue;
                        if (dp[pos + 1][ns]) {
                            ok = true;
                            break;
                        }
                    }
                    dp[pos][state] = ok;
                }
            }
        }

        if (!dp[0][0]) return "";

        //greedy construction
        StringBuilder sb = new StringBuilder(L);
        int state = 0;
        for (int pos = 0; pos < L; ++pos) {
            if (word[pos] != 0) {
                char c = word[pos];
                sb.append(c);
                state = next[state][c - 'a'];
            } 
            else {
                for (int c = 0; c < 26; ++c) {
                    int ns = next[state][c];
                    if (forbid[pos] && ns == m) continue;
                    if (dp[pos + 1][ns]) {
                        sb.append((char) ('a' + c));
                        state = ns;
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }
}
