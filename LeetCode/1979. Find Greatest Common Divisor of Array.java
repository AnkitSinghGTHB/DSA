class Solution {
    public int findGCD(int[] nums) {
        int mx=1;
        int mn=1000;
        for (int i=0;i<nums.length;i++){
            if(nums[i]>mx){
                mx=nums[i];
            }
            if(nums[i]<mn){
                mn=nums[i];
            }
        }
        //now i get gcd
        int ans=0;
        for (int i=mn;i>=1;i--){
            if(mx%i==0 && mn%i==0){
                ans=i;
                break;
            }
        }
        return ans; //can i do in less than O(n)?
    }
}
