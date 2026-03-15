class Fancy {
    private static final int MOD = 1_000_000_007;
    private List<Long> originals;          // stored values v_i
    private long mul;                       // global multiplier
    private long add;                        // global additive offset

    public Fancy() {
        originals = new ArrayList<>();
        mul = 1;
        add = 0;
    }

    public void append(int val) {
        // v = (val - add) * inv(mul) % MOD
        long x = (val - add + MOD) % MOD;
        long inv = modPow(mul, MOD - 2);
        long v = x * inv % MOD;
        originals.add(v);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = mul * m % MOD;
        add = add * m % MOD;
    }

    public int getIndex(int idx) {
        if (idx < 0 || idx >= originals.size()) {
            return -1;
        }
        long v = originals.get(idx);
        long res = (v * mul % MOD + add) % MOD;
        return (int) res;
    }

    // fast exponentiation a^e mod MOD
    private long modPow(long a, long e) {
        long res = 1;
        a %= MOD;
        while (e > 0) {
            if ((e & 1) == 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            e >>= 1;
        }
        return res;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
