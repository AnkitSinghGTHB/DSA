class Solution {
    private static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = (int) Math.sqrt(n) + 1; // threshold for sqrt decomposition

        //separate queries: large k (k > B) and small k (k <= B)
        List<int[]> large = new ArrayList<>();
        // small[k][r] stores list of updates for residue r modulo k
        List<int[]>[][] small = new ArrayList[B + 1][];
        for (int k = 1; k <= B; k++) {
            small[k] = new ArrayList[k];
            for (int r = 0; r < k; r++) {
                small[k][r] = new ArrayList<>();
            }
        }

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k > B) {
                large.add(new int[]{l, r, k, v});
            } else {
                int residue = l % k;
                int tStart = (l - residue) / k;
                int tEnd = (r - residue) / k;
                small[k][residue].add(new int[]{tStart, tEnd, v});
            }
        }

        //multiplier for each index, initially 1
        int[] mult = new int[n];
        Arrays.fill(mult, 1);

        //process large k queries directly (each touches at most n/k <= sqrt(n) indices)
        for (int[] q : large) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int i = l; i <= r; i += k) {
                mult[i] = (int) ((long) mult[i] * v % MOD);
            }
        }

        //process small k queries using difference arrays per residue class
        for (int k = 1; k <= B; k++) {
            for (int residue = 0; residue < k; residue++) {
                List<int[]> updates = small[k][residue];
                if (updates.isEmpty()) continue;

                //number of indices with this residue: (n - residue + k - 1) / k
                int M = (n - residue + k - 1) / k;
                int[] diff = new int[M + 1];
                Arrays.fill(diff, 1);

                for (int[] upd : updates) {
                    int ts = upd[0], te = upd[1], v = upd[2];
                    diff[ts] = (int) ((long) diff[ts] * v % MOD);
                    if (te + 1 < M) {
                        diff[te + 1] = (int) ((long) diff[te + 1] * modInv(v) % MOD);
                    }
                }

                int cur = 1;
                for (int t = 0; t < M; t++) {
                    cur = (int) ((long) cur * diff[t] % MOD);
                    int idx = residue + t * k;
                    mult[idx] = (int) ((long) mult[idx] * cur % MOD);
                }
            }
        }

        //apply multipliers to nums and compute XOR
        int xor = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = (int) ((long) nums[i] * mult[i] % MOD);
            xor ^= nums[i];
        }
        return xor;
    }

    private int modInv(int a) {
        return pow(a, MOD - 2);
    }

    private int pow(int a, int e) {
        long res = 1;
        long base = a;
        while (e > 0) {
            if ((e & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            e >>= 1;
        }
        return (int) res;
    }
}
