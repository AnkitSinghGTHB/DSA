class Solution {
    public boolean checkOnesSegment(String s) {
        //can we just see if patter like 01 exists then it will be false ig
        return !s.contains("01");
    }
}
