class Solution {
    public int maxDistinct(String s) {
        //This question requires HashMap
        //www.w3schools.com/java/java_hashmap.asp
        HashMap<Character, Integer> unique = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            unique.put(s.charAt(i), 1);
        }
        return unique.size();
    }
}
