class Solution {
    public int missingMultiple(int[] nums, int k) {
        //my soln is bad for unsorted input
        // int mulmin=1;
        // int mulmax=1;
        // for (int i=0;i<nums.length;i++){
        //     int r = nums[i]%k;
        //     if(r==0){
        //         int q=nums[i]/k;
        //         if(q>mulmax){
        //             mulmax=q;
        //         }
        //         if(q==mulmin){
        //             mulmin++;
        //         }
        //     }
        // }
        // if(mulmin<mulmax){
        //     return mulmin*k;
        // }
        // return (mulmax+1)*k;

        Set<Integer> seen = new HashSet<>();
        for (int x : nums) {
            if (x % k == 0) {
                seen.add(x / k);
            }
        }
        int m = 1;
        while (seen.contains(m)) m++;
        return m * k;
    }
}
