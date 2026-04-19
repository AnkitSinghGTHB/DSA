class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int j = 0;
        int maxDist = 0;
        for (int i = 0; i < n1; i++) {
            //ensure j is at least i (since we need i <= j)
            if (j < i) j = i;
            //mov j to the right as long as the condition holds
            while (j < n2 && nums2[j] >= nums1[i]) {
                j++;
            }
            //after the loop, the last valid index is j-1
            if (j - 1 >= i) {
                maxDist = Math.max(maxDist, (j - 1) - i);
            }
        }
        return maxDist;
    }
}
