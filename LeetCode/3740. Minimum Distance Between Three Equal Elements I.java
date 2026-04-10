class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        //group indices by value
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        int minDist = Integer.MAX_VALUE;
        for (List<Integer> indices : map.values()) {
            if (indices.size() < 3) continue;
            Collections.sort(indices);
            //for each triple of consecutive indices (smallest span)
            for (int i = 0; i <= indices.size() - 3; i++) {
                int dist = 2 * (indices.get(i + 2) - indices.get(i));
                if (dist < minDist) minDist = dist;
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
