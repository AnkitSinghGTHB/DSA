class Solution {
    public String maxSumOfSquares(int num, int sum) {
        //num is no. of digits of n
        //sum is sum of digits of n
        if (sum > 9 * num || sum < 1) {
            return "";
        }
        int q = sum / 9;
        int r = sum % 9;
        StringBuilder sb = new StringBuilder();
        if (r == 0) {
            for (int i = 0; i < q; i++) sb.append('9');
            for (int i = 0; i < num - q; i++) sb.append('0');
        } else {
            for (int i = 0; i < q; i++) sb.append('9');
            sb.append((char)('0' + r));
            for (int i = 0; i < num - q - 1; i++) sb.append('0');
        }
        return sb.toString();
    }
}
