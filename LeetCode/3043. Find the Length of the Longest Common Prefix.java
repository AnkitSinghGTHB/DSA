class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        //my happy little inefficient solution, i forgot how to do tries
        Set<String> prefixes = new HashSet<>();
        //store all prefixes from arr1
        for (int num : arr1) {
            String s = Integer.toString(num);
            for (int i = 1; i <= s.length(); i++) {
                prefixes.add(s.substring(0, i));
            }
        }
        int maxLen = 0;
        //check prefixes from arr2
        for (int num : arr2) {
            String s = Integer.toString(num);
            for (int i = 1; i <= s.length(); i++) {
                if (prefixes.contains(s.substring(0, i))) {
                    maxLen = Math.max(maxLen, i);
                }
            }
        }
        return maxLen;
    }
}
