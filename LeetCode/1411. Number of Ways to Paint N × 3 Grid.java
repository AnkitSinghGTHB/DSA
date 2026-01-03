class Solution {
    public int numOfWays(int n) {
        final int MOD = 1000000007;
        long aba = 6; // patterns of type ABA (two colors)
        long abc = 6; // patterns of type ABC (three colors)
        for (int i = 2; i <= n; i++) {
            long newAba = (3 * aba + 2 * abc) % MOD;
            long newAbc = (2 * aba + 2 * abc) % MOD;
            aba = newAba;
            abc = newAbc;
        }
        return (int)((aba + abc) % MOD);
    }
}
