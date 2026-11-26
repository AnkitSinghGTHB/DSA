class Solution {
    public boolean canAliceWin(int n) {
        //bob cant win if he cant make his move
        //alice  will lose if bob has enough
        //they are using some bitmask lookup
        // ao normal integer counting is also possible since n<=50
        if ((n >= 10 && n <= 18) || (n >= 27 && n <= 33) || (n >= 40 && n <= 44) || n >= 49) {
            return true;
        } else {
            return false;
        }
    }
}
