class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        //group indices by value
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        //pre‑compute circular distance helper
        List<Integer> ans = new ArrayList<>();
        for (int q : queries) {
            int val = nums[q];
            List<Integer> indices = map.get(val);
            if (indices.size() == 1) {
                ans.add(-1);
                continue;
            }
            //find pos of q in the sorted list
            int pos = Collections.binarySearch(indices, q);
            //bcoz q is guaranteed to be in the list
            int size = indices.size();
            int left = indices.get((pos - 1 + size) % size);
            int right = indices.get((pos + 1) % size);
            int distLeft = Math.min(Math.abs(q - left), n - Math.abs(q - left));
            int distRight = Math.min(Math.abs(q - right), n - Math.abs(q - right));
            ans.add(Math.min(distLeft, distRight));
        }
        return ans;
    }
}
