class Solution {
    //i used deepseek to solve this
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char t = target.charAt(i);
            boolean placed = false;
            // try characters from t to 'z'
            for (char c = t; c <= 'z'; c++) {
                if (freq[c - 'a'] == 0) continue;
                if (c > t) {
                    // choose this larger character, then fill with smallest remaining
                    result.append(c);
                    freq[c - 'a']--;
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        while (freq[ch - 'a'] > 0) {
                            result.append(ch);
                            freq[ch - 'a']--;
                        }
                    }
                    return result.toString();
                } else { // c == t
                    // try to place t and see if we can still be greater later
                    freq[t - 'a']--;
                    int[] copy = freq.clone();
                    if (canBeGreater(copy, target, i + 1)) {
                        result.append(t);
                        placed = true;
                        break;
                    } else {
                        freq[t - 'a']++;
                    }
                }
            }
            if (!placed) {
                return "";
            }
        }

        // If we matched target exactly, need the next permutation of target
        // (because the multiset of target equals that of s)
        char[] arr = target.toCharArray();
        int p = n - 2;
        while (p >= 0 && arr[p] >= arr[p + 1]) p--;
        if (p < 0) return "";
        int q = n - 1;
        while (arr[q] <= arr[p]) q--;
        // swap
        char tmp = arr[p];
        arr[p] = arr[q];
        arr[q] = tmp;
        // reverse suffix
        int l = p + 1, r = n - 1;
        while (l < r) {
            tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            l++;
            r--;
        }
        return new String(arr);
    }

    // checks if we can arrange characters in freq to form a string > target[start:]
    private boolean canBeGreater(int[] freq, String target, int start) {
        int n = target.length();
        for (int i = start; i < n; i++) {
            char cur = target.charAt(i);
            // try to place a character larger than cur
            for (char c = (char) (cur + 1); c <= 'z'; c++) {
                if (freq[c - 'a'] > 0) return true;
            }
            // must match cur
            if (freq[cur - 'a'] == 0) return false;
            freq[cur - 'a']--;
        }
        return false;
    }
}
