class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;
        for (int num = num1; num <= num2; num++) {
            total += peakcheck(num);
        }
        return total;
    }
    public int peakcheck(int num){
        //num is not neccessarily a 3 digit number
        //so that just 3 dig apprch was not good
        String s = Integer.toString(num);
        int n = s.length();
        if (n < 3) return 0;
        int count = 0;
        for (int i = 1; i < n - 1; i++) {
            char left = s.charAt(i - 1);
            char mid = s.charAt(i);
            char right = s.charAt(i + 1);
            if (mid > left && mid > right) {
                count++;
            } 
            else if (mid < left && mid < right) {
                count++;
            }
        }
        return count;
    }
}
