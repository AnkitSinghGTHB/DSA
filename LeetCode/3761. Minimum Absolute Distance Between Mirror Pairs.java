class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> lastIndex = new HashMap<>();
        int minDist = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            //check if current number is the reverse of some previous number
            if (lastIndex.containsKey(nums[i])) {
                int dist = i - lastIndex.get(nums[i]);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
            //store the reverse of current number for future matches
            int rev = reverse(nums[i]);
            lastIndex.put(rev, i);
        }
        
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
    
    private int reverse(int x) {
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }
        return rev;
    }
}
