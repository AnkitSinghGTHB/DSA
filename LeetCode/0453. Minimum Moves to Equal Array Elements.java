class Solution {
    public int minMoves(int[] nums) {
        //how about, i get the min and reduce all to that value while counting
        
        int minx = nums[0];
        for (int i:nums){
            if (i<minx){
                minx=i;
            }
        }
        int c =0;
        for (int i:nums){
            c+=i-minx;
        }
        return c;
    }
}
