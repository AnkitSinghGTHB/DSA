class Solution {
    public int uniqueXorTriplets(int[] nums) {
        //well i can simulate, but is there a mathematical optimization?
        int n = nums.length;
        //special cases for small n
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        //for n >= 3, answer is the smallest power of two strictly greater than n
        int p = 1;
        while (p <= n) {
            p <<= 1;
        }
        return p;
    }
}
