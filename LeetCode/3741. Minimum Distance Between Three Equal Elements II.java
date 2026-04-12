class Solution {
    public int minimumDistance(int[] nums) {
        //hey wait a minute
        int n = nums.length;
        //group indices by value
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        int minSpan = Integer.MAX_VALUE;
        for (List<Integer> indices : map.values()) {
            if (indices.size() < 3) continue;
            //sort indices (they are naturally in increasing order if we iterate i from 0 to n-1)
            //but we will sort anyway to be safe.
            Collections.sort(indices);
            for (int i = 0; i <= indices.size() - 3; i++) {
                int span = indices.get(i + 2) - indices.get(i);
                if (span < minSpan) {
                    minSpan = span;
                }
            }
        }
        return minSpan == Integer.MAX_VALUE ? -1 : 2 * minSpan;
    }
}
