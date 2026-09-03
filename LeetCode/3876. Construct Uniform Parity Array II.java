class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = nums1[0];
        boolean allEven = true, allOdd = true;
        for (int x : nums1) {
            if (x < min) {
                min = x;
            }
            if (x % 2 == 0) {
                allOdd = false;
            }
            else {
                allEven = false;
            }
        }
        if (allEven || allOdd) {
            return true;
        }
        return min % 2 == 1;
    }
}
