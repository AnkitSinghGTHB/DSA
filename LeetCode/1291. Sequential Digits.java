class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        for (int len = 1; len <= 9; len++) {
            for (int start = 1; start <= 9 - len + 1; start++) {
                int num = 0;
                int digit = start;
                for (int i = 0; i < len; i++) {
                    num = num * 10 + digit;
                    digit++;
                }
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        Collections.sort(result);
        return result;
    }
}
