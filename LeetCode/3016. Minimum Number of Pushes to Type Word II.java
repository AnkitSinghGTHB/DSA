class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        //this is supposed to be the overthought soln
        //first i make frequency map then i remap the keys
        //how do i keep them in sorted manner tho
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        // Sort frequencies in descending order
        Integer[] counts = Arrays.stream(freq).boxed().sorted(Collections.reverseOrder()).toArray(Integer[]::new);
        int total = 0;
        for (int i = 0; i < 26 && counts[i] > 0; i++) {
            int pushes = i / 8 + 1;   // first 8 get 1 push, next 8 get 2, etc.
            total += counts[i] * pushes;
        }
        return total;
    }
}
