class Solution {
    public int bitwiseComplement(int n) {
        //handling the edge case where n is 0
        if (n == 0) return 1;        
        //thne find the smallest number with all bits set that is >= n
        //example, if n=5 (101), mask=7 (111)
        int mask = (Integer.highestOneBit(n) << 1) - 1;
        //xor with mask flips all bits within the number's bit-length
        return n ^ mask;
    }
}
