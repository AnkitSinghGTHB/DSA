class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] result = new long[n];
        //group indices by value
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        //process each group
        for (List<Integer> indices : map.values()) {
            int m = indices.size();
            if (m == 1) continue; //no other same value, dist 0
            //prefix sum of indices
            long[] prefix = new long[m];
            prefix[0] = indices.get(0);
            for (int i = 1; i < m; i++) {
                prefix[i] = prefix[i - 1] + indices.get(i);
            }
            //for each index, compute sum of distances to all other indices in the group
            for (int i = 0; i < m; i++) {
                int idx = indices.get(i);
                long leftSum = (i > 0) ? prefix[i - 1] : 0;
                long rightSum = prefix[m - 1] - prefix[i];
                long leftCount = i;
                long rightCount = m - 1 - i;
                long dist = idx * leftCount - leftSum + (rightSum - idx * rightCount);
                result[idx] = dist;
            }
        }
        return result;
    }
}
