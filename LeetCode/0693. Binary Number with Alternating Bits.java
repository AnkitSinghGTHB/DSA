class Solution {
    public boolean hasAlternatingBits(int n) {
        int x = n ^ (n >> 1);
        //if bits alternate, x will be all ones (like 111...)
        //check if x is of the form 2^k - 1
        return (x & (x + 1)) == 0;
    }
}
