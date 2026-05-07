class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] prefMax = new int[n]; //prefix maximum
        prefMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefMax[i] = Math.max(prefMax[i-1], nums[i]);
        }
        int[] suffMin = new int[n]; //suffix minimum
        suffMin[n-1] = nums[n-1];
        for (int i = n-2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i+1], nums[i]);
        }
        //find cut positions where left max <= right min
        List<Integer> cuts = new ArrayList<>();
        cuts.add(-1); //left boundary
        for (int i = 0; i < n-1; i++) {
            if (prefMax[i] <= suffMin[i+1]) {
                cuts.add(i);
            }
        }
        cuts.add(n-1); //right boundary
        int[] ans = new int[n];
        for (int idx = 0; idx < cuts.size()-1; idx++) {
            int L = cuts.get(idx) + 1;
            int R = cuts.get(idx+1);
            int maxVal = prefMax[R]; //max in this segment
            for (int i = L; i <= R; i++) {
                ans[i] = maxVal;
            }
        }
        return ans;
    }
}
