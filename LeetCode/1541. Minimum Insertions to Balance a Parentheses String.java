class Solution {
    public int minInsertions(String s) {//finally a no stack soln
        int ans = 0;
        int open = 0; // unmatched '(' that still need two ')'
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
                i++;
            } else { // c == ')'
                // check if next char is also ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // We have a closing unit "))"
                    if (open > 0) {
                        open--;          // matches one '('
                    } else {
                        ans++;           // need to insert '(' before this pair
                    }
                    i += 2;              // skip both ')'
                } else {
                    // Single ')'
                    if (open > 0) {
                        open--;          // matches one '('
                        ans++;           // but we need one more ')' to make "))"
                    } else {
                        ans += 2;        // need '(' before and ')' after -> "())"
                    }
                    i++;
                }
            }
        }
        ans += open * 2; // each remaining '(' needs two ')'
        return ans;
    }
}
