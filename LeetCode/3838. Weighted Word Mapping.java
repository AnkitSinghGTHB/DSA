class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            int total = 0;
            for (char c : word.toCharArray()) {
                total += weights[c - 'a'];
            }
            int remainder = total % 26;
            // map 0->'z', 1->'y', ..., 25->'a'
            char mapped = (char) ('z' - remainder);
            result.append(mapped);
        }
        return result.toString();
    }
}
