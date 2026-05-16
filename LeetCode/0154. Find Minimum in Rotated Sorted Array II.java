class Solution {
    public int findMin(int[] nums) {
        //this odly looks familiar to a ques i did yesterday
        //only catch is that this time there are repeated elements
        //and doing that would be way more difficult ig
        int h = nums.length-1;
        int l= 0;
        while(l<h){
            int mid = (h+l)/2;
            if(nums[mid]>nums[h]){
                l=mid+1;//min is in the right half
            }
            else if(nums[mid]<nums[h]){
                h=mid;//min is in the left half (including mid)
            }
            else{//nums[mid] == nums[h], cannot decide, shrink from right
                h--;
            }
        }
        return nums[l];
    }
}
