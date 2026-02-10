class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int[] lastSeen = new int[100001]; //since nums[i] <= 1e5
        Arrays.fill(lastSeen, -1);
        int best = 0;
        
        for (int i = 0; i < n; i++) {
            if (n - i <= best) break; //cannot get longer
            int distinctEven = 0, distinctOdd = 0;
            for (int j = i; j < n; j++) {
                int x = nums[j];
                if (lastSeen[x] < i) { //first time in this subarray
                    if (x % 2 == 0) distinctEven++;
                    else distinctOdd++;
                    lastSeen[x] = i;
                }
                if (distinctEven == distinctOdd) {
                    best = Math.max(best, j - i + 1);
                }
            }
        }        
        return best;
    }
}
