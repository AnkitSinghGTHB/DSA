class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int maxLen = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                // Case 1: s[i-1] == '(' → "...()"
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } 
                // Case 2: s[i-1] == ')' → "...))"
                else {
                    int prevLen = dp[i - 1];
                    // Check if the character before the previous valid substring is '('
                    if (i - prevLen - 1 >= 0 && s.charAt(i - prevLen - 1) == '(') {
                        dp[i] = prevLen + 2 + (i - prevLen - 2 >= 0 ? dp[i - prevLen - 2] : 0);
                    }
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }
}
