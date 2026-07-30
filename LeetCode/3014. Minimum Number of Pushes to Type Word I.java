class Solution {
    public int minimumPushes(String word) {
        //okay i was just overthinking it
        int n = word.length();
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += (i / 8) + 1;   //first 8 letters get 1 push, next 8 get 2, etc.
        }
        return total;
    }
}
