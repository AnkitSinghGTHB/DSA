class Solution {
    public int mirrorDistance(int n) {
        //biggest bruh moments of all time
        int rev = 0;
        int temp = n;
        while (temp > 0) {
            rev = rev * 10 + (temp % 10);
            temp /= 10;
        }
        return Math.abs(n - rev);
    }
}
