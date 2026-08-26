class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        //i took help frm deepseek
        int n = s.length();
        String ans = null;
        for (int i = 0; i < n; i++) {
            int ones = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') ones++;
                if (ones == k) {
                    String sub = s.substring(i, j + 1);
                    if (ans == null || sub.length() < ans.length() ||
                        (sub.length() == ans.length() && sub.compareTo(ans) < 0)) {
                        ans = sub;
                    }
                } else if (ones > k) {
                    break; // since ones only increase, no need to extend further
                }
            }
        }
        return ans == null ? "" : ans;
    }
}
