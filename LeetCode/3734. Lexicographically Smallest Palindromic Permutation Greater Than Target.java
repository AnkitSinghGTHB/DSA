//i used deepseek to solve it, bcoz i was getting wrong answers
class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        // Check if palindrome possible
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                oddCount++;
                oddChar = i;
            }
        }
        if (oddCount > 1) return "";

        int h = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) halfCount[i] = freq[i] / 2;

        String mid = (n % 2 == 1) ? String.valueOf((char) ('a' + oddChar)) : "";
        char[] T = target.toCharArray();

        // Quick check: if the largest palindrome <= target, impossible
        StringBuilder maxFirst = new StringBuilder();
        for (int c = 25; c >= 0; c--) {
            for (int j = 0; j < halfCount[c]; j++) maxFirst.append((char) ('a' + c));
        }
        String maxPal = maxFirst.toString() + mid + new StringBuilder(maxFirst.toString()).reverse().toString();
        if (maxPal.compareTo(target) <= 0) return "";

        // DFS to find smallest > target
        StringBuilder prefix = new StringBuilder();
        String ans = dfs(0, halfCount, prefix, false, h, mid, T);
        return ans == null ? "" : ans;
    }

    private String dfs(int idx, int[] rem, StringBuilder prefix, boolean greater,
                       int h, String mid, char[] T) {
        if (idx == h) {
            // Build full palindrome
            String first = prefix.toString();
            String full = first + mid + new StringBuilder(first).reverse().toString();
            if (full.compareTo(new String(T)) > 0) return full;
            return null;
        }

        // If already greater, fill rest with smallest possible
        if (greater) {
            StringBuilder rest = new StringBuilder();
            for (int c = 0; c < 26; c++) {
                while (rem[c] > 0) {
                    rest.append((char) ('a' + c));
                    rem[c]--;
                }
            }
            String first = prefix.toString() + rest.toString();
            String full = first + mid + new StringBuilder(first).reverse().toString();
            return full; // must be > target because prefix already made it greater
        }

        // Try characters from 'a' to 'z'
        for (int c = 0; c < 26; c++) {
            if (rem[c] == 0) continue;
            if (c < T[idx] - 'a') continue; // would make prefix smaller, impossible

            prefix.append((char) ('a' + c));
            rem[c]--;
            boolean newGreater = greater || (c > T[idx] - 'a');
            String res = dfs(idx + 1, rem, prefix, newGreater, h, mid, T);
            if (res != null) return res;
            // backtrack
            rem[c]++;
            prefix.deleteCharAt(prefix.length() - 1);
        }
        return null;
    }
}
