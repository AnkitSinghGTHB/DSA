class Solution {
    public int longestSubsequence(int[] nums) {
        //okay now this sounds fun
        //shall we bruteforce or something?
        //first we xor all of it (if !0 then return n)
        //then we remove one by one (n-1)
        //xor(allxorred, one we wana remove) = n-1xors
        //check if that is !0 then good, else move to the next
        //maybe there is some mathematical optimization?
        //catch: if any element is 0 we need to exclude it
        //okay so i was overcomplicating it, but was nearly right
        int xor = 0;
        boolean hasNonZero = false;
        for (int x : nums) {
            xor ^= x;
            if (x != 0) {
                hasNonZero = true;
            }
        }
        if (xor != 0) {
            return nums.length;
        }
        if (hasNonZero) {
            return nums.length - 1;
        }
        return 0;
    }
}
