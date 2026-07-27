class Solution {
    public int maxProduct(int[] nums) {
        //okay now this is like day before yesterday's ques
        int m1=0, m2=0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            int t=nums[i];
            if (t>m1){
                m2=m1;
                m1=t;
            }
            else if(t>m2){
                m2=t;
            }
        }
        return (m1-1)*(m2-1);
    }
}
