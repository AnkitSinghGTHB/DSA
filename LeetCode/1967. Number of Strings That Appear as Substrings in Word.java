class Solution {
    public int numOfStrings(String[] patterns, String word) {
        //in python it is pretty easy
        //what would be in java? oh contains() method
        //usage: str.contains(substr) -> bool
        int count = 0;
        for (String p : patterns) {
            if (word.contains(p)) count++;
        }
        return count;
    }
}
