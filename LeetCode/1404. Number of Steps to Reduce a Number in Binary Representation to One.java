class Solution {
    public int numSteps(String s) {
        int steps = 0;
        StringBuilder sb = new StringBuilder(s);
        // Continue until we reach "1"
        while (!(sb.length() == 1 && sb.charAt(0) == '1')) {
            int len = sb.length();
            if (sb.charAt(len - 1) == '0') {
                // even: divide by 2 (remove last character)
                sb.deleteCharAt(len - 1);
            } else {
                // odd: add 1
                int i = len - 1;
                // Flip all trailing 1's to 0
                while (i >= 0 && sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                    i--;
                }
                if (i >= 0) {
                    // Flip the first 0 to 1
                    sb.setCharAt(i, '1');
                } else {
                    // All bits were 1, insert a leading 1
                    sb.insert(0, '1');
                }
            }
            steps++;
        }
        return steps;
    }
}