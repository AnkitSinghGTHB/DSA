class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k;
        if (s.length() < k) return false;
        boolean[] seen = new boolean[need];
        int mask = need - 1;
        int val = 0;
        for (int i = 0; i < k; i++) {//compute first window
            val = (val << 1) | (s.charAt(i) - '0');
        }
        seen[val] = true;
        int count = 1;
        for (int i = k; i < s.length(); i++) {//slide the window
            val = ((val << 1) & mask) | (s.charAt(i) - '0');
            if (!seen[val]) {
                seen[val] = true;
                count++;
                if (count == need) return true;
            }
        }
        return count == need;
    }
}
