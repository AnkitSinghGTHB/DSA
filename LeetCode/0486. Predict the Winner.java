class Solution {
    public boolean predictTheWinner(int[] nums) {
        //a 2 ptr approahc?
        //i see there are two ways to this ques
        //for example take the 1,5,200,7 , if 1 chosen then good, if 7 chosen then no
        //so we have to keep that in mind too, i dont wanna do dp here
        //how about recursion (rip space complexity)
        return maxDiff(nums, 0, nums.length - 1) >= 0;
    }
    /* let me map it out 
    1,5,200,7 , n=4
    md(0,3)
        pL = 1 - md(1,3)
            md(1,3)
                pL = 5 - md(2,3)
                    md(2,3)
                        pL = 200 - 7 = 193
                        pR = 7 - 200 = -193
                        ret 193
                    pL = 5 - 193 = -188
                pR = 7 - md(1,2)
                    md(1,2)
                        pL = 5 - 200 = -195
                        pR = 200 - 5 = 195
                        ret 195
                    pR = 7 - 195 = -188
                ret max(-188, -188) = -188
            pL = 1 - (-188) = 189
        pR = 7 - md(0,2)
            md(0,2)
                pL = 1 - md(1,2)
                    md(1,2) = 195
                    pL = 1 - 195 = -194
                pR = 200 - md(0,1)
                    md(0,1)
                        pL = 1 - 5 = -4
                        pR = 5 - 1 = 4
                        ret 4
                    pR = 200 - 4 = 196
                ret max(-194, 196) = 196
            pR = 7 - 196 = -189
        ret max(189, -189) = 189
    ret val is +ve so answers true
    
     */
    private int maxDiff(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int pickLeft = nums[left] - maxDiff(nums, left + 1, right);
        int pickRight = nums[right] - maxDiff(nums, left, right - 1);
        return Math.max(pickLeft, pickRight);
    }
}
