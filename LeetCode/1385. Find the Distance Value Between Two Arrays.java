class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        //ok so abs(a1 - a2) <=d then counts
        int count = 0;
        for (int x : arr1) {
            boolean valid = true;
            for (int y : arr2) {
                if (Math.abs(x - y) <= d) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                count++;
            }
        }
        return count;
    }
}
