class Solution {
    public int minimumCost(int[] nums) {
        int sum=nums[0];
        Arrays.sort(nums);
        if (sum==nums[0]){
            return sum+nums[1]+nums[2];
        }
        else if(sum==nums[1]){
            return sum+nums[0]+nums[2];
        }
        else{
            return sum+nums[0]+nums[1];
        }
    }
}
