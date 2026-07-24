class Solution {
  //took help from deepseek again
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;  // 2^11, covers all values up to 1500
        boolean[] pairXor = new boolean[MAX];
        
        // All possible XORs of two numbers (including same)
        for (int x : nums) {
            for (int y : nums) {
                pairXor[x ^ y] = true;
            }
        }
        
        boolean[] tripleXor = new boolean[MAX];
        for (int x : nums) {
            for (int v = 0; v < MAX; v++) {
                if (pairXor[v]) {
                    tripleXor[x ^ v] = true;
                }
            }
        }
        
        int count = 0;
        for (boolean b : tripleXor) {
            if (b) count++;
        }
        return count;
    }
}
