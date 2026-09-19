//i didnt know, had to tke help from deepseek

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, n);
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == n) first[c] = i;
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();
        // For each character, try to form a valid interval starting at its first occurrence
        for (int c = 0; c < 26; c++) {
            if (first[c] == n) continue;
            int start = first[c];
            boolean[] inSet = new boolean[26];
            inSet[c] = true;
            int L = first[c], R = last[c];
            boolean changed = true;
            while (changed) {
                changed = false;
                for (int i = L; i <= R; i++) {
                    int ch = s.charAt(i) - 'a';
                    if (!inSet[ch]) {
                        inSet[ch] = true;
                        L = Math.min(L, first[ch]);
                        R = Math.max(R, last[ch]);
                        changed = true;
                    }
                }
            }
            if (L == start) {
                intervals.add(new int[]{L, R});
            }
        }

        // Sort intervals by end position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        int m = intervals.size();
        int[] dpCount = new int[m + 1];
        int[] dpLen = new int[m + 1];
        boolean[] took = new boolean[m + 1];
        int[] choice = new int[m + 1];
        Arrays.fill(choice, -1);

        for (int i = 1; i <= m; i++) {
            int[] cur = intervals.get(i - 1);
            int l = cur[0], r = cur[1];
            int len = r - l + 1;

            // Option 1: skip this interval
            dpCount[i] = dpCount[i - 1];
            dpLen[i] = dpLen[i - 1];
            took[i] = false;
            choice[i] = -1;

            // Option 2: take this interval
            int p = -1;
            for (int j = i - 2; j >= 0; j--) {
                if (intervals.get(j)[1] < l) {
                    p = j;
                    break;
                }
            }
            int candCount = dpCount[p + 1] + 1;
            int candLen = dpLen[p + 1] + len;
            if (candCount > dpCount[i] || (candCount == dpCount[i] && candLen < dpLen[i])) {
                dpCount[i] = candCount;
                dpLen[i] = candLen;
                took[i] = true;
                choice[i] = p;
            }
        }

        // Reconstruct chosen intervals
        List<String> result = new ArrayList<>();
        int i = m;
        while (i > 0) {
            if (took[i]) {
                int[] interval = intervals.get(i - 1);
                result.add(s.substring(interval[0], interval[1] + 1));
                i = choice[i] + 1;
            } else {
                i--;
            }
        }
        return result;
    }
}
