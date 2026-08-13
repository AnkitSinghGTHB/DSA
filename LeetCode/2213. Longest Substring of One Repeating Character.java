//by deepseek
class Solution {
    // Segment tree arrays
    private int[] leftChar, rightChar, pref, suff, best, length;
    private int n;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        int m = 4 * n + 5;
        leftChar = new int[m];
        rightChar = new int[m];
        pref = new int[m];
        suff = new int[m];
        best = new int[m];
        length = new int[m];
        build(1, 0, n - 1, s);

        int k = queryCharacters.length();
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            update(1, 0, n - 1, idx, ch - 'a');
            ans[i] = best[1];
        }
        return ans;
    }

    private void build(int node, int l, int r, String s) {
        if (l == r) {
            int c = s.charAt(l) - 'a';
            leftChar[node] = rightChar[node] = c;
            pref[node] = suff[node] = best[node] = 1;
            length[node] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);
        merge(node);
        length[node] = length[node * 2] + length[node * 2 + 1];
    }

    private void update(int node, int l, int r, int idx, int newChar) {
        if (l == r) {
            leftChar[node] = rightChar[node] = newChar;
            // length already 1
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(node * 2, l, mid, idx, newChar);
        else update(node * 2 + 1, mid + 1, r, idx, newChar);
        merge(node);
    }

    private void merge(int node) {
        int left = node * 2;
        int right = node * 2 + 1;
        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

        // prefix
        pref[node] = pref[left];
        if (pref[left] == length[left] && rightChar[left] == leftChar[right]) {
            pref[node] = length[left] + pref[right];
        }

        // suffix
        suff[node] = suff[right];
        if (suff[right] == length[right] && rightChar[left] == leftChar[right]) {
            suff[node] = length[right] + suff[left];
        }

        // best
        best[node] = Math.max(best[left], best[right]);
        if (rightChar[left] == leftChar[right]) {
            best[node] = Math.max(best[node], suff[left] + pref[right]);
        }

        length[node] = length[left] + length[right];
    }
}
