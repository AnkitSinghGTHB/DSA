class Solution {
    public int numberOfSubstrings(String s) {
        //we can manage a hashmap with the count of those characters ig
        //lets see with abcabc example
        //a:2 , b:2, c:2
        //is it a pnc question
        //like total permutations
        //a -> 2C1
        //b -> 2C1
        //c -> 2C1
        //so like a and b and c
        // 2C1 x 2C1 x 2C1
        // or am i missing something?
        //i feel it would then overcount
        //other than that we can use 2ptr ig
        int n = s.length();
        int[] count = new int[3]; // indices 0,1,2 for 'a','b','c'
        int left = 0;
        int ans = 0;
        for (int right = 0; right < n; right++) {
            count[s.charAt(right) - 'a']++;
            //shrink left as much as possible while window is still valid
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                //try to remove the leftmost character
                int c = s.charAt(left) - 'a';
                count[c]--;
                left++;
            }
            //after the loop, the window [left..right] is NOT valid (one counter is zero)
            //but the window [left-1..right] WAS valid, and all starts <= left-1 are valid
            //so number of valid substrings ending at 'right' is exactly 'left'
            ans += left;
        }
        return ans;
    }
}
