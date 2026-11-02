class Solution {
    public int minMoves2(int[] nums) {
        //so we are going to get best moves if we put the middle element as the comparision point
        //so from middle we increment or decrement
        //otherwise ig we would need n^2 passes
        Arrays.sort(nums);
        int t = nums[nums.length /2];
        int c =0;
        for (int i:nums){
            c+= Math.abs(i-t);
        }
        return c;
    }
}
