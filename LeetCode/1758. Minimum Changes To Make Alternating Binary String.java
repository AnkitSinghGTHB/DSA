class Solution {
    public int minOperations(String s) {
        int count1 = 0; //mismatches for pattern starting with '0'
        int count2 = 0; //mismatches for pattern starting with '1'
        for (int i = 0; i < s.length(); i++) {
            char expectedForPattern0 = (i % 2 == 0) ? '0' : '1';
            char expectedForPattern1 = (i % 2 == 0) ? '1' : '0';
            if (s.charAt(i) != expectedForPattern0) count1++;
            if (s.charAt(i) != expectedForPattern1) count2++;
        }
        return Math.min(count1, count2);
    }
}
