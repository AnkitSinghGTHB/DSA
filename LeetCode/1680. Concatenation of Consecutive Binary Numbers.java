class Solution {
    public int concatenatedBinary(int n) {
        final long MOD = 1_000_000_007L;
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            int bits = 32-Integer.numberOfLeadingZeros(i);//number of bits in i
            ans = ((ans << bits) + i) % MOD;
        }
        return (int) ans;
    }
}
