class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        int operations = 0;
        while (!isNonDecreasing(list)) {
            //find the adjacent pair with minimum sum (leftmost if tie)
            int minSum = Integer.MAX_VALUE;
            int idx = -1;
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < minSum) {
                    minSum = sum;
                    idx = i;
                }
            }
            //merge the pair at idx
            int newVal = list.get(idx) + list.get(idx + 1);
            list.set(idx, newVal);
            list.remove(idx + 1);
            operations++;
        }
        return operations;
    }
    private boolean isNonDecreasing(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
