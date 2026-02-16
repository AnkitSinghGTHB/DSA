class Solution {
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // extract the i-th bit (from LSB) and place it at the (31-i) position
            result = (result << 1) | ((n >>> i) & 1);
        }
        return result;
    }
}
