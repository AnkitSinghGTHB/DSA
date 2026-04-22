class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        for (String query : queries) {
            for (String dictWord : dictionary) {
                if (canTransform(query, dictWord)) {
                    result.add(query);
                    break; //no need to check other dictionary words
                }
            }
        }
        return result;
    }

    private boolean canTransform(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 2) return false;
            }
        }
        return true;
    }
}
