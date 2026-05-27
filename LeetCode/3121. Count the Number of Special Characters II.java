class Solution {
    public int numberOfSpecialChars(String word) {
        int n = word.length();
        int[] firstUpper = new int[26];
        int[] lastLower = new int[26];
        //init firstUpper to large value, lastLower to -1
        for (int i = 0; i < 26; i++) {
            firstUpper[i] = n;
            lastLower[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                int idx = c - 'a';
                lastLower[idx] = i;
            } else {
                int idx = c - 'A';
                if (firstUpper[idx] == n) firstUpper[idx] = i;
            }
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (lastLower[i] != -1 && firstUpper[i] != n && lastLower[i] < firstUpper[i]) {
                count++;
            }
        }
        return count;
    }
}
