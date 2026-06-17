class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        char[] ops = s.toCharArray();
        long[] lenAfter = new long[n];
        long curLen = 0;
        //compute length after each operation
        for (int i = 0; i < n; i++) {
            char c = ops[i];
            if (c >= 'a' && c <= 'z') {
                curLen++;
            } else if (c == '*') {
                if (curLen > 0) curLen--;
            } else if (c == '#') {
                curLen *= 2;
            } else if (c == '%') {
                //ength unchanged
            }
            lenAfter[i] = curLen;
        }
        
        long totalLen = curLen;
        if (k >= totalLen) return '.';
        
        //backtrack from the end to find the character
        for (int i = n - 1; i >= 0; i--) {
            char c = ops[i];
            long before = (i == 0 ? 0 : lenAfter[i - 1]);
            long after = lenAfter[i];
            
            if (c >= 'a' && c <= 'z') {
                if (k == before) {
                    return c;
                }
                //otherwise keep searching in earlier part
            } else if (c == '*') {
                //removal doesn't change positions of earlier characters
                //just continue
            } else if (c == '#') {
                //duplicate: k falls into one of the two copies
                k = k % before;
            } else if (c == '%') {
                //reverse: mirror the position
                k = before - 1 - k;
            }
        }
        return '.';
    }
}
