class Solution {
    public String stoneGameIII(int[] stoneValue) {
        //one can only pick from front
        //ques is if there are only 3 elements in array, what if alice picks all 3
        //okay testcase shows "Alice" as the answer in that n=3 case
        //okay another prob, this is where dp is required, i hate dp
        //so imagine big array
        // 1,2,3,4,5,6,7,8,9
        //here if a goes 1+2+3 -> 6
        // then b 4+5+6 -> 15
        //then a 6+ 8+9 -> 23, alice wins
        //but if they play optimally they will try that their opponents get least scr
        //i had to ask deepseek to get to know the dp logic here
        int n = stoneValue.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int best = Integer.MIN_VALUE;
            int sum = 0;
            for (int x = 1; x <= 3 && i + x - 1 < n; x++) {
                sum += stoneValue[i + x - 1];
                best = Math.max(best, sum - dp[i + x]);
            }
            dp[i] = best;
        }
        if (dp[0] > 0) return "Alice";
        else if (dp[0] < 0) return "Bob";
        else return "Tie";
    }
}
