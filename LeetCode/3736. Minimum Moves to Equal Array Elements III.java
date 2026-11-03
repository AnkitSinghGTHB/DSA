class Solution {
    public int minMoves(int[] nums) {
        // so i can only increase one element by 1 per move
        //i need to get the min total moves i can make to keep all equal
        //the final equal value must be at least the current max
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int moves = 0;
        for (int x : nums) {
            moves += max - x;
        }
        return moves;
    }
}
