class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String t = s + s;
        int len = 2 * n;
        int[] A = new int[len];
        int[] B = new int[len];
        for (int i = 0; i < len; i++) {
            char c = t.charAt(i);
            //pattern starting with 0 at global index 0
            char p0 = (i % 2 == 0) ? '0' : '1';
            A[i] = (c == p0) ? 0 : 1;
            //pattern starting with 1 at global index 0
            char p1 = (i % 2 == 0) ? '1' : '0';
            B[i] = (c == p1) ? 0 : 1;
        }
        int[] prefA = new int[len + 1];
        int[] prefB = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefA[i + 1] = prefA[i] + A[i];
            prefB[i + 1] = prefB[i] + B[i];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sumA = prefA[i + n] - prefA[i];
            int sumB = prefB[i + n] - prefB[i];
            int cand0 = (i % 2 == 0) ? sumA : sumB;//local pattern starting with 0
            int cand1 = (i % 2 == 0) ? sumB : sumA;//local pattern starting with 1
            ans = Math.min(ans, Math.min(cand0, cand1));
        }
        return ans;
    }
}
