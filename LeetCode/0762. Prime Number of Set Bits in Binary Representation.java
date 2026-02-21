class Solution {
    public int countPrimeSetBits(int left, int right) {
        //primes up to 20 (since 10^6 < 2^20) <taken from constraints>
        boolean[] isPrime = new boolean[21];
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19};
        for (int p : primes) {
            isPrime[p] = true;
        }
        int count = 0;
        for (int i = left; i <= right; i++) {
            int bits = Integer.bitCount(i);
            if (isPrime[bits]) {
                count++;
            }
        }
        return count;
    }
}
