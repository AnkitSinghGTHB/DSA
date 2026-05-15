class Solution {
    public int findMin(int[] nums) {
        int h = nums.length-1;
        int l= 0;
        while(l<h){
            int mid = (h+l)/2;
            if(nums[mid]>nums[h]){
                l=mid+1;
            }
            else{
                h=mid;
            }
            if(nums[h]>=nums[l]){
                return nums[l];
            }
        }
        return nums[0];
    }
}
