class Solution {
    public int[] sortByBits(int[] arr) {        
        Integer[] nums = new Integer[arr.length];//convert to Integer array for custom sorting
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i];
        }        
        //sort using comparator: first by bit count, then by value
        Arrays.sort(nums, (a, b) -> {
            int bitA = Integer.bitCount(a);
            int bitB = Integer.bitCount(b);
            if (bitA != bitB) {
                return Integer.compare(bitA, bitB);
            }
            return Integer.compare(a, b);
        });
        for (int i = 0; i < arr.length; i++) {//convert back to int[]
            arr[i] = nums[i];
        }
        return arr;
    }
}
