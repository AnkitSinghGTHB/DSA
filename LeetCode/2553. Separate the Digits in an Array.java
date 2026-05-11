class Solution {
    public int[] separateDigits(int[] nums) {
        //tf is this question bro
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            //reversing issue
            List<Integer> rev = new ArrayList<>();
            while (num > 0) {
                rev.add(num % 10);
                num /= 10;
            }
            //now it is correct order
            for (int i = rev.size() - 1; i >= 0; i--) {
                list.add(rev.get(i));
            }
        }
        //covnersion of List<Integer> to int[]
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
