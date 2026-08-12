class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        //do i count every subarray?
        //i get it that i will be using a sliding window but
        //but how do i change the count?
        HashMap<Integer, Integer> f = new HashMap<>();
        int mx_l=0;
        for(int l=0, r=0; r<nums.length;r++){
            f.merge(nums[r], 1, Integer::sum); //this is the new thing ilearnt
            while(f.get(nums[r])>k){
                f.merge(nums[l++], -1, Integer::sum);
            }
            mx_l=Math.max(mx_l, r-l+1);
        }
        return mx_l;
    }
}
