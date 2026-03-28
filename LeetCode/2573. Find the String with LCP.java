class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        //check diagonal and symmetry
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] != lcp[j][i]) return "";
            }
        }

        char[] s = new char[n];

        //greedy construction of the string
        for (int i = 0; i < n; i++) {
            if (s[i] == 0) {
                //find forced character from previous positions with lcp[i][j] > 0
                char forced = 0;
                for (int j = 0; j < i; j++) {
                    if (lcp[i][j] > 0) {
                        if (forced == 0) forced = s[j];
                        else if (forced != s[j]) return ""; // conflict
                    }
                }

                if (forced != 0) {
                    //ensure that no position j with lcp[i][j] == 0 has the same character
                    for (int j = 0; j < i; j++) {
                        if (lcp[i][j] == 0 && s[j] == forced) return "";
                    }
                    s[i] = forced;
                }
                else {
                    //choose the smallest character not used by any j with lcp[i][j] == 0
                    boolean[] used = new boolean[26];
                    for (int j = 0; j < i; j++) {
                        if (lcp[i][j] == 0) {
                            used[s[j] - 'a'] = true;
                        }
                    }
                    char c = 'a';
                    while (c <= 'z' && used[c - 'a']) c++;
                    if (c > 'z') return ""; // no available character
                    s[i] = c;
                }
            }
        }

        //compute the actual LCP matrix of the constructed string
        int[][] actual = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s[i] == s[j]) {
                    if (i + 1 < n && j + 1 < n) actual[i][j] = 1 + actual[i + 1][j + 1];
                    else actual[i][j] = 1;
                } 
                else {
                    actual[i][j] = 0;
                }
            }
        }

        //verify that the computed LCP matches the input
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (actual[i][j] != lcp[i][j]) return "";
            }
        }

        return new String(s);
    }
}
