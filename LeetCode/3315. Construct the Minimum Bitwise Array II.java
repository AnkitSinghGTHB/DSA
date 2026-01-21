class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];        
        for (int i = 0; i < n; i++) {
            int p = nums.get(i);            
            if ((p & 1) == 0) {//even prime (2) has no solution
                ans[i] = -1;
                continue;
            }            
            int r = 0;//count number of trailing 1s in binary representation of p
            int temp = p;
            while ((temp & 1) == 1) {
                r++;
                temp >>= 1;
            }            
            //compute the smallest x such that x | (x+1) = p
            int x = (p & ~((1 << r) - 1)) | ((1 << (r - 1)) - 1);
            ans[i] = x;
        }        
        return ans;
    }
}
