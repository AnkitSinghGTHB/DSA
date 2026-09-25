class Solution {
    public int smallestIndex(int[] nums) {
        int n = nums.length;
        for (int i=0;i<n;i++){
            int t=nums[i];
            int sum=0;
            while(t>0){
                int d = t%10;
                sum+=d;
                t/=10;
            }
            if(sum==i){
                return i;
            }
        }
        return -1;
    }
}
