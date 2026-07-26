class Solution {
    public int maximumProduct(int[] nums) {
        //so yesterday's question but upped 1 time?
        //oh btw they gave array
        //oh damn, i didnt realize that if i multiply two big negatives, then i can get big positive
        //that changes alot of things then
        //okay, nobody told me that sorting is acceptable here
        Arrays.sort(nums);
        int n = nums.length;
        int candidate1 = nums[n-1] * nums[n-2] * nums[n-3];
        int candidate2 = nums[0] * nums[1] * nums[n-1];
        return Math.max(candidate1, candidate2);
    }
}
