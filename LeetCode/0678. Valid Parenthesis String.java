class Solution {
    public boolean checkValidString(String s) {
        //uh backtrack
        //if we are using dp here then like lets count (
        //with stack to balance the ( & )
        //* will be used to even out if anything is wrong
        //main issue is if like somethinglike this arrives *()(
        //lets see
        int low = 0;   // min possible (
        int high = 0;  // max possible (

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } 
            else if (c == ')') {
                low = Math.max(low - 1, 0);
                high--;
                if (high < 0) return false; // too many ')'
            } 
            else { // '*'
                low = Math.max(low - 1, 0); // treat '*' as ')'
                high++;                     // treat '*' as '('
            }
        }

        return low == 0;
    }
}
